package com.dslplatform.exmaples.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.joda.time.LocalDate;
import org.slf4j.Logger;

import android.util.Log;

import com.dslplatform.client.Bootstrap;
import com.dslplatform.examples.android.AndroidLibrary.Book;
import com.dslplatform.examples.android.AndroidLibrary.Review;
import com.dslplatform.patterns.ServiceLocator;

public class DatabaseTest extends TestCase {

	static ServiceLocator locator;

	protected void setUp() {
		try {
			System.setProperty("org.slf4j.simpleLogger.defaultLogLevel",
					"trace");
			locator = Bootstrap.init(DatabaseTest.class
					.getResourceAsStream("/dsl-project.ini"));
			locator.resolve(Logger.class).info("Locator has been initialized.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("setUp");

	}

	public void testMethodDatabase() {
		System.out.println("testMethodDatabsase");
		try {

			Log.d("DELETE", "Deleting all books!");
			//deletes all the books we already have
			for (Book b : Book.findAll()) {
				b.delete();
			}

			//creating new Book object
			Book book = new Book();

			LocalDate date;

			//creating new review list
			List<Review> reviews = new ArrayList<Review>();
			//adding reviews to the list
			reviews.add(new Review(
					4,
					"Very well paced, interesting story line and great characters.",
					"Paul Noel"));
			reviews.add(new Review(
					5,
					"The first few chapters were kind of slow but once the story picked up I couldn't "
							+ "put the book down and I dropped all my weekend plans until I had read the last page.",
					"Kirima"));
			reviews.add(new Review(
					3,
					"The plot is unusual, for sure, but that's not enough to make it a great book.",
					"Matthew Hickerson"));

			//creating new authors list
			List<String> authors = new ArrayList<String>();
			//adding authors to list
			authors.add("Stieg Larsson");

			//creating new date 
			date = new LocalDate(2009, 10, 16);

			Log.d("ADD", "Adding book: The Girl with the Dragon Tattoo");
			//adding all data for the book and persisting it to the database
			book.setTitle("The Girl with the Dragon Tattoo")
					.setIsbn("978-0307454546").setAuthors(authors)
					.setLanguage("English").setPages(599).setPublishedOn(date)
					.setPublisher("Vintage Crime").setReviews(reviews)
					.persist();

			//clearing list, and adding data for the other books
			date = new LocalDate(2010, 6, 3);
			authors.clear();
			authors.add("Suzanne Collins");
			reviews.clear();
			reviews.add(new Review(5,
					"The story is well written, love Suzanne Collins books",
					"Sandonna Lingnau"));
			reviews.add(new Review(
					5,
					"This is not a book that you read this for enjoyment but rather for a sense of justice and victory!",
					"Bibliophile"));
			reviews.add(new Review(
					5,
					"I liked that you did not know what was going to happen until the end of each book.",
					"Cheryl"));
			book = new Book();
			Log.d("ADD", "Adding book: The Hunger Games");
			book.setTitle("The Hunger Games").setIsbn("978-0439023528")
					.setAuthors(authors).setLanguage("English").setPages(384)
					.setPublishedOn(date).setPublisher("Scholastic")
					.setReviews(reviews).persist();

			date = new LocalDate(1999, 9, 8);
			authors.clear();
			authors.add("J. K. Rowling");
			reviews.clear();
			reviews.add(new Review(
					5,
					"I highly recommend this book to anyone who hasn't read it yet, kid or adult.",
					"Amazing Harry"));
			reviews.add(new Review(
					5,
					"Just read this book for the first time and was very happy finished before time to get the next one so had to borrow the next one.",
					"Patricia Mullins"));
			reviews.add(new Review(
					5,
					"Harry Potter and the Sorceror's Stone is one of those rare children's books that seems to be utterly wasted on children.",
					"Customer"));
			book = new Book();
			Log.d("ADD", "Adding book: Harry Potter and the Sorcerer's Stone");
			book.setTitle("Harry Potter and the Sorcerer's Stone")
					.setIsbn("978-0590353427").setAuthors(authors)
					.setLanguage("English").setPages(320).setPublishedOn(date)
					.setPublisher("Scholastic").setReviews(reviews).persist();

			authors.clear();
			authors.add("Stephenie Meyer");
			date = new LocalDate(2011, 8, 15);
			reviews.clear();
			reviews.add(new Review(
					5,
					"I recommend this book for anyone this isn't just a young adult book this is for all ages. boys and girls.",
					"Hannhah Carr"));
			reviews.add(new Review(
					5,
					"I recommend to any young girl who believes in love at first sight and believe in the power of of love and destiny.",
					"Latish Simon"));
			reviews.add(new Review(
					5,
					"This is a great book for all ages, romance, action, a very nice love story, a mystery that keeps you wanting more.",
					"Lora Snow"));
			book = new Book();
			Log.d("ADD", "Adding book: The Twilight Saga, Book 1");
			book.setTitle("The Twilight Saga, Book 1")
					.setIsbn("978-0316015844").setAuthors(authors)
					.setLanguage("English").setPages(374).setPublishedOn(date)
					.setPublisher("Little, Brown Books for Young Readers")
					.setReviews(reviews).persist();

			authors.clear();
			authors.add("J. K. Rowling");
			date = new LocalDate(2004, 8, 10);
			reviews.clear();
			reviews.add(new Review(
					5,
					"The book is packed with the action, humor, and mystery that has made the series just as enjoyable for adults to read as for children.",
					"homebody315"));
			reviews.add(new Review(
					5,
					"Harry Potter and the Order of the Pheonix is an incredible book. It is definitely the best of the series so far.",
					"Heather"));
			reviews.add(new Review(
					5,
					"I don't want to give too much away but I will say that knowing that someone was going to die made the whole book more suspenseful.",
					"Kristin"));
			book = new Book();
			Log.d("ADD",
					"Adding book: Harry Potter and the Order of the Phoenix");
			book.setTitle("Harry Potter and the Order of the Phoenix")
					.setIsbn("978-0439358078").setAuthors(authors)
					.setLanguage("English").setPages(956).setPublishedOn(date)
					.setPublisher("Scholastic Inc").setReviews(reviews)
					.persist();

			authors.clear();
			authors.add("Dan Brown");
			date = new LocalDate(2009, 5, 31);
			reviews.clear();
			reviews.add(new Review(
					5,
					"The story developes at a very fast pace and will keep you entertained to the end.",
					"Doug Carins"));
			reviews.add(new Review(
					5,
					"I couldn't put it down once I started. It's a work of FICTION.",
					"DemandingSky"));
			reviews.add(new Review(
					2,
					"However, I still think there are better works of suspense out there if you look for them.",
					"Scott George"));
			book = new Book();
			Log.d("ADD", "Adding book: The Da Vinci Code");
			book.setTitle("The Da Vinci Code").setIsbn("978-0307277674")
					.setAuthors(authors).setLanguage("English").setPages(597)
					.setPublishedOn(date).setPublisher("Anchor")
					.setReviews(reviews).persist();

			authors.clear();
			authors.add("Eric Evans");
			date = new LocalDate(2003, 8, 30);
			reviews.clear();
			reviews.add(new Review(
					5,
					"The examples in this book are real-world, make sense, and are applicable to solving any business problem. The only wish I have is that I haven't read this book years ago.",
					"Giftcard recipient"));
			reviews.add(new Review(
					5,
					"This book is fantastic, a great resource and an excellent reference.",
					"Janek Claus"));
			reviews.add(new Review(
					5,
					"This is one of the you must read books about OOP. It's simple, well written and also lets you learn a lot about how you have to build OOP applications, design and architecture aspects.",
					"Matias"));
			book = new Book();
			Log.d("ADD",
					"Adding book: Domain-Driven Design: Tackling Complexity in the Heart of Software");
			book.setTitle(
					"Domain-Driven Design: Tackling Complexity in the Heart of Software")
					.setIsbn("978-0321125215").setAuthors(authors)
					.setLanguage("English").setPages(560).setPublishedOn(date)
					.setPublisher(" Addison-Wesley Professional")
					.setReviews(reviews).persist();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
