package com.dslplatform.examples.android.AndroidLibrary;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;
import com.fasterxml.jackson.annotation.*;

public final class AuthorsBookCount implements java.io.Serializable {
    @JsonCreator
    public AuthorsBookCount(
            @JsonProperty("author") final String author,
            @JsonProperty("BookCount") final long BookCount) {
        this();
        if (author != null) setAuthor(author);
        setBookCount(BookCount);
    }

    private static final long serialVersionUID = 0x0097000a;

    public AuthorsBookCount() {
        this.author = "";
    }

    private String author;

    @JsonProperty("author")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getAuthor() {
        return author;
    }

    public AuthorsBookCount setAuthor(final String value) {
        if (value == null)
            throw new IllegalArgumentException(
                    "Property \"author\" cannot be null!");
        this.author = value;

        return this;
    }

    private long BookCount;

    @JsonProperty("BookCount")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public long getBookCount() {
        return BookCount;
    }

    public AuthorsBookCount setBookCount(final long value) {
        this.BookCount = value;

        return this;
    }

    public AuthorsBookCount populate() throws java.io.IOException {
        return populate(Bootstrap.getLocator());
    }

    public AuthorsBookCount populate(final ServiceLocator locator)
            throws java.io.IOException {
        final ReportingProxy proxy = (locator != null ? locator : Bootstrap
                .getLocator()).resolve(ReportingProxy.class);
        final AuthorsBookCount result;
        try {
            result = proxy.populate(this).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
        this.BookCount = result.BookCount;
        return this;
    }
}
