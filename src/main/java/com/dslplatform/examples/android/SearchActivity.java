package com.dslplatform.examples.android;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.dslplatform.examples.android.AndroidLibrary.Book;
import com.dslplatform.examples.android.AndroidLibrary.repositories.BookRepository;
import com.dslplatform.examples.android.utilities.BookArrayAdapter;
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

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
