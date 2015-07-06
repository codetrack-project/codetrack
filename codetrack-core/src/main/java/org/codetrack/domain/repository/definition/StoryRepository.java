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
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.definition.Story;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Story data
 *
 * @author josecmoj at 04/07/15.
 * @see Story
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface StoryRepository {

    /**
     * Save the Story instance
     *
     * @param story instance to be saved
     * @return the Story instance saved
     * @throws CanNotSaveData if is not possible save Story instance
     */
    Story save(Story story) throws CanNotSaveData;

    /**
     * Remove an Story instance from graph
     *
     * @param story instance to be removed
     * @return the removed Story instance
     * @throws CanNotRemoveData if is not possible to remove Story instance
     */
    Story remove(Story story) throws CanNotRemoveData;

    /**
     * Find an Story by Id
     *
     * @param id to be find
     * @return the founded Story instance
     * @throws CanNotFoundData if not found an Story instance
     */
    Story findById(String id) throws CanNotFoundData;

    /**
     * Find an Story by Name (exact)
     *
     * @param name to be find
     * @return the founded Story instance
     * @throws CanNotFoundData if not found an Story instance
     */
    Story findByName(String name) throws CanNotFoundData;

    /**
     * Get all Story instances
     *
     * @return list of Storys
     */
    List<Story> allStorys() throws CanNotFoundData;

}