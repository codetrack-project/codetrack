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
import org.codetrack.domain.data.identify.Mark;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Mark data
 *
 * @author josecmoj at 04/07/15.
 * @see Mark
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface MarkRepository {

    /**
     * Save the Mark instance
     *
     * @param mark instance to be saved
     * @return the Mark instance saved
     * @throws CanNotSaveData if is not possible save Mark instance
     */
    Mark save(Mark mark) throws CanNotSaveData;

    /**
     * Remove an Mark instance from graph
     *
     * @param mark instance to be removed
     * @return the removed Mark instance
     * @throws CanNotRemoveData if is not possible to remove Mark instance
     */
    Mark remove(Mark mark) throws CanNotRemoveData;

    /**
     * Find an Mark by Id
     *
     * @param id to be find
     * @return the founded Mark instance
     * @throws CanNotFoundData if not found an Mark instance
     */
    Mark findById(String id) throws CanNotFoundData;

    /**
     * Find an Mark by Name (exact)
     *
     * @param name to be find
     * @return the founded Mark instance
     * @throws CanNotFoundData if not found an Mark instance
     */
    Mark findByName(String name) throws CanNotFoundData;

    /**
     * Get all Mark instances
     *
     * @return list of Marks
     */
    List<Mark> allMarks() throws CanNotFoundData;

}