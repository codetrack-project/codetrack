package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * Indicate a requirement on the software
 * <p/>
 *
 * @author josecmoj at 22/05/15.
 * @see UseCase
 * @see Story
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,
        ElementType.METHOD,
        ElementType.FIELD})
@Documented
public @interface Requirement {

    /**
     * Requirement id
     *
     * @return String requirement id
     */
    String id();

    /**
     * Requirement description
     *
     * @return String requirement description
     */
    String description() default "";

    /**
     * UseCase
     *
     * @return UseCase
     */
    UseCase usecase() default @UseCase(id = "none");

    /**
     * Story
     *
     * @return Story
     */
    Story story() default @Story(id = "none");


}
