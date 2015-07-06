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

package org.codetrack.domain.repository.fixing;

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.fixing.Fix;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Fix data
 *
 * @author josecmoj at 04/07/15.
 * @see Fix
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface FixRepository {

    /**
     * Save the Fix instance
     *
     * @param fix instance to be saved
     * @return the Fix instance saved
     * @throws CanNotSaveData if is not possible save Fix instance
     */
    Fix save(Fix fix) throws CanNotSaveData;

    /**
     * Remove an Fix instance from graph
     *
     * @param fix instance to be removed
     * @return the removed Fix instance
     * @throws CanNotRemoveData if is not possible to remove Fix instance
     */
    Fix remove(Fix fix) throws CanNotRemoveData;

    /**
     * Find an Fix by Id
     *
     * @param id to be find
     * @return the founded Fix instance
     * @throws CanNotFoundData if not found an Fix instance
     */
    Fix findById(String id) throws CanNotFoundData;

    /**
     * Find an Fix by Name (exact)
     *
     * @param name to be find
     * @return the founded Fix instance
     * @throws CanNotFoundData if not found an Fix instance
     */
    Fix findByName(String name) throws CanNotFoundData;

    /**
     * Get all Fix instances
     *
     * @return list of Fixs
     */
    List<Fix> allFixs() throws CanNotFoundData;

}