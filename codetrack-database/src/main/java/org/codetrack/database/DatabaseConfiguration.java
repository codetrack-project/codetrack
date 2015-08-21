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

import com.google.common.base.Strings;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.database.exception.DatabaseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * Class to handle database configuration data and operations
 *
 * @author josecmoj at 25/04/15.
 */
@Product(id = "codetrack-database")
@Feature(id = "#4-DATABASE")
@Component
public class DatabaseConfiguration {

    private static String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * Environment instance
     */
    @Autowired
    private Environment env;


    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);

    /**
     * Database properties configuration
     */
    private PropertiesConfiguration pc;

    /**
     * Codetrack config path
     */
    private String codetrackConfigPath;

    /**
     * Access codetrack configuration path
     *
     * @return String with config path
     */
    public String getCodetrackConfigPath() {

        if (Strings.isNullOrEmpty(codetrackConfigPath))
            codetrackConfigPath = env.getProperty("user.home") + FILE_SEPARATOR + ".codetrack";

        return codetrackConfigPath;
    }

    /**
     * Set the codetrack configuration path
     *
     * @param codetrackConfigPath - path
     */
    public void setCodetrackConfigPath(String codetrackConfigPath) {
        this.codetrackConfigPath = codetrackConfigPath;
    }

    /**
     * Create the minimum configuration data
     */
    @PostConstruct
    public void createMinimumConfiguration() {

        File file = new File(getCodetrackConfigPath() + FILE_SEPARATOR + "databases");
        if (!file.exists())
            file.mkdirs();

        logger.info("Created the minimum path configuration");
    }

    /**
     * Access general database properties
     *
     * @return properties
     */
    public PropertiesConfiguration databaseProperties() {

        if (pc == null)
            try {

                File file = new File(getCodetrackConfigPath() + FILE_SEPARATOR + "database.properties");

                if (!file.exists())
                    file.createNewFile();

                pc = new PropertiesConfiguration(file);
                pc.setAutoSave(true);

            } catch (Exception e) {
                throw new DatabaseError("Read database configuration file error", e, DatabaseError.DATABASE_OPEN_MAIN_CONFIG_ERROR);
            }

        return pc;

    }

    /**
     * Access a general property in configuration
     *
     * @param key - key property
     * @param defaultValue - value if property is not defined
     * @return String with property value
     */
    public String getProperty(String key, String defaultValue) {

        if (pc == null)
            pc = databaseProperties();

        return pc.getString(key, defaultValue);
    }

    /**
     * Load registered database parameters from configuration files
     *
     * @return map with name/DatabaseParameters
     */
    public Map<String, DatabaseParameters> loadRegisteredDatabases() {

        Hashtable<String, DatabaseParameters> databaseInfoHashtable = new Hashtable<>();

        try {

            Iterator it = databaseProperties().getKeys("database.registered");

            while (it.hasNext()) {

                String databaseName = databaseProperties().getString((String) it.next());

                File file = new File(getCodetrackConfigPath() + FILE_SEPARATOR + "database-" + databaseName + ".properties");

                DatabaseParameters.Builder dbib = DatabaseParameters.newBuilder();

                if (file.exists()) {

                    PropertiesConfiguration dbpc = new PropertiesConfiguration(file);

                    dbib.name(dbpc.getString("name"))
                            .engine(DatabaseEngine.valueOf(dbpc.getString("engine")))
                            .user(dbpc.getString("user"))
                            .password(dbpc.getString("password"))
                            .url(dbpc.getString("url"))
                            .path(dbpc.getString("path"));
                } else {

                    dbib.name(databaseName)
                            .engine(DatabaseEngine.FILE)
                            .user("")
                            .password("")
                            .url("")
                            .path(getCodetrackConfigPath() + FILE_SEPARATOR + "databases");
                }

                databaseInfoHashtable.put(databaseName, dbib.build());
            }

        } catch (ConfigurationException e) {
            throw new DatabaseError("Erro on load database properties file", e, DatabaseError.DATABASE_OPEN_CONFIG_ERROR);
        }

        return databaseInfoHashtable;

    }

    /**
     * Save database configuration
     *
     * @param databaseParameters
     */
    public void saveRegisteredDatabase(DatabaseParameters databaseParameters) {

        try {

            String databasePropertiesName = getCodetrackConfigPath() + FILE_SEPARATOR + "database-" + databaseParameters.getName() + ".properties";

            File file = new File(databasePropertiesName);

            if (!file.exists())
                file.createNewFile();

            PropertiesConfiguration dbpc = new PropertiesConfiguration(file);

            dbpc.setProperty("name", databaseParameters.getName());
            dbpc.setProperty("engine", databaseParameters.getEngine().name());
            dbpc.setProperty("user", databaseParameters.getUser());
            dbpc.setProperty("password", databaseParameters.getPassword());
            dbpc.setProperty("url", databaseParameters.getUrl());
            dbpc.setProperty("path", databaseParameters.getPath());

            dbpc.save();

        } catch (Exception e) {
            throw new DatabaseError("Error on load database properties file", e, DatabaseError.DATABASE_OPEN_CONFIG_ERROR);
        }

    }

    /**
     * Remove database configuration
     *
     * @param name
     */
    public void removeConfiguration(String name) {

        String databasePropertiesName = getCodetrackConfigPath() + FILE_SEPARATOR + "database-" + name + ".properties";

        try {

            File file = new File(databasePropertiesName);

            if (file.exists())
                file.delete();

            Iterator it = databaseProperties().getKeys("databases.registered");

            while (it.hasNext()) {

                String key = (String) it.next();

                String registered = databaseProperties().getString(key);

                if (registered.equals(name)) {
                    databaseProperties().clearProperty(key);
                    break;
                }

            }

        } catch (Exception e) {
            logger.error("Error on delete database " + name, e);
            throw new DatabaseError("Delete database " + name + " exception", e, DatabaseError.DATABASE_DELETE_ERROR);
        }
    }

    /**
     * Save the database key
     *
     * @param name of key
     */
    public void saveDatabaseKey(String name) {

        try {

            Iterator it = databaseProperties().getKeys("databases.registered");

            boolean found = false;
            int id = 1;

            while (it.hasNext()) {

                String key = (String) it.next();

                String registered = databaseProperties().getString(key);

                if (registered.equals(name)) {
                    found = true;
                    break;
                }

                id++;

            }

            if (!found)
                databaseProperties().setProperty("database.registered." + id, name);


        } catch (Exception e) {
            logger.error("Error on save database properties " + name, e);
            throw new DatabaseError("save database properties exception", e, DatabaseError.DATABASE_SAVE_ERROR);
        }
    }
}
