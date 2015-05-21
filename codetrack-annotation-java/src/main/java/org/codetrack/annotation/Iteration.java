package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * Indicate the iteration phase to answer the "when code is...?" question
 *
 * @author josecmoj at 20/05/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Iteration {

    /**
     * Iteration id
     *
     * @return String iteration id
     */
    String id();

    /**
     * Iteration description option
     *
     * @return
     */
    String description() default "";

}
