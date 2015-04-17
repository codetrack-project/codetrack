package org.codetrack.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Author {

    /**
     * Nome do autor
     *
     * @return String o nome do autor
     */
	String name();

    /**
     * Email do autor
     *
     * @return String o email do autor
     */
	String email();

}
