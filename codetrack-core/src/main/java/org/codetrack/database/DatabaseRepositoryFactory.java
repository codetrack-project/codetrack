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

package org.codetrack.database;

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.Project;
import org.codetrack.repository.Repository;

/**
 * Interface defines operations of an repository factory
 * @author josecmoj at 16/07/15.
 */
@Product(id = "codetrack-database")
@Feature(id = "#4-DATABASE")
public interface DatabaseRepositoryFactory {

    /**
     * Get instance of Repository
     *
     * @param database instance of database
     * @param project  instance of project
     * @param <E>      generic class
     * @return instance of Repository
     */
    <E> Repository<E> getRepository(Database database, Project project);

}
