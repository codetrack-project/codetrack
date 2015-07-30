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

package org.codetrack.repository;

import org.codetrack.exception.CanNotFoundData;
import org.codetrack.exception.CanNotRemoveData;
import org.codetrack.exception.CanNotSaveData;

/**
 * @author josecmoj at 09/07/15.
 */
public interface Repository<T> {

    /**
     * Save the Item instance
     *
     * @param item instance to be saved
     * @return the item instance saved
     * @throws CanNotSaveData if is not possible save Mark instance
     */
    T save(T item) throws CanNotSaveData;

    /**
     * Remove an item instance from graph
     *
     * @param item instance to be removed
     * @return the removed item instance
     * @throws CanNotRemoveData if is not possible to remove Mark instance
     */
    T remove(T item) throws CanNotRemoveData;

    /**
     * Search method
     * @param request Search request
     * @return instance of an SearchResponse implements
     * @throws CanNotFoundData
     */
    SearchResponse<T> search(SearchRequest<T> request) throws CanNotFoundData;

}
