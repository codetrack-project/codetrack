package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * List of References
 * <p/>
 *
 * @author josecmoj at 21/05/15.
 * @see Reference
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.CONSTRUCTOR,
        ElementType.LOCAL_VARIABLE})
@Documented
public @interface References {

    /**
     * Reference list
     *
     * @return array of {@link Reference}
     */
    Reference[] value();
}
