package com.dslplatform.examples.android.AndroidLibrary.repositories;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;

public class BookRepository
        extends
        ClientPersistableRepository<com.dslplatform.examples.android.AndroidLibrary.Book> {
    public BookRepository(
            final ServiceLocator locator) {
        super(com.dslplatform.examples.android.AndroidLibrary.Book.class,
                locator);
    }
}
