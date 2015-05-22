package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * Group of requirements
 * <p/>
 *
 * @author josecmoj at 22/05/15.
 * @see Requirement
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,
        ElementType.METHOD,
        ElementType.FIELD})
@Documented
public @interface Requirements {

    /**
     * Group of requirements
     *
     * @return array of {@link Requirement}
     */
    Requirement[] value();

}
