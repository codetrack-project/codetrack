package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * Group of features
 * <p/>
 *
 * @author josecmoj at 22/05/15.
 * @see Feature
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Features {

    /**
     * Feature list
     *
     * @return {@link Feature}
     */
    Feature[] features();


}
