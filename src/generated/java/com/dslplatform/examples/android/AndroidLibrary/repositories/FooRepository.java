package com.dslplatform.examples.android.AndroidLibrary.repositories;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;

public class FooRepository
        extends
        ClientPersistableRepository<com.dslplatform.examples.android.AndroidLibrary.Foo> {
    public FooRepository(
            final ServiceLocator locator) {
        super(com.dslplatform.examples.android.AndroidLibrary.Foo.class,
                locator);
    }
}
