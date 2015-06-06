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

import java.lang.annotation.*;

/**
 * The condition annotation.
 * Annotate code with an condition.
 * <p/>
 *
 * @author josecmoj at 06/06/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Condition {

    /**
     * Condition id
     *
     * @return String condition id
     */
    String id();

    /**
     * Condition name
     *
     * @return String condition name
     */
    String name() default "";

    /**
     * Condition expression
     *
     * @return String condition expression
     */
    String expression() default "";

    /**
     * Condition description
     *
     * @return String condition description
     */
    String description() default "";

}
