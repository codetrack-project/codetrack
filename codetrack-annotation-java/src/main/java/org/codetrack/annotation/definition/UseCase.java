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
 * The use case annotation.
 * This annotation indicate the use case definition information of an type
 * <p/>
 * @author josecmoj on 14/04/15.
 * @see Revision
 * @see UseCase
 * @see Cycle
 * @see Iteration
 * @see Sprint
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface UseCase {

    /**
     * UseCase id
     *
     * @return String case id
     */
    String id();

    /**
     * Revision list
     *
     * @return array of Revision
     * @see Revision
     */
    Revision[] revisions() default {};

    /**
     * Feature
     *
     * @return feature
     */
    Feature feature() default @Feature(id = "none");

    /**
     * Optional cycle
     *
     * @return Cycle
     * @see Cycle
     */
    Cycle cycle() default @Cycle(id = "none");

    /**
     * Optional iteration
     *
     * @return Iteration
     * @see Iteration
     */
    Iteration iteration() default @Iteration(id = "none");

    /**
     * Optional sprint
     *
     * @return Sprint
     * @see Sprint
     */
    Sprint sprint() default @Sprint(id = "none");


}
