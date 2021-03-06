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

import org.codetrack.annotation.identify.Author;

import java.lang.annotation.*;

/**
 * The annotation used to mark an revision
 * <p/>
 * @author josecmoj on 14/04/15.
 * @see Author
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Revision {

    /**
     * Revision id
     *
     * @return String id
     */
    String id();

    /**
     * Date of revision
     * Preferences to use the YYYY/MM/DD date format
     * @return String date of revision
     */
    String date();

    /**
     * Description of revision
     *
     * @return String revision text
     */
    String description() default "";

    /**
     * Author of revision
     *
     * @return revision {@link Author}
     */
    Author author();
}
