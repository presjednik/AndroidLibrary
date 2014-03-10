package com.dslplatform.examples.android;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dslplatform.examples.android.AndroidLibrary.Book;
import com.dslplatform.examples.android.AndroidLibrary.Review;
import com.dslplatform.examples.android.AndroidLibrary.repositories.BookRepository;
import com.dslplatform.examples.android.utilities.BookArrayAdapter;
import com.dslplatform.examples.android.utilities.ListDialogFragment;
import com.dslplatform.patterns.Specification;

public class SearchActivity extends FragmentActivity {

	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.list_books);

		handleIntent(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}

	/**
	 * Method for handling the intent. Getting the search query 
	 * and using the specification to get books for the query.
	 * 
	 * @param intent - intent from whom we get the query String
	 */
	private void handleIntent(Intent intent) {

		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			final String query = intent.getStringExtra(SearchManager.QUERY)
					.toLowerCase();

			try {
				//create a searchBooks specification with query String
				final BookRepository bookRepository = new BookRepository(
						MainActivity.locator);
				final Specification<Book> searchBooks = new Book.searchBooks(
						query);
				final Future<List<Book>> futureResults = bookRepository
						.search(searchBooks);

				final List<Book> bookList = futureResults.get();

				final BookArrayAdapter bookAdapter = new BookArrayAdapter(this,
						R.layout.book_layout, bookList);

				listView.setTextFilterEnabled(true);
				listView.setAdapter(bookAdapter);
				
				listView.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						final Book book = (Book) parent.getAdapter()
								.getItem(position);
						getReviews(book.getIsbn());
					}
				});

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Gets all reviews for a book using DSL specifications.
	 * Sends three lists to ListDialogFragment so he can display the reviews.
	 * 
	 * @param isbn - isbn of the book we want the reviews
	 */
	private void getReviews(String isbn) {
		final BookRepository bookRepository = new BookRepository(
				MainActivity.locator);
		final Specification<Book> isbnEquals = new Book.isbnEquals(isbn);
		final Future<List<Book>> futureResults = bookRepository
				.search(isbnEquals);

		final ArrayList<Integer> images = new ArrayList<Integer>();
		final ArrayList<String> reviewed = new ArrayList<String>();
		final ArrayList<String> comments = new ArrayList<String>();
		try {
			for (Review r : futureResults.get().get(0).getReviews()) {
				images.add(r.getRating());
				reviewed.add(r.getReviewdBy());
				comments.add(r.getComment());
			}

			final ListDialogFragment listFragment = new ListDialogFragment();
			final Bundle bundle = new Bundle();
			bundle.putStringArrayList("reviewed", reviewed);
			bundle.putStringArrayList("comments", comments);
			bundle.putIntegerArrayList("images", images);
			bundle.putString("title", "Reviews");
			bundle.putString("isbn", isbn);
			listFragment.setArguments(bundle);
			final FragmentTransaction ft = getSupportFragmentManager()
					.beginTransaction();
			ft.add(listFragment, null);
			ft.commitAllowingStateLoss();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
