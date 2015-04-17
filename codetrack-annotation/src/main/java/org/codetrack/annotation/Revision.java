package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * Created by josecmoj on 14/04/15.
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Documented
public @interface Revision {

    String id();

    String date();

    String description();

    Author author();
}
