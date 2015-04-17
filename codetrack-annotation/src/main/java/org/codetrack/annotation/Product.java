package org.codetrack.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Documented
public @interface Product {

    /**
     * Identificador do produto atendido pela classe
     *
     * @return String com o id da solução
     */
	String id();

    /**
     * Use cases envolvidos no desenvolvimento do produto
     *
     * @return
     */
    Case[] cases();
}
