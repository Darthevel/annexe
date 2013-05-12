package com.ehret.jug.facade;

import com.ehret.jug.domain.Framework;
import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;
import static org.fest.assertions.Assertions.*;


/**
 * Objet permettant de faciliter le controle d'un objet Framework
 */
public class FrameworkAssert extends GenericAssert<FrameworkAssert, Framework> {

    /**
     * Creates a new <code>{@link org.fest.assertions.GenericAssert}</code>.
     *
     * @param actual   the actual value to verify.
     */
    protected FrameworkAssert(Framework actual) {
        super(FrameworkAssert.class, actual);
    }

    /**
     * an entry point for SynHistoTrainAssert
     */
    public static FrameworkAssert assertThat(Framework actual) {
        return new FrameworkAssert(actual);
    }

    /**
     * Verifies that the actual {@code Long} is equal to the given one.
     * @return this assertion object.
     * @throws AssertionError if the actual {@code Long} is not equal to the given one.
     */
    public FrameworkAssert hasUniqueKey() {
        isNotNull();
        Assertions.assertThat(actual.getName()).overridingErrorMessage("Name is required").isNotNull();
        Assertions.assertThat(actual.getVersion()).overridingErrorMessage("Version is required").isNotNull();
        Assertions.assertThat(actual.getLanguage()).overridingErrorMessage("Language is required").isNotNull();
        return this;
    }
}
