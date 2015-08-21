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
 * The step annotation
 * Annotate an step of UseCase, Story, Featue or Issue
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
public @interface Step {

    /**
     * Step id
     *
     * @return String id of Step
     */
    String id();

    /**
     * Step name
     *
     * @return String name of Step
     */
    String name() default "";

    /**
     * Step description
     *
     * @return String description of Step
     */
    String description() default "";

    /**
     * Step condition
     *
     * @return Condition of Step
     * @see Condition
     */
    Condition condition() default @Condition(id = "none");

    /**
     * Alternative Step condition group
     *
     * @return Conditions of Step
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
