package com.dslplatform.examples.android.utilities;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.dslplatform.examples.android.MainActivity;
import com.dslplatform.examples.android.R;
import com.dslplatform.examples.android.AndroidLibrary.Book;
import com.dslplatform.examples.android.AndroidLibrary.Review;
import com.dslplatform.examples.android.AndroidLibrary.repositories.BookRepository;
import com.dslplatform.patterns.Specification;

public class AddDialogFragment extends DialogFragment {

	private Spinner spinnerRating;
	private EditText editReviewedBy;
	private EditText editComment;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		super.onCreateDialog(savedInstanceState);

		final String isbn = getArguments().getString("isbn");

		final AlertDialog.Builder builder = new AlertDialog.Builder(
				getActivity());

		final LayoutInflater inflater = getActivity().getLayoutInflater();
		final View v = inflater.inflate(R.layout.add_review_layout, null);
		spinnerRating = (Spinner) v.findViewById(R.id.spinner_rating);
		editReviewedBy = (EditText) v.findViewById(R.id.edit_reviwedBy);
		editComment = (EditText) v.findViewById(R.id.edit_comment);

		builder.setView(v).setTitle("Add review")
				.setNegativeButton(R.string.close, null)
				.setPositiveButton(R.string.add_review, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						addReviewToDatabase(isbn);
					}
				});
		return builder.create();
	}

	/**
	 * Adds a new review to the database. First it uses a Specification 
	 * to get the book to which we are going to add the review, 
	 * than it gets the reviews, adds the new one and at the end it 
	 * persists the book.
	 * 
	 * @param isbn - isbn of the book to which we are adding a new review
	 */
	private void addReviewToDatabase(String isbn) {
		//specification for getting the book
		final BookRepository bookRepository = new BookRepository(
				MainActivity.locator);
		final Specification<Book> isbnEquals = new Book.isbnEquals(isbn);
		final Future<List<Book>> futureResults = bookRepository
				.search(isbnEquals);

		//creating new Review that needs to be added
		final Review review = new Review();
		review.setRating(Integer.parseInt(String.valueOf(spinnerRating
				.getSelectedItem())));
		review.setReviewdBy(editReviewedBy.getText().toString());
		review.setComment(editComment.getText().toString());

		try {
			//getting the book, adding the review and persisting
			final Book book = futureResults.get().get(0);
			book.getReviews().add(review);
			book.persist();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
