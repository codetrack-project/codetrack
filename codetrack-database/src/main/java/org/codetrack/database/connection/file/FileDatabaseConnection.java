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

package org.codetrack.database.connection.file;

import org.codetrack.database.DatabaseParameters;
import org.codetrack.database.connection.DatabaseConnection;
import org.codetrack.database.data.Database;

import java.io.*;
import java.util.Date;

/**
 * File stream serializer DatabaseConnection implementation
 *
 * @author josecmoj at 02/05/15.
 * @see DatabaseConnection
 * @see FileDatabase
 * @see DatabaseParameters
 * @see FileDatabaseException
 */
public class FileDatabaseConnection implements DatabaseConnection {

    public static String DATABASE_EXTENSION = ".dat";

    public static String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * Database parameters information
     */
    private DatabaseParameters databaseParameters;

    /**
     * Out file Stream used to save Database
     */
    private OutputStream outFile;

    /**
     * Out buffer stream used to save Database
     */
    private OutputStream outBuffer;

    /**
     * Object output used to save Database
     */
    private ObjectOutput output;

    /**
     * In file stream to load Database
     */
    private InputStream inFile;

    /**
     * In buffer stream used to load Database
     */
    private InputStream inBuffer;

    /**
     * Object input to load Database
     */
    private ObjectInput input;

    /**
     * Database file name
     */
    private String fileName;

    /**
     * Codetrack base path name
     */
    private String pathName;

    /**
     * Database graph instance
     */
    private Database database;

    public FileDatabaseConnection(DatabaseParameters databaseParameters) {
        this.databaseParameters = databaseParameters;
    }

    @Override
    public Database getDatabase() {
        return database;
    }

    @Override
    public void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public DatabaseParameters getDatabaseParameters() {
        return databaseParameters;
    }

    @Override
    public void setDatabaseParameters(DatabaseParameters databaseParameters) {
        this.databaseParameters = databaseParameters;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    /**
     * Close file output database (if is opened)
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {

        if (output != null) {
            output.flush();
            output.close();
            output = null;
        }

    }

    /**
     * This simulate a open database connection operation
     * Set database file name to internal variable fileName
     *
     * @throws Exception
     */
    @Override
    public void open() throws Exception {

        setPathName(databaseParameters.getPath() + FILE_SEPARATOR + "databases" + FILE_SEPARATOR + databaseParameters.getName());
        setFileName(getPathName() + DATABASE_EXTENSION);

    }

    /**
     * Load database graph from database file
     *
     * @throws Exception
     * @throws FileDatabaseException
     */
    @Override
    public void load() throws Exception {

        File file = new File(fileName);

        if (file.exists()) {

            inFile = new FileInputStream(file);
            inBuffer = new BufferedInputStream(inFile);
            input = new ObjectInputStream(inBuffer);

            this.database = (Database) input.readObject();

            input.close();
        }

        // File not exist and database is not create yet
        if (this.database == null)
            this.database = FileDatabase.newBuilder()
                    .name(databaseParameters.getName())
                    .lastUpdate(new Date())
                    .build();


    }

    /**
     * Save database graph in database file
     *
     * @throws Exception
     * @throws FileDatabaseException
     */
    @Override
    public void save() throws Exception {

        if (database.isModified())
            database.setLastUpdate(new Date());

        File file = new File(fileName);

        try {

            if (!file.exists())
                file.createNewFile();

            if (file.exists()) {

                outFile = new FileOutputStream(file);
                outBuffer = new BufferedOutputStream(outFile);
                output = new ObjectOutputStream(outBuffer);

                output.writeObject(database);

                close();

            } else
                throw new FileDatabaseException("Cannot save file " + file.getAbsolutePath());

        } catch (Exception ex) {

            throw new FileDatabaseException("Cannot create file " + file.getCanonicalPath());
        }

    }

    /**
     * Delete database file
     *
     * @throws Exception
     * @throws FileDatabaseException
     */
    @Override
    public void delete() throws Exception {

        File file = new File(fileName);

        if (file.exists()) {

            try {
                file.delete();
            } catch (Exception e) {
                throw new FileDatabaseException("Cannot delete file " + fileName);
            }

        }

    }

    /**
     * Access to database file state information
     *
     * @return String with status information
     */
    @Override
    public String state() {

        StringBuilder builder = new StringBuilder();

        builder.append("database: ")
                .append(databaseParameters.getName())
                .append("|engine: ")
                .append(databaseParameters.getEngine().name());

        File file = new File(fileName);
        if (file.exists()) {
            builder.append("|file: ")
                    .append(file.getAbsolutePath())
                    .append("|size: ")
                    .append(file.length());
        }

        return builder.toString();
    }
}
