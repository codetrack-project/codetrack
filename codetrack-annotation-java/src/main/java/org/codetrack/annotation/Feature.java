package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * Indicate an feature in the software
 * <p/>
 *
 * @author josecmoj at 22/05/15.
 * @see UseCase
 * @see Cycle
 * @see Iteration
 * @see Sprint
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Feature {

    /**
     * Feature id
     *
     * @return String id
     */
    String id();

    /**
     * Feature description
     *
     * @return String feature description
     */
    String description() default "";

    /**
     * Optional cycle
     *
     * @return Cycle
     * @see Cycle
     */
    Cycle cycle() default @Cycle(id = "none");

    /**
     * Optional iteration
     *
     * @return Iteration
     * @see Iteration
     */
    Iteration iteration() default @Iteration(id = "none");

    /**
     * Optional sprint
     *
     * @return Sprint
     * @see Sprint
     */
    Sprint sprint() default @Sprint(id = "none");

    /**
     * Optional UseCase
     *
     * @return UseCase
     */
    UseCase usecase() default @UseCase(id = "none");

    /**
     * Optional Story
     *
     * @return Story
     */
    Story story() default @Story(id = "none");
}
