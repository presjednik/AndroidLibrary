package com.dslplatform.examples.android.AndroidLibrary;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;
import com.fasterxml.jackson.annotation.*;

public class Review implements java.io.Serializable {
    public Review() {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.rating = 0;
        this.comment = "";
        this.reviewdBy = "";
        this.Bookisbn = "";
        this.Index = 0;
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
        final Review other = (Review) obj;

        return URI != null && URI.equals(other.URI);
    }

    @Override
    public String toString() {
        return URI != null ? "Review(" + URI + ')' : "new Review("
                + super.hashCode() + ')';
    }

    private static final long serialVersionUID = 0x0097000a;

    public Review(
            final int rating,
            final String comment,
            final String reviewdBy) {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        setRating(rating);
        setComment(comment);
        setReviewdBy(reviewdBy);
    }

    @JsonCreator
    private Review(
            @JacksonInject("_serviceLocator") final ServiceLocator _serviceLocator,
            @JsonProperty("URI") final String URI,
            @JsonProperty("rating") final int rating,
            @JsonProperty("comment") final String comment,
            @JsonProperty("reviewdBy") final String reviewdBy,
            @JsonProperty("Bookisbn") final String Bookisbn,
            @JsonProperty("Index") final int Index) {
        this._serviceLocator = _serviceLocator;
        this._domainProxy = _serviceLocator.resolve(DomainProxy.class);
        this._crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.URI = URI;
        this.rating = rating;
        this.comment = comment == null ? "" : comment;
        this.reviewdBy = reviewdBy == null ? "" : reviewdBy;
        this.Bookisbn = Bookisbn == null ? "" : Bookisbn;
        this.Index = Index;
    }

    private int rating;

    @JsonProperty("rating")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public int getRating() {
        return rating;
    }

    public Review setRating(final int value) {
        this.rating = value;

        return this;
    }

    private String comment;

    @JsonProperty("comment")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getComment() {
        return comment;
    }

    public Review setComment(final String value) {
        if (value == null)
            throw new IllegalArgumentException(
                    "Property \"comment\" cannot be null!");
        this.comment = value;

        return this;
    }

    private String reviewdBy;

    @JsonProperty("reviewdBy")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getReviewdBy() {
        return reviewdBy;
    }

    public Review setReviewdBy(final String value) {
        if (value == null)
            throw new IllegalArgumentException(
                    "Property \"reviewdBy\" cannot be null!");
        this.reviewdBy = value;

        return this;
    }

    private String Bookisbn;

    @JsonProperty("Bookisbn")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getBookisbn() {
        return Bookisbn;
    }

    public Review setBookisbn(final String value) {
        if (value == null)
            throw new IllegalArgumentException(
                    "Property \"Bookisbn\" cannot be null!");
        com.dslplatform.examples.android.Guards.checkLength(value, 14);
        this.Bookisbn = value;

        return this;
    }

    private int Index;

    @JsonProperty("Index")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public int getIndex() {
        return Index;
    }

    public Review setIndex(final int value) {
        this.Index = value;

        return this;
    }
}
