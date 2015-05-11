/*
 * Copyright 2015 the original author or authors members of codetrack.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.codetrack.database;

import org.codetrack.database.connection.DatabaseConnection;
import org.codetrack.database.connection.file.FileDatabaseConnection;
import org.codetrack.database.exception.DatabaseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

/**
 * Manage access to registered databases
 *
 * @author josecmoj at 25/04/15.
 */
@Component
public class DatabaseManager {

    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    private Logger logger = LoggerFactory.getLogger(DatabaseManager.class);

    /**
     * Current Database information
     */
    private DatabaseParameters activeDatabaseParameters;

    /**
     * Registered database information map
     */
    private Map<String, DatabaseParameters> databaseInfoTable;

    /**
     * Active connection map
     */
    private Hashtable<String, DatabaseConnection> databaseConnectionTable = new Hashtable<>();

    /**
     * Load an initialize data
     */
    @PostConstruct
    public void init() {
        databaseInfoTable = databaseConfiguration.loadRegisteredDatabases();
    }

    /**
     * Get active database parameters
     *
     * @return active DatabaseParameters instance
     */
    public DatabaseParameters getActiveDatabase() {
        return activeDatabaseParameters;
    }

    /**
     * Set active database parameters
     *
     * @param activeDatabaseParameters
     */
    public void setActiveDatabase(DatabaseParameters activeDatabaseParameters) {
        this.activeDatabaseParameters = activeDatabaseParameters;
    }


    /**
     * Exist an current database activated?
     *
     * @return
     */
    public boolean isActive() {
        return (activeDatabaseParameters != null);
    }

    /**
     * Get active DatabaseConnection
     *
     * @return active DatabaseConnection instance
     */
    public DatabaseConnection getActiveDatabaseConnection() {
        return databaseConnectionTable.get(activeDatabaseParameters.getName());
    }

    /**
     * Access all connections
     *
     * @return Collection of database connections
     */
    public Collection<DatabaseConnection> getConnections() {
        return databaseConnectionTable.values();
    }

    /**
     * Access all registered database information
     *
     * @return Collection of registered DatabaseParameters
     */
    public Collection<DatabaseParameters> getRegisteredDatabases() {
        return databaseInfoTable.values();
    }

    /**
     * Set project description on active DatabaseConnection
     *
     * @param description
     */
    public void setProjectDescription(String description) {
        getActiveDatabaseConnection().getDatabase().getProject().setDescription(description);
    }

    /**
     * Delete database
     *
     * @param name of database
     * @return String with result message
     * @throws DatabaseError
     */
    public String delete(String name) {

        try {

            DatabaseConnection conn = databaseConnectionTable.get(name);

            if (conn == null) {
                conn = createConnection(databaseInfoTable.get(name));
            }
            conn.open();
            ;
            conn.delete();

            databaseConfiguration.removeConfiguration(name);
            databaseInfoTable.remove(name);

        } catch (Exception e) {

            throw new DatabaseError("Delete database error!", e, DatabaseError.DATABASE_DELETE_ERROR);

        }

        return "Database " + name + " is successfully deleted";
    }

    /**
     * Open registered database connection
     *
     * @param usesDatabase name of registered database
     * @throws DatabaseError
     */
    private void open(DatabaseParameters usesDatabase) throws DatabaseError {

        System.out.println("Try open database " + usesDatabase.getName());

        activeDatabaseParameters = usesDatabase;

        DatabaseConnection conn = getActiveDatabaseConnection();

        if (conn == null) {

            conn = createConnection(activeDatabaseParameters);

            try {

                conn.open();
                databaseConnectionTable.put(activeDatabaseParameters.getName(), conn);

            } catch (Exception e) {
                new DatabaseError("Open the " + activeDatabaseParameters.getName() + " database error", e, DatabaseError.DATABASE_OPEN_ERROR);
            }
        }

        System.out.println("Database " + activeDatabaseParameters.getName() + " is current active");
    }

    /**
     * Close current database connection
     *
     * @throws DatabaseError
     */
    private void closeCurrent() throws DatabaseError {

        if (activeDatabaseParameters != null) {

            System.out.println("Closing database " + activeDatabaseParameters.getName());

            try {

                DatabaseConnection conn = getActiveDatabaseConnection();

                if (conn != null) {
                    conn.close();
                    databaseConnectionTable.remove(activeDatabaseParameters.getName());
                }

                System.out.println("Database " + activeDatabaseParameters.getName() + " is closed...");

                activeDatabaseParameters = null;

            } catch (Exception e) {
                throw new DatabaseError("Close database " + activeDatabaseParameters.getName() + " error", e, DatabaseError.DATABASE_ERROR_BASE);
            }
        }


    }

    /**
     * Create instance of DatabaseConnection
     *
     * @param databaseParameters
     * @return new DatabaseConnection instance
     */
    private DatabaseConnection createConnection(DatabaseParameters databaseParameters) {

        switch (databaseParameters.getEngine()) {

            case FILE:
                return new FileDatabaseConnection(databaseParameters);

            default:
                return null;
        }

    }

    /**
     * Register database
     *
     * @param databaseParameters with database configuration
     */
    public void register(DatabaseParameters databaseParameters) {

        if (databaseParameters == null)
            throw new DatabaseError("Database info is null", DatabaseError.DATABASE_CREATE_ERROR);
        else {

            if (databaseInfoTable.containsKey(databaseParameters.getName()))
                throw new DatabaseError("Database " + databaseParameters.getName() + " is already registered", DatabaseError.DATABASE_CREATE_ERROR);
            else {

                databaseInfoTable.put(databaseParameters.getName(), databaseParameters);
                databaseConfiguration.saveRegisteredDatabase(databaseParameters);
                databaseConfiguration.saveDatabaseKey(databaseParameters.getName());

                System.out.println("Database " + databaseParameters.getName() + " is now registered");

            }

        }

    }

    /**
     * Changes the current active database
     *
     * @param name register database name
     * @throws DatabaseError
     */
    public void uses(String name) throws DatabaseError {

        DatabaseParameters usesDatabase = databaseInfoTable.get(name);

        if (usesDatabase == null)
            throw new DatabaseError("Database " + name + " is not registered", DatabaseError.DATABASE_REGISTER_ERROR);
        else {

            closeCurrent();
            open(usesDatabase);
            load();
        }

    }

    /**
     * Close current database connection
     *
     * @throws DatabaseError
     */
    public void close() throws DatabaseError {

        DatabaseParameters usesDatabase = databaseInfoTable.get(activeDatabaseParameters.getName());

        if (usesDatabase == null)
            throw new DatabaseError("Database " + activeDatabaseParameters.getName() + " is not registered", DatabaseError.DATABASE_REGISTER_ERROR);
        else {
            save();
            closeCurrent();
        }

    }

    /**
     * Save Database graph on active DatabaseConnection
     *
     * @throws DatabaseError
     */
    public void save() {

        DatabaseConnection conn = getActiveDatabaseConnection();

        if (conn == null)
            throw new DatabaseError("No active database connection", DatabaseError.DATABASE_OPEN_ERROR);
        else {

            try {

                conn.save();
                databaseConfiguration.saveRegisteredDatabase(getActiveDatabase());

            } catch (Exception e) {
                throw new DatabaseError("Save project error!", e, DatabaseError.DATABASE_SAVE_ERROR);
            }

        }

    }

    /**
     * Load Database graph in active DatabaseConnection
     *
     * @throws DatabaseError
     */
    public void load() {

        DatabaseConnection conn = getActiveDatabaseConnection();

        if (conn == null)
            throw new DatabaseError("No active database connection", DatabaseError.DATABASE_OPEN_ERROR);
        else {

            try {

                conn.load();

            } catch (Exception e) {
                throw new DatabaseError("Load project error!", e, DatabaseError.DATABASE_LOAD_ERROR);
            }
        }

    }

}
