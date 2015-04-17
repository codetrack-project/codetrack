package org.codetrack.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Fix {

    /**
     * Tipo do erro
     *
     * @return Type tipo do erro
     */
    FixType type();

    /**
     * Identificador do erro
     *
     * @return String - identificador do erro
     */
	String id();

}
