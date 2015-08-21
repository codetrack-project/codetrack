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

import java.lang.annotation.*;

/**
 * Reference to get more information about annotate type or method
 * <p/>
 *
 * @author josecmoj at 21/05/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.CONSTRUCTOR,
        ElementType.LOCAL_VARIABLE})
@Documented
public @interface Reference {

    /**
     * Reference id
     *
     * @return String reference id
     */
    String id();

    /**
     * Reference name
     *
     * @return String values
     */
    String name() default "";

    /**
     * Reference values
     *
     * @return array string values
     */
    String[] values() default {};
}
