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
import org.codetrack.domain.data.identify.Source;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Source data
 *
 * @author josecmoj at 04/07/15.
 * @see Source
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface SourceRepository {

    /**
     * Save the Source instance
     *
     * @param source instance to be saved
     * @return the Source instance saved
     * @throws CanNotSaveData if is not possible save Source instance
     */
    Source save(Source source) throws CanNotSaveData;

    /**
     * Remove an Source instance from graph
     *
     * @param source instance to be removed
     * @return the removed Source instance
     * @throws CanNotRemoveData if is not possible to remove Source instance
     */
    Source remove(Source source) throws CanNotRemoveData;

    /**
     * Find an Source by Id
     *
     * @param id to be find
     * @return the founded Source instance
     * @throws CanNotFoundData if not found an Source instance
     */
    Source findById(String id) throws CanNotFoundData;

    /**
     * Find an Source by Name (exact)
     *
     * @param name to be find
     * @return the founded Source instance
     * @throws CanNotFoundData if not found an Source instance
     */
    Source findByName(String name) throws CanNotFoundData;

    /**
     * Get all Source instances
     *
     * @return list of Sources
     */
    List<Source> allSources() throws CanNotFoundData;

}