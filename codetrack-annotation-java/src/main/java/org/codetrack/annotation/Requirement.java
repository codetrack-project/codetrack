package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * Indicate a requirement on the software
 * <p/>
 *
 * @author josecmoj at 22/05/15.
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
     * UseCase
     *
     * @return UseCase
     */
    UseCase usecase() default @UseCase(id = "none");


}
