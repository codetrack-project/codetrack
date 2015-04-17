package org.codetrack.annotation;

import java.lang.annotation.*;

/**
 * @author josecmoj josecmoj@gmail.com
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Artifacts {

    /**
     * Lista de artefatos relacionados ao código
     *
     * @return
     */
    String[] artifacts();

}
