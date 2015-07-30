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
 * The rule annotation
 * Annotate code with an rule defined in UseCase, Story, Feature or Issue.
 * <p/>
 * @author josecmoj on 18/04/15.
 * @see Step
 * @see UseCase
 * @see Story
 * @see Feature
 * @see Issue
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Rule {

    /**
     * Rule id
     *
     * @return String rule id
     */
    String id();

    /**
     * Rule name
     *
     * @return String rule name
     */
    String name() default "";

    /**
     * Step execution of rule
     *
     * @return String step rule
     */
    Step step() default @Step(id = "none");

    /**
     * UseCase
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
