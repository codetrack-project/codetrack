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

import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.definition.Feature;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Feature data
 *
 * @author josecmoj at 04/07/15.
 * @see Feature
 */
@Product(id = "codetrack-core")
@org.codetrack.annotation.definition.Feature(id = "#4-DATABASE")
public interface FeatureRepository {

    /**
     * Save the Feature instance
     *
     * @param feature instance to be saved
     * @return the Feature instance saved
     * @throws CanNotSaveData if is not possible save Feature instance
     */
    Feature save(Feature feature) throws CanNotSaveData;

    /**
     * Remove an Feature instance from graph
     *
     * @param feature instance to be removed
     * @return the removed Feature instance
     * @throws CanNotRemoveData if is not possible to remove Feature instance
     */
    Feature remove(Feature feature) throws CanNotRemoveData;

    /**
     * Find an Feature by Id
     *
     * @param id to be find
     * @return the founded Feature instance
     * @throws CanNotFoundData if not found an Feature instance
     */
    Feature findById(String id) throws CanNotFoundData;

    /**
     * Find an Feature by Name (exact)
     *
     * @param name to be find
     * @return the founded Feature instance
     * @throws CanNotFoundData if not found an Feature instance
     */
    Feature findByName(String name) throws CanNotFoundData;

    /**
     * Get all Feature instances
     *
     * @return list of Features
     */
    List<Feature> allFeatures() throws CanNotFoundData;

}