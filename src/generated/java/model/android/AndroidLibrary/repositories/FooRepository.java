package model.android.AndroidLibrary.repositories;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;

public class FooRepository extends
        ClientPersistableRepository<model.android.AndroidLibrary.Foo> {
    public FooRepository(
            final ServiceLocator locator) {
        super(model.android.AndroidLibrary.Foo.class, locator);
    }
}
