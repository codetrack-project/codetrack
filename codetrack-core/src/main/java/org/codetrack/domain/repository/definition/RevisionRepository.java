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
import org.codetrack.domain.data.definition.Revision;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Revision data
 *
 * @author josecmoj at 04/07/15.
 * @see Revision
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface RevisionRepository {

    /**
     * Save the Revision instance
     *
     * @param revision instance to be saved
     * @return the Revision instance saved
     * @throws CanNotSaveData if is not possible save Revision instance
     */
    Revision save(Revision revision) throws CanNotSaveData;

    /**
     * Remove an Revision instance from graph
     *
     * @param revision instance to be removed
     * @return the removed Revision instance
     * @throws CanNotRemoveData if is not possible to remove Revision instance
     */
    Revision remove(Revision revision) throws CanNotRemoveData;

    /**
     * Find an Revision by Id
     *
     * @param id to be find
     * @return the founded Revision instance
     * @throws CanNotFoundData if not found an Revision instance
     */
    Revision findById(String id) throws CanNotFoundData;

    /**
     * Find an Revision by Name (exact)
     *
     * @param name to be find
     * @return the founded Revision instance
     * @throws CanNotFoundData if not found an Revision instance
     */
    Revision findByName(String name) throws CanNotFoundData;

    /**
     * Get all Revision instances
     *
     * @return list of Revisions
     */
    List<Revision> allRevisions() throws CanNotFoundData;

}