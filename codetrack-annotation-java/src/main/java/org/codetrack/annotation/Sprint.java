package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * Identify an Sprint
 * </p>
 *
 * @author josecmoj at 22/05/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Sprint {

    /**
     * Sprint id
     *
     * @return String sprint id
     */
    String id();

    /**
     * Sprint description option
     *
     * @return String description
     */
    String description() default "";

}
