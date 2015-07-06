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

package org.codetrack.domain.repository.identify;

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.identify.Reference;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Reference data
 *
 * @author josecmoj at 04/07/15.
 * @see Reference
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface ReferenceRepository {

    /**
     * Save the Reference instance
     *
     * @param reference instance to be saved
     * @return the Reference instance saved
     * @throws CanNotSaveData if is not possible save Reference instance
     */
    Reference save(Reference reference) throws CanNotSaveData;

    /**
     * Remove an Reference instance from graph
     *
     * @param reference instance to be removed
     * @return the removed Reference instance
     * @throws CanNotRemoveData if is not possible to remove Reference instance
     */
    Reference remove(Reference reference) throws CanNotRemoveData;

    /**
     * Find an Reference by Id
     *
     * @param id to be find
     * @return the founded Reference instance
     * @throws CanNotFoundData if not found an Reference instance
     */
    Reference findById(String id) throws CanNotFoundData;

    /**
     * Find an Reference by Name (exact)
     *
     * @param name to be find
     * @return the founded Reference instance
     * @throws CanNotFoundData if not found an Reference instance
     */
    Reference findByName(String name) throws CanNotFoundData;

    /**
     * Get all Reference instances
     *
     * @return list of References
     */
    List<Reference> allReferences() throws CanNotFoundData;

}