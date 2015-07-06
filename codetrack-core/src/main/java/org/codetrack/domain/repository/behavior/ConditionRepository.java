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
import org.codetrack.domain.data.behavior.Condition;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Condition data
 *
 * @author josecmoj at 04/07/15.
 * @see Condition
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface ConditionRepository {

    /**
     * Save the Condition instance
     *
     * @param condition instance to be saved
     * @return the Condition instance saved
     * @throws CanNotSaveData if is not possible save Condition instance
     */
    Condition save(Condition condition) throws CanNotSaveData;

    /**
     * Remove an Condition instance from graph
     *
     * @param condition instance to be removed
     * @return the removed Condition instance
     * @throws CanNotRemoveData if is not possible to remove Condition instance
     */
    Condition remove(Condition condition) throws CanNotRemoveData;

    /**
     * Find an Condition by Id
     *
     * @param id to be find
     * @return the founded Condition instance
     * @throws CanNotFoundData if not found an Condition instance
     */
    Condition findById(String id) throws CanNotFoundData;

    /**
     * Find an Condition by Name (exact)
     *
     * @param name to be find
     * @return the founded Condition instance
     * @throws CanNotFoundData if not found an Condition instance
     */
    Condition findByName(String name) throws CanNotFoundData;

    /**
     * Get all Condition instances
     *
     * @return list of Conditions
     */
    List<Condition> allConditions() throws CanNotFoundData;

}