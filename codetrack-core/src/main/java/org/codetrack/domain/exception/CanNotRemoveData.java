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

package org.codetrack.domain.exception;

/**
 * @author josecmoj at 30/06/15.
 */
public class CanNotRemoveData extends RuntimeException {

    public CanNotRemoveData() {
        super();
    }

    public CanNotRemoveData(String message) {
        super(message);
    }

    public CanNotRemoveData(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotRemoveData(Throwable cause) {
        super(cause);
    }

    protected CanNotRemoveData(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
