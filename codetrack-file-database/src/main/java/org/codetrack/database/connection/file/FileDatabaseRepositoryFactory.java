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

package org.codetrack.database.connection.file;

import org.codetrack.database.connection.DatabaseRepositoryFactory;
import org.codetrack.domain.data.Database;
import org.codetrack.domain.data.Project;
import org.codetrack.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * FileDatabase repository factory
 *
 * @author josecmoj at 16/07/15.
 */
@Component
@Scope("prototype")
public class FileDatabaseRepositoryFactory implements DatabaseRepositoryFactory {

    /**
     * Spring injected context
     */
    @Autowired
    private ApplicationContext context;

    /**
     * Get a FileRepository based instance
     *
     * @param database instance of database
     * @param project  instance of project
     * @param <E>      generic graph data class
     * @return instance of FileRepository
     */
    @Override
    public <E> Repository<E> getRepository(Database database, Project project) {
        FileRepository<E> fileRepository = context.getBean(FileRepository.class, database, project);
        return fileRepository;
    }
}
