package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * Indicate the iteration phase to answer the "when code is...?" question
 * <p/>
 * @author josecmoj at 20/05/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Iteration {

    /**
     * Iteration id
     *
     * @return String iteration id
     */
    String value();

    /**
     * Iteration description option
     *
     * @return
     */
    String description() default "";

}
