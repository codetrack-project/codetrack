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
import org.codetrack.domain.data.identify.Author;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Author data
 *
 * @author josecmoj at 30/06/15.
 * @see Author
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface AuthorRepository {

    /**
     * Save the Author instance
     *
     * @param author instance to be saved
     * @return the Author instance saved
     * @throws CanNotSaveData if is not possible save Author instance
     */
    Author save(Author author) throws CanNotSaveData;

    /**
     * Remove an Author instance from graph
     *
     * @param author instance to be removed
     * @return the removed Author instance
     * @throws CanNotRemoveData if is not possible to remove Author instance
     */
    Author remove(Author author) throws CanNotRemoveData;

    /**
     * Find an Author by Id
     *
     * @param id to be find
     * @return the founded Author instance
     * @throws CanNotFoundData if not found an Author instance
     */
    Author findById(String id) throws CanNotFoundData;

    /**
     * Find an Author by Name (exact)
     *
     * @param name to be find
     * @return the founded Author instance
     * @throws CanNotFoundData if not found an Author instance
     */
    Author findByName(String name) throws CanNotFoundData;

    /**
     * Get all Author instances
     *
     * @return list of Authors
     */
    List<Author> allAuthors() throws CanNotFoundData;

}