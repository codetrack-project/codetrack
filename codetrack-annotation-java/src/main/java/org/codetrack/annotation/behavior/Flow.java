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

package org.codetrack.annotation.behavior;

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.definition.Issue;
import org.codetrack.annotation.definition.Story;
import org.codetrack.annotation.definition.UseCase;

import java.lang.annotation.*;

/**
 * The flow annotation.
 * Annotate code with an flow condition.
 * <p/>
 *
 * @author josecmoj at 30/05/15.
 * @see Condition
 * @see Conditions
 * @see UseCase
 * @see Story
 * @see Feature
 * @see Issue
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Flow {

    /**
     * Flow id
     *
     * @return String flow id
     */
    String id();

    /**
     * Flow name
     *
     * @return String flow name
     */
    String name() default "";

    /**
     * Flow condition
     *
     * @return Condition of Flow
     * @see Condition
     */
    Condition condition() default @Condition(id = "none");

    /**
     * Alternative Flow condition group
     *
     * @return Conditions of Flow
     * @see Conditions
     */
    Conditions conditions() default @Conditions({});

    /**
     * UseCase
     *
     * @return UseCase
     */
    UseCase usecase() default @UseCase(id = "none");

    /**
     * Story
     *
     * @return Story
     */
    Story story() default @Story(id = "none");

    /**
     * Feature
     *
     * @return Feature
     */
    Feature feature() default @Feature(id = "none");

    /**
     * Issue
     *
     * @return Issue
     */
    Issue issue() default @Issue(id = "none");
}
