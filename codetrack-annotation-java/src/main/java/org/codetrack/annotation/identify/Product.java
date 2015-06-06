/*
 *  Copyright 2015 the original author or authors members of codetrack.org
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.codetrack.annotation.identify;

import org.codetrack.annotation.definition.*;

import java.lang.annotation.*;

/**
 * The annotation used to indicate an product
 * <p/>
 * @author josecmoj on 14/04/15.
 * @see UseCases
 * @see Stories
 * @see Features
 * @see Requirements
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Product {

    /**
     * Product id
     * @return String com o id da solução
     */
    String id();

    /**
     * Requirements of Product
     *
     * @return {@link Requirements} with array of {@link Requirement}
     */
    Requirements requirements() default @Requirements({});

    /**
     * Use cases of Product
     *
     * @return {@link UseCases} with array of {@link UseCase}
     */
    UseCases usecases() default @UseCases({});

    /**
     * Stories of Product
     *
     * @return {@link Stories} with array of {@link Story}
     */
    Stories stories() default @Stories({});

    /**
     * Features of Product
     *
     * @return {@link Features} with array of {@link Feature}
     */
    Features features() default @Features({});
}
