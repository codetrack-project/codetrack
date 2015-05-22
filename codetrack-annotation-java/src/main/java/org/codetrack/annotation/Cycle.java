package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * Indicate the cycle development phase to answer the "when code is...?" question
 * <p/>
 * @author josecmoj at 20/05/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Cycle {

    /**
     * Cycle id
     *
     * @return String cycle id
     */
    String id();

    /**
     * Cycle description option
     *
     * @return String description
     */
    String description() default "";
}
