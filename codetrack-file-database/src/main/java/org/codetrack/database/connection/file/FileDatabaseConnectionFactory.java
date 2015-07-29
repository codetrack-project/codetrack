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

import org.codetrack.database.DatabaseConnectionFactory;
import org.codetrack.database.DatabaseEngine;
import org.codetrack.database.DatabaseManager;
import org.codetrack.database.DatabaseParameters;
import org.codetrack.database.connection.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * File based Database connection implementation
 *
 * @author josecmoj at 06/07/15.
 */
@Component
public class FileDatabaseConnectionFactory implements DatabaseConnectionFactory{

    /**
     * Spring context injected
     */
    @Autowired
    private ApplicationContext context;

    /**
     * DatabaseManager injected instance
     */
    @Autowired
    private DatabaseManager databaseManager;

    /**
     * Register the connection factory in the DatabaseManager
     */
    @PostConstruct
    public void register(){
        if (databaseManager != null)
            databaseManager.registerConnectionFactory(this);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Always is FILE
     */
    @Override
    public DatabaseEngine getEngine() {
        return DatabaseEngine.FILE;
    }

    /**
     * Testing engine type
     * @param engine - enum database engine type
     * @return true if engine is FILE type
     */
    @Override
    public boolean isLikeThisEngine(DatabaseEngine engine) {
        return (DatabaseEngine.FILE.equals(engine));
    }

    /**
     * Factory method to get new FileDatabaseConnection instance
     * @param databaseParameters - parameters for create connection instance
     * @return new instance of FileDatabaseConnection
     */
    @Override
    public DatabaseConnection getInstance(DatabaseParameters databaseParameters) {
        FileDatabaseConnection connection = context.getBean(FileDatabaseConnection.class);
        connection.setDatabaseParameters(databaseParameters);
        return connection;
    }
}
