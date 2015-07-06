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
import org.codetrack.domain.data.definition.Issue;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Issue data
 *
 * @author josecmoj at 04/07/15.
 * @see Issue
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface IssueRepository {

    /**
     * Save the Issue instance
     *
     * @param issue instance to be saved
     * @return the Issue instance saved
     * @throws CanNotSaveData if is not possible save Issue instance
     */
    Issue save(Issue issue) throws CanNotSaveData;

    /**
     * Remove an Issue instance from graph
     *
     * @param issue instance to be removed
     * @return the removed Issue instance
     * @throws CanNotRemoveData if is not possible to remove Issue instance
     */
    Issue remove(Issue issue) throws CanNotRemoveData;

    /**
     * Find an Issue by Id
     *
     * @param id to be find
     * @return the founded Issue instance
     * @throws CanNotFoundData if not found an Issue instance
     */
    Issue findById(String id) throws CanNotFoundData;

    /**
     * Find an Issue by Name (exact)
     *
     * @param name to be find
     * @return the founded Issue instance
     * @throws CanNotFoundData if not found an Issue instance
     */
    Issue findByName(String name) throws CanNotFoundData;

    /**
     * Get all Issue instances
     *
     * @return list of Issues
     */
    List<Issue> allIssues() throws CanNotFoundData;

}