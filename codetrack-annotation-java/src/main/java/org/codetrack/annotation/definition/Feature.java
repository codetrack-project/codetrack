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

package org.codetrack.annotation.definition;

import org.codetrack.annotation.temporal.Cycle;
import org.codetrack.annotation.temporal.Iteration;
import org.codetrack.annotation.temporal.Sprint;

import java.lang.annotation.*;

/**
 * Indicate an feature in the software
 * <p/>
 *
 * @author josecmoj at 22/05/15.
 * @see UseCase
 * @see Cycle
 * @see Iteration
 * @see Sprint
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Feature {

    /**
     * Feature id
     *
     * @return String id
     */
    String id();

    /**
     * Feature description
     *
     * @return String feature description
     */
    String description() default "";

    /**
     * List of revisions
     *
     * @return array of {@link Revision}
     */
    Revision[] revisions() default {};

    /**
     * Optional UseCase
     *
     * @return UseCase
     */
    UseCase usecase() default @UseCase(id = "none");

    /**
     * Optional Story
     *
     * @return Story
     */
    Story story() default @Story(id = "none");
}
