package org.codetrack.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Fixes {

	Fix[] fixes();
}
