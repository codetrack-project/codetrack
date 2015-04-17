package org.codetrack.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD})
@Documented
public @interface Rule {

    String id();

	String caseid();

}
