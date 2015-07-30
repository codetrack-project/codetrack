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

package org.codetrack.database.connection;

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.database.DatabaseParameters;
import org.codetrack.domain.data.Database;
import org.codetrack.domain.data.Project;
import org.codetrack.repository.Repository;

/**
 * Interface to database connection
 *
 * @author josecmoj at 29/04/15.
 * @see DatabaseParameters
 */
@Product(id = "codetrack-database")
@Feature(id = "#4-DATABASE")
public interface DatabaseConnection {

    /**
     * Access database graph instance
     *
     * @return Database instance
     */
    Database getDatabase();

    /**
     * Set a database graph instance
     *
     * @param database instance
     */
    void setDatabase(Database database);

    /**
     * Database connection parameters an access definitions
     *
     * @return info instance
     * @see DatabaseParameters
     */
    DatabaseParameters getDatabaseParameters();

    /**
     * Set a database parameters definitions
     *
     * @param databaseParameters
     * @see DatabaseParameters
     */
    void setDatabaseParameters(DatabaseParameters databaseParameters);

    /**
     * This method open the database connection
     *
     * @throws Exception
     */
    void open() throws Exception;

    /**
     * This method close the database connection
     *
     * @throws Exception
     */
    void close() throws Exception;

    /**
     * Load all database graph
     *
     * @throws Exception
     */
    void load() throws Exception;

    /**
     * Save all database graph
     *
     * @throws Exception
     */
    void save() throws Exception;

    /**
     * Delete the database
     *
     * @throws Exception
     */
    void delete() throws Exception;

    /**
     * Access database connection status
     *
     * @return String with status information
     */
    String state();

    /**
     * Access the repository factory
     *
     * @return instance of DatabaseRepositoryFactory
     */
    DatabaseRepositoryFactory getRepositoryFactory();

    /**
     * Get a DatabaseRepositoryFactory instance to handle Item
     *
     * @param project - project instance to get repository
     * @param <E> - type of item to handle
     * @return Repository instance
     */
    <E> Repository<E> getRepository(Project project);


}
