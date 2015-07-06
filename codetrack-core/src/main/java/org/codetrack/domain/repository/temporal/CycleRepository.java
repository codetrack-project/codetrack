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
import org.codetrack.domain.data.temporal.Cycle;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Cycle data
 *
 * @author josecmoj at 30/06/15.
 * @see Cycle
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface CycleRepository {

    /**
     * Save the Cycle instance
     *
     * @param cycle instance to be saved
     * @return the Cycle instance saved
     * @throws CanNotSaveData if is not possible save Cycle instance
     */
    Cycle save(Cycle cycle) throws CanNotSaveData;

    /**
     * Remove an Cycle instance from graph
     *
     * @param cycle instance to be removed
     * @return the removed Cycle instance
     * @throws CanNotRemoveData if is not possible to remove Cycle instance
     */
    Cycle remove(Cycle cycle) throws CanNotRemoveData;

    /**
     * Find an Cycle by Id
     *
     * @param id to be find
     * @return the founded Cycle instance
     * @throws CanNotFoundData if not found an Cycle instance
     */
    Cycle findById(String id) throws CanNotFoundData;

    /**
     * Find an Cycle by Name (exact)
     *
     * @param name to be find
     * @return the founded Cycle instance
     * @throws CanNotFoundData if not found an Cycle instance
     */
    Cycle findByName(String name) throws CanNotFoundData;

    /**
     * Get all Cycle instances
     *
     * @return list of Cycles
     */
    List<Cycle> allCycles() throws CanNotFoundData;

}