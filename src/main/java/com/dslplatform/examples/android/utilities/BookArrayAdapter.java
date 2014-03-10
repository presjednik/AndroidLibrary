package com.dslplatform.examples.android.utilities;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dslplatform.examples.android.R;
import com.dslplatform.examples.android.AndroidLibrary.Book;

public class BookArrayAdapter extends ArrayAdapter<Book> {

	private List<Book> books;

	public BookArrayAdapter(Activity context, int resource, List<Book> objects) {
		super(context, resource, objects);
		this.books = objects;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		View v = view;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.book_layout, null);
		}

		Book b = books.get(position);

		if (b != null) {
			final TextView title = (TextView) v.findViewById(R.id.title_book);
			final TextView author = (TextView) v.findViewById(R.id.author_book);
			final TextView isbn = (TextView) v.findViewById(R.id.isbn_book);
			final TextView published = (TextView) v.findViewById(R.id.published_book);
			final TextView publisher = (TextView) v.findViewById(R.id.publisher_book);
			final TextView pages = (TextView) v.findViewById(R.id.pages_book);
			final TextView language = (TextView) v.findViewById(R.id.language_book);

			title.setText("Title: " + b.getTitle());
			author.setText("Authors: " + getAllAuthors(b.getAuthors()));
			isbn.setText("ISBN: " + b.getIsbn());
			published.setText("Published on: " + b.getPublishedOn().toString());
			publisher.setText("Publisher: " + b.getPublisher());
			pages.setText("Pages: " + b.getPages());
			language.setText("Language: " + b.getLanguage());
		}

		return v;
	}

	/**
	 * Creates a string which holds all authors of the book.
	 * 
	 * @param authors - list with all authors
	 * @return string containg all authors of the book
	 */
	private String getAllAuthors(List<String> authors) {
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < authors.size(); i++) {
			builder.append(authors.get(i));
			if (i < authors.size() - 1) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}

}
