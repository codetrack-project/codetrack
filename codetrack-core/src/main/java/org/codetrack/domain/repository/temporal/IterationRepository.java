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
import org.codetrack.domain.data.temporal.Iteration;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Iteration data
 *
 * @author josecmoj at 30/06/15.
 * @see Iteration
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface IterationRepository {

    /**
     * Save the Iteration instance
     *
     * @param iteration instance to be saved
     * @return the Iteration instance saved
     * @throws CanNotSaveData if is not possible save Iteration instance
     */
    Iteration save(Iteration iteration) throws CanNotSaveData;

    /**
     * Remove an Iteration instance from graph
     *
     * @param iteration instance to be removed
     * @return the removed Iteration instance
     * @throws CanNotRemoveData if is not possible to remove Iteration instance
     */
    Iteration remove(Iteration iteration) throws CanNotRemoveData;

    /**
     * Find an Iteration by Id
     *
     * @param id to be find
     * @return the founded Iteration instance
     * @throws CanNotFoundData if not found an Iteration instance
     */
    Iteration findById(String id) throws CanNotFoundData;

    /**
     * Find an Iteration by Name (exact)
     *
     * @param name to be find
     * @return the founded Iteration instance
     * @throws CanNotFoundData if not found an Iteration instance
     */
    Iteration findByName(String name) throws CanNotFoundData;

    /**
     * Get all Iteration instances
     *
     * @return list of Iterations
     */
    List<Iteration> allIterations() throws CanNotFoundData;

}