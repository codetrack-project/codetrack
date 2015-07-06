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

package org.codetrack.domain.repository.behavior;

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.behavior.Flow;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Flow data
 *
 * @author josecmoj at 04/07/15.
 * @see Flow
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface FlowRepository {

    /**
     * Save the Flow instance
     *
     * @param flow instance to be saved
     * @return the Flow instance saved
     * @throws CanNotSaveData if is not possible save Flow instance
     */
    Flow save(Flow flow) throws CanNotSaveData;

    /**
     * Remove an Flow instance from graph
     *
     * @param flow instance to be removed
     * @return the removed Flow instance
     * @throws CanNotRemoveData if is not possible to remove Flow instance
     */
    Flow remove(Flow flow) throws CanNotRemoveData;

    /**
     * Find an Flow by Id
     *
     * @param id to be find
     * @return the founded Flow instance
     * @throws CanNotFoundData if not found an Flow instance
     */
    Flow findById(String id) throws CanNotFoundData;

    /**
     * Find an Flow by Name (exact)
     *
     * @param name to be find
     * @return the founded Flow instance
     * @throws CanNotFoundData if not found an Flow instance
     */
    Flow findByName(String name) throws CanNotFoundData;

    /**
     * Get all Flow instances
     *
     * @return list of Flows
     */
    List<Flow> allFlows() throws CanNotFoundData;

}