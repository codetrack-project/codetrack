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
import org.codetrack.domain.data.identify.Path;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Path data
 *
 * @author josecmoj at 01/07/15.
 * @see Path
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface PathRepository {

    /**
     * Save the Path instance
     *
     * @param path instance to be saved
     * @return the Path instance saved
     * @throws CanNotSaveData if is not possible save Path instance
     */
    Path save(Path path) throws CanNotSaveData;

    /**
     * Remove an Path instance from graph
     *
     * @param path instance to be removed
     * @return the removed Path instance
     * @throws CanNotRemoveData if is not possible to remove Path instance
     */
    Path remove(Path path) throws CanNotRemoveData;

    /**
     * Find an Path by Id
     *
     * @param id to be find
     * @return the founded Path instance
     * @throws CanNotFoundData if not found an Path instance
     */
    Path findById(String id) throws CanNotFoundData;

    /**
     * Find an Path by Name (exact)
     *
     * @param name to be find
     * @return the founded Path instance
     * @throws CanNotFoundData if not found an Path instance
     */
    Path findByName(String name) throws CanNotFoundData;

    /**
     * Get all Path instances
     *
     * @return list of Paths
     */
    List<Path> allPaths() throws CanNotFoundData;

}