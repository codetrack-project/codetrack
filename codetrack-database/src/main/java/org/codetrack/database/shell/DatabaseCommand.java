/*
 * Copyright 2015 the original author or authors.
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

package org.codetrack.database.shell;

import com.google.common.base.Strings;
import org.codetrack.database.DatabaseConfiguration;
import org.codetrack.database.DatabaseEngine;
import org.codetrack.database.DatabaseManager;
import org.codetrack.database.DatabaseParameters;
import org.codetrack.database.exception.DatabaseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * Shell command to manipulate database operations
 *
 * @author josecmoj at 21/04/15.
 */
@Component
public class DatabaseCommand implements CommandMarker {

    private Logger logger = LoggerFactory.getLogger(DatabaseCommand.class);

    /**
     * Database manager instance
     */
    @Autowired
    private DatabaseManager databaseManager;

    /**
     * Database configuration instance
     */
    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    /**
     * Verify availability of "database register" command
     *
     * @return always true
     */
    @CliAvailabilityIndicator({"database register"})
    public boolean isDatabaseRegisterAvailable() {
        return true;
    }

    /**
     * Verify availability of "database list" command
     *
     * @return always true
     */
    @CliAvailabilityIndicator({"database list"})
    public boolean isDatabaseListAvailable() {
        return true;
    }

    /**
     * Verify availability of "database use" command
     *
     * @return always true
     */
    @CliAvailabilityIndicator({"database use"})
    public boolean isDatabaseUsesAvailable() {
        return true;
    }

    /**
     * Verify availability of "database active" command
     *
     * @return always true
     */
    @CliAvailabilityIndicator({"database active"})
    public boolean isDatabaseActiveAvailable() {
        return true;
    }

    /**
     * Verify availability of "database state" command
     *
     * @return always true
     */
    @CliAvailabilityIndicator({"database state"})
    public boolean isDatabaseStateAvailable() {
        return true;
    }

    /**
     * Verify availability of "database delete" command
     *
     * @return always true
     */
    @CliAvailabilityIndicator({"database delete"})
    public boolean isDatabaseDeleteAvailable() {
        return true;
    }

    /**
     * Register database command
     *
     * @param name of database
     * @param path to save the database
     * @return String with message result
     * @throws DatabaseError if database register error occur
     */
    @CliCommand(value = "database register", help = "Register database")
    public String register(
            @CliOption(key = {"name"}, mandatory = false, unspecifiedDefaultValue = "codetrackbase", help = "The database name")
            final String name,
            @CliOption(key = {"description"}, mandatory = false, specifiedDefaultValue = "", help = "Description of database")
            final String description,
            @CliOption(key = {"path"}, mandatory = false, specifiedDefaultValue = "", help = "The connection url database")
            final String path) throws DatabaseError {

        DatabaseParameters databaseParameters;

        if (Strings.isNullOrEmpty(path))
            databaseParameters = new DatabaseParameters(name, "", "", DatabaseEngine.FILE, "", databaseConfiguration.getCodetrackConfigPath());
        else
            databaseParameters = new DatabaseParameters(name, "", "", DatabaseEngine.FILE, "", path);

        databaseManager.register(databaseParameters);
        databaseManager.uses(databaseParameters.getName());
        databaseManager.setProjectDescription(description);

        return "Database " + name + " on " + databaseParameters.getPath() + " is registered!";

    }

    /**
     * List all registered databases
     *
     * @return String with database list
     */
    @CliCommand(value = "database list", help = "List all databases")
    public String list() {

        if (!databaseManager.getRegisteredDatabases().isEmpty()) {

            StringBuffer listResult = new StringBuffer();

            for (DatabaseParameters databaseParameters : databaseManager.getRegisteredDatabases()) {
                listResult
                        .append(databaseParameters.getName())
                        .append(" [")
                        .append(databaseParameters.getEngine().name())
                        .append("]")
                        .append("\n");
            }

            return listResult.toString();

        } else {

            return "Database register is empty. Nothing to do...";
        }

    }

    /**
     * Use a registered database
     *
     * @return String with message result
     */
    @CliCommand(value = "database use", help = "Use an registered database")
    public String use(
            @CliOption(key = {"name"}, mandatory = false, unspecifiedDefaultValue = "codetrackbase", help = "The database name")
            String name) {

        if (!databaseManager.getRegisteredDatabases().isEmpty()) {
            try {

                databaseManager.uses(name);

            } catch (DatabaseError dbe) {
                logger.error("Error on use database " + name, dbe);
                return "Cannot use the database " + name;
            }

            return "Database " + name + " is active";

        } else {

            return "Database register is empty. Nothing to do...";
        }
    }

    /**
     * Save the active database data
     *
     * @return String with message result
     */
    @CliCommand(value = "database save", help = "Save active database")
    public String save() {

        try {

            databaseManager.save();

        } catch (DatabaseError dbe) {
            logger.error("Error on save database " + databaseManager.getActiveDatabase().getName(), dbe);
            return "Cannot save the database " + databaseManager.getActiveDatabase().getName();
        }

        return "Database " + databaseManager.getActiveDatabase().getName() + " is saved";
    }

    /**
     * Reload database graph from database file
     * WARNING: this command erase database graph not saved modifications
     *
     * @return String with message result
     */
    @CliCommand(value = "database reload", help = "Reload active database (WARNING: this command erase not saved modified data)")
    public String reload() {

        try {

            databaseManager.load();

        } catch (DatabaseError dbe) {
            logger.error("Error on reload database " + databaseManager.getActiveDatabase().getName(), dbe);
            return "Cannot reload the database " + databaseManager.getActiveDatabase().getName();
        }

        return "Database " + databaseManager.getActiveDatabase().getName() + " is reload";

    }

    /**
     * Display current active database
     *
     * @return String with active database name
     */
    @CliCommand(value = "database active", help = "Display active database")
    public String active() {

        if (databaseManager.isActive())
            return databaseManager.getActiveDatabase().getName();
        else
            return "You not yet uses an database... try 'database use' command first";
    }

    /**
     * Display state of active or all registered databases
     *
     * @return String with active database state
     */
    @CliCommand(value = "database state", help = "Display active database state")
    public String state() {

        if (databaseManager.getActiveDatabaseConnection() != null)
            return databaseManager.getActiveDatabaseConnection().state();
        else
            return "You not yet uses an database... try 'database use' command first";
    }

    /**
     * Delete database
     *
     * @return String with active database state
     */
    @CliCommand(value = "database delete", help = "Delete active database")
    public String delete(
            @CliOption(key = {"name"}, mandatory = false, help = "Delete an database")
            String name) {

        if ((databaseManager.getActiveDatabase() != null)
                && (databaseManager.getActiveDatabase().getName().equals(name)))
            databaseManager.close();

        return databaseManager.delete(name);
    }

}
