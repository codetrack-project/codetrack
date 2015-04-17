package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * Created by josecmoj on 16/04/15.
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Documented
public @interface Cases {

    Case[] cases();
}
