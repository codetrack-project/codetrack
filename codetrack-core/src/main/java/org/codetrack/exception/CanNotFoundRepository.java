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

package org.codetrack.exception;

/**
 * @author josecmoj at 16/07/15.
 */
public class CanNotFoundRepository extends RuntimeException {

    public CanNotFoundRepository() {
        super();
    }

    public CanNotFoundRepository(Class itemClass) {
        String message = "Can not found repository to item " + itemClass.getSimpleName() + " class";
    }

    public CanNotFoundRepository(String message) {
        super(message);
    }

    public CanNotFoundRepository(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotFoundRepository(Throwable cause) {
        super(cause);
    }

    protected CanNotFoundRepository(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
