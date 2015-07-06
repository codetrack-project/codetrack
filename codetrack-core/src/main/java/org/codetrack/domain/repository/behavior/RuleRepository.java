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
import org.codetrack.domain.data.behavior.Rule;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Rule data
 *
 * @author josecmoj at 04/07/15.
 * @see Rule
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface RuleRepository {

    /**
     * Save the Rule instance
     *
     * @param rule instance to be saved
     * @return the Rule instance saved
     * @throws CanNotSaveData if is not possible save Rule instance
     */
    Rule save(Rule rule) throws CanNotSaveData;

    /**
     * Remove an Rule instance from graph
     *
     * @param rule instance to be removed
     * @return the removed Rule instance
     * @throws CanNotRemoveData if is not possible to remove Rule instance
     */
    Rule remove(Rule rule) throws CanNotRemoveData;

    /**
     * Find an Rule by Id
     *
     * @param id to be find
     * @return the founded Rule instance
     * @throws CanNotFoundData if not found an Rule instance
     */
    Rule findById(String id) throws CanNotFoundData;

    /**
     * Find an Rule by Name (exact)
     *
     * @param name to be find
     * @return the founded Rule instance
     * @throws CanNotFoundData if not found an Rule instance
     */
    Rule findByName(String name) throws CanNotFoundData;

    /**
     * Get all Rule instances
     *
     * @return list of Rules
     */
    List<Rule> allRules() throws CanNotFoundData;

}