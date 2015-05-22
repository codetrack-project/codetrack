package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * Reference to get more information about annotate type or method
 * <p/>
 *
 * @author josecmoj at 21/05/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.CONSTRUCTOR,
        ElementType.LOCAL_VARIABLE})
@Documented
public @interface Reference {

    /**
     * Reference id
     *
     * @return String reference id
     */
    String id();

    /**
     * Reference value
     *
     * @return String values
     */
    String value() default "";

    /**
     * Reference values
     *
     * @return array string values
     */
    String[] values() default {};
}
