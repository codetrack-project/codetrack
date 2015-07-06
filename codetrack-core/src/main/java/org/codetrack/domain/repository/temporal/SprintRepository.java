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

package org.codetrack.domain.repository.temporal;

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.temporal.Sprint;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Sprint data
 *
 * @author josecmoj at 30/06/15.
 * @see Sprint
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface SprintRepository {

    /**
     * Save the Sprint instance
     *
     * @param sprint instance to be saved
     * @return the Sprint instance saved
     * @throws CanNotSaveData if is not possible save Sprint instance
     */
    Sprint save(Sprint sprint) throws CanNotSaveData;

    /**
     * Remove an Sprint instance from graph
     * @param sprint instance to be removed
     * @return the removed Sprint instance
     * @throws CanNotRemoveData if is not possible to remove Sprint instance
     */
    Sprint remove(Sprint sprint) throws CanNotRemoveData;

    /**
     * Find an Sprint by Id
     * @param id to be find
     * @return the founded Sprint instance
     * @throws CanNotFoundData if not found an Sprint instance
     */
    Sprint findById(String id) throws CanNotFoundData;

    /**
     * Find an Sprint by Name (exact)
     * @param name to be find
     * @return the founded Sprint instance
     * @throws CanNotFoundData
     */
    Sprint findByName(String name) throws CanNotFoundData;

    /**
     * Get all Sprint instances
     * @return list of Sprints
     */
    List<Sprint> allSprints() throws CanNotFoundData;



}
