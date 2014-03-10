package com.dslplatform.examples.android.AndroidLibrary;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;
import com.fasterxml.jackson.annotation.*;

public class Book implements java.io.Serializable, AggregateRoot {
    public Book() {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.isbn = "";
        this.title = "";
        this.authors = new java.util.ArrayList<String>();
        this.reviews = new java.util.ArrayList<com.dslplatform.examples.android.AndroidLibrary.Review>();
        this.publisher = "";
        this.language = "";
        this.publishedOn = new org.joda.time.LocalDate();
        this.pages = 0;
    }

    private transient final ServiceLocator _serviceLocator;
    private transient final DomainProxy _domainProxy;
    private transient final CrudProxy _crudProxy;

    private String URI;

    @JsonProperty("URI")
    public String getURI() {
        return this.URI;
    }

    @Override
    public int hashCode() {
        return URI != null ? URI.hashCode() : super.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;
        final Book other = (Book) obj;

        return URI != null && URI.equals(other.URI);
    }

    @Override
    public String toString() {
        return URI != null ? "Book(" + URI + ')' : "new Book("
                + super.hashCode() + ')';
    }

    private static final long serialVersionUID = 0x0097000a;

    public Book(
            final String isbn,
            final String title,
            final java.util.List<String> authors,
            final java.util.List<com.dslplatform.examples.android.AndroidLibrary.Review> reviews,
            final String publisher,
            final String language,
            final org.joda.time.LocalDate publishedOn,
            final int pages) {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        setIsbn(isbn);
        setTitle(title);
        setAuthors(authors);
        setReviews(reviews);
        setPublisher(publisher);
        setLanguage(language);
        setPublishedOn(publishedOn);
        setPages(pages);
    }

    @JsonCreator
    private Book(
            @JacksonInject("_serviceLocator") final ServiceLocator _serviceLocator,
            @JsonProperty("URI") final String URI,
            @JsonProperty("isbn") final String isbn,
            @JsonProperty("title") final String title,
            @JsonProperty("authors") final java.util.List<String> authors,
            @JsonProperty("reviews") final java.util.List<com.dslplatform.examples.android.AndroidLibrary.Review> reviews,
            @JsonProperty("publisher") final String publisher,
            @JsonProperty("language") final String language,
            @JsonProperty("publishedOn") final org.joda.time.LocalDate publishedOn,
            @JsonProperty("pages") final int pages) {
        this._serviceLocator = _serviceLocator;
        this._domainProxy = _serviceLocator.resolve(DomainProxy.class);
        this._crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.URI = URI;
        this.isbn = isbn == null ? "" : isbn;
        this.title = title == null ? "" : title;
        this.authors = authors == null
                ? new java.util.ArrayList<String>()
                : authors;
        this.reviews = reviews == null
                ? new java.util.ArrayList<com.dslplatform.examples.android.AndroidLibrary.Review>()
                : reviews;
        this.publisher = publisher == null ? "" : publisher;
        this.language = language == null ? "" : language;
        this.publishedOn = publishedOn == null
                ? new org.joda.time.LocalDate()
                : publishedOn;
        this.pages = pages;
    }

    public static Book find(final String uri) throws java.io.IOException {
        return find(uri, Bootstrap.getLocator());
    }

    public static Book find(final String uri, final ServiceLocator locator)
            throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator())
                    .resolve(CrudProxy.class).read(Book.class, uri).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    public static java.util.List<Book> find(final Iterable<String> uris)
            throws java.io.IOException {
        return find(uris, Bootstrap.getLocator());
    }

    public static java.util.List<Book> find(
            final Iterable<String> uris,
            final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator())
                    .resolve(DomainProxy.class).find(Book.class, uris).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    public static java.util.List<Book> findAll() throws java.io.IOException {
        return findAll(null, null, Bootstrap.getLocator());
    }

    public static java.util.List<Book> findAll(final ServiceLocator locator)
            throws java.io.IOException {
        return findAll(null, null, locator);
    }

    public static java.util.List<Book> findAll(
            final Integer limit,
            final Integer offset) throws java.io.IOException {
        return findAll(limit, offset, Bootstrap.getLocator());
    }

    public static java.util.List<Book> findAll(
            final Integer limit,
            final Integer offset,
            final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator())
                    .resolve(DomainProxy.class)
                    .findAll(Book.class, limit, offset, null).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    public static java.util.List<Book> search(
            final Specification<Book> specification) throws java.io.IOException {
        return search(specification, null, null, Bootstrap.getLocator());
    }

    public static java.util.List<Book> search(
            final Specification<Book> specification,
            final ServiceLocator locator) throws java.io.IOException {
        return search(specification, null, null, locator);
    }

    public static java.util.List<Book> search(
            final Specification<Book> specification,
            final Integer limit,
            final Integer offset) throws java.io.IOException {
        return search(specification, limit, offset, Bootstrap.getLocator());
    }

    public static java.util.List<Book> search(
            final Specification<Book> specification,
            final Integer limit,
            final Integer offset,
            final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator())
                    .resolve(DomainProxy.class)
                    .search(specification, limit, offset, null).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    public static long count() throws java.io.IOException {
        return count(Bootstrap.getLocator());
    }

    public static long count(final ServiceLocator locator)
            throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator())
                    .resolve(DomainProxy.class).count(Book.class).get()
                    .longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    public static long count(final Specification<Book> specification)
            throws java.io.IOException {
        return count(specification, Bootstrap.getLocator());
    }

    public static long count(
            final Specification<Book> specification,
            final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator())
                    .resolve(DomainProxy.class).count(specification).get()
                    .longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    private void updateWithAnother(
            final com.dslplatform.examples.android.AndroidLibrary.Book result) {
        this.URI = result.URI;

        this.isbn = result.isbn;
        this.title = result.title;
        this.authors = result.authors;
        this.reviews = result.reviews;
        this.publisher = result.publisher;
        this.language = result.language;
        this.publishedOn = result.publishedOn;
        this.pages = result.pages;
    }

    public Book persist() throws java.io.IOException {
        final Book result;
        try {
            result = this.URI == null
                    ? _crudProxy.create(this).get()
                    : _crudProxy.update(this).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
        this.updateWithAnother(result);
        return this;
    }

    public Book delete() throws java.io.IOException {
        try {
            return _crudProxy.delete(Book.class, URI).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    private String isbn;

    @JsonProperty("isbn")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(final String value) {
        if (value == null)
            throw new IllegalArgumentException(
                    "Property \"isbn\" cannot be null!");
        com.dslplatform.examples.android.Guards.checkLength(value, 14);
        this.isbn = value;

        return this;
    }

    private String title;

    @JsonProperty("title")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getTitle() {
        return title;
    }

    public Book setTitle(final String value) {
        if (value == null)
            throw new IllegalArgumentException(
                    "Property \"title\" cannot be null!");
        this.title = value;

        return this;
    }

    private java.util.List<String> authors;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("authors")
    public java.util.List<String> getAuthors() {
        return authors;
    }

    public Book setAuthors(final java.util.List<String> value) {
        if (value == null)
            throw new IllegalArgumentException(
                    "Property \"authors\" cannot be null!");
        com.dslplatform.examples.android.Guards.checkNulls(value);
        this.authors = value;

        return this;
    }

    private java.util.List<com.dslplatform.examples.android.AndroidLibrary.Review> reviews;

    @JsonProperty("reviews")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public java.util.List<com.dslplatform.examples.android.AndroidLibrary.Review> getReviews() {
        return reviews;
    }

    public Book setReviews(
            final java.util.List<com.dslplatform.examples.android.AndroidLibrary.Review> value) {
        if (value == null)
            throw new IllegalArgumentException(
                    "Property \"reviews\" cannot be null!");
        com.dslplatform.examples.android.Guards.checkNulls(value);
        this.reviews = value;

        return this;
    }

    private String publisher;

    @JsonProperty("publisher")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getPublisher() {
        return publisher;
    }

    public Book setPublisher(final String value) {
        if (value == null)
            throw new IllegalArgumentException(
                    "Property \"publisher\" cannot be null!");
        this.publisher = value;

        return this;
    }

    private String language;

    @JsonProperty("language")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getLanguage() {
        return language;
    }

    public Book setLanguage(final String value) {
        if (value == null)
            throw new IllegalArgumentException(
                    "Property \"language\" cannot be null!");
        this.language = value;

        return this;
    }

    private org.joda.time.LocalDate publishedOn;

    @JsonProperty("publishedOn")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public org.joda.time.LocalDate getPublishedOn() {
        return publishedOn;
    }

    public Book setPublishedOn(final org.joda.time.LocalDate value) {
        if (value == null)
            throw new IllegalArgumentException(
                    "Property \"publishedOn\" cannot be null!");
        this.publishedOn = value;

        return this;
    }

    private int pages;

    @JsonProperty("pages")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public int getPages() {
        return pages;
    }

    public Book setPages(final int value) {
        this.pages = value;

        return this;
    }
}
