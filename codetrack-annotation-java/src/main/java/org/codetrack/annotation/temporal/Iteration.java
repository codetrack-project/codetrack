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

package org.codetrack.annotation.temporal;

import java.lang.annotation.*;

/**
 * Indicate the iteration phase to answer the "when code is...?" question
 * <p/>
 * @author josecmoj at 20/05/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE, ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Iteration {

    /**
     * Iteration id
     *
     * @return String iteration id
     */
    String id();

    /**
     * Iteration description option
     *
     * @return String description
     */
    String description() default "";

    /**
     * Iteration start date
     * Preferences to use the YYYY/MM/DD date format
     *
     * @return end date of Iteration
     */
    String startAt() default "";

    /**
     * Iteration end date
     * Preferences to use the YYYY/MM/DD date format
     *
     * @return end date of Iteration
     */
    String endAt() default "";

}
