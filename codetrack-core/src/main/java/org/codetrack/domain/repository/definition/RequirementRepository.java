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

package org.codetrack.domain.repository.definition;

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.definition.Requirement;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Requirement data
 *
 * @author josecmoj at 04/07/15.
 * @see Requirement
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface RequirementRepository {

    /**
     * Save the Requirement instance
     *
     * @param requirement instance to be saved
     * @return the Requirement instance saved
     * @throws CanNotSaveData if is not possible save Requirement instance
     */
    Requirement save(Requirement requirement) throws CanNotSaveData;

    /**
     * Remove an Requirement instance from graph
     *
     * @param requirement instance to be removed
     * @return the removed Requirement instance
     * @throws CanNotRemoveData if is not possible to remove Requirement instance
     */
    Requirement remove(Requirement requirement) throws CanNotRemoveData;

    /**
     * Find an Requirement by Id
     *
     * @param id to be find
     * @return the founded Requirement instance
     * @throws CanNotFoundData if not found an Requirement instance
     */
    Requirement findById(String id) throws CanNotFoundData;

    /**
     * Find an Requirement by Name (exact)
     *
     * @param name to be find
     * @return the founded Requirement instance
     * @throws CanNotFoundData if not found an Requirement instance
     */
    Requirement findByName(String name) throws CanNotFoundData;

    /**
     * Get all Requirement instances
     *
     * @return list of Requirements
     */
    List<Requirement> allRequirements() throws CanNotFoundData;

}