/*
 * Copyright 2015 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.codetrack.database.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * Runtime codetrack database exception
 *
 * @author josecmoj at 21/04/15.
 */
public class DatabaseError extends NestedRuntimeException {

    /**
     * ERROR BASE code
     */
    final public static int DATABASE_ERROR_BASE = 1000;

    /**
     * Create database error code
     */
    final public static int DATABASE_CREATE_ERROR = DATABASE_ERROR_BASE + 1;

    /**
     * Register database error code
     */
    final public static int DATABASE_REGISTER_ERROR = DATABASE_CREATE_ERROR + 1;

    /**
     * Open database error code
     */
    final public static int DATABASE_OPEN_ERROR = DATABASE_REGISTER_ERROR + 1;

    /**
     * Open database.properties error code
     */
    final public static int DATABASE_OPEN_MAIN_CONFIG_ERROR = DATABASE_OPEN_ERROR + 1;

    /**
     * Open database properties error code
     */
    final public static int DATABASE_OPEN_CONFIG_ERROR = DATABASE_OPEN_MAIN_CONFIG_ERROR + 1;

    /**
     * Save data error code
     */
    public static final int DATABASE_SAVE_ERROR = DATABASE_OPEN_CONFIG_ERROR + 1;

    /**
     * Load database error code
     */
    public static final int DATABASE_LOAD_ERROR = DATABASE_SAVE_ERROR + 1;

    /**
     * Delete database error code
     */
    public static final int DATABASE_DELETE_ERROR = DATABASE_LOAD_ERROR + 1;

    /**
     * Delete database error code
     */
    public static final int DATABASE_PROJECT_NOT_FOUND = DATABASE_DELETE_ERROR + 1;


    /**
     * Error code in instance exception
     */
    private int code;

    public DatabaseError(String message, int code) {
        super(message);
        this.code = code;
    }

    public DatabaseError(String msg, Throwable cause, int code) {
        super(msg, cause);
        this.code = code;
    }
}
