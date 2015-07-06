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

package org.codetrack.domain.repository;

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.Project;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * Repository interface defines access to Project data
 *
 * @author josecmoj at 04/07/15.
 * @see Project
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface ProjectRepository {

    /**
     * Save the Project instance
     *
     * @param project instance to be saved
     * @return the Project instance saved
     * @throws CanNotSaveData if is not possible save Project instance
     */
    Project save(Project project) throws CanNotSaveData;

    /**
     * Remove an Project instance from graph
     *
     * @param project instance to be removed
     * @return the removed Project instance
     * @throws CanNotRemoveData if is not possible to remove Project instance
     */
    Project remove(Project project) throws CanNotRemoveData;

    /**
     * Find an Project by Id
     *
     * @param id to be find
     * @return the founded Project instance
     * @throws CanNotFoundData if not found an Project instance
     */
    Project findById(String id) throws CanNotFoundData;

    /**
     * Find an Project by Name (exact)
     *
     * @param name to be find
     * @return the founded Project instance
     * @throws CanNotFoundData if not found an Project instance
     */
    Project findByName(String name) throws CanNotFoundData;

    /**
     * Get all Project instances
     *
     * @return list of Projects
     */
    List<Project> allProjects() throws CanNotFoundData;

}