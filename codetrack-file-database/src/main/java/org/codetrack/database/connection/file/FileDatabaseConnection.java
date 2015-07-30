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

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.database.DatabaseParameters;
import org.codetrack.database.connection.DatabaseConnection;
import org.codetrack.database.connection.DatabaseRepositoryFactory;
import org.codetrack.domain.data.Database;
import org.codetrack.domain.data.Project;
import org.codetrack.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * File stream serializer DatabaseConnection implementation
 *
 * @author josecmoj at 02/05/15.
 * @see DatabaseConnection
 * @see FileDatabase
 * @see DatabaseParameters
 * @see FileDatabaseException
 */
@Product(id = "codetrack-file-database")
@Feature(id = "#4-DATABASE")
@Component
@Scope("prototype")
public class FileDatabaseConnection implements DatabaseConnection, Observer{

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
    private FileDatabase database;

    /**
     * Repository factory
     */
    @Autowired
    private FileDatabaseRepositoryFactory repositoryFactory;

    public FileDatabaseConnection() {
        super();
    }

    public FileDatabaseConnection(DatabaseParameters databaseParameters) {
        super();
        this.databaseParameters = databaseParameters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Database getDatabase() {
        return database;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDatabase(Database database) {
        this.database = (FileDatabase)database;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabaseParameters getDatabaseParameters() {
        return databaseParameters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDatabaseParameters(DatabaseParameters databaseParameters) {
        this.databaseParameters = databaseParameters;
    }

    /**
     * Access file name
     *
     * @return String with file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Setting file name
     * @param fileName - String with file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get path name
     * @return String with path name
     */
    public String getPathName() {
        return pathName;
    }

    /**
     * Setting path name
     * @param pathName - String with path name
     */
    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    /**
     * Close file output database (if is opened)
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

            this.database = (FileDatabase) input.readObject();

            input.close();
        }

        // File not exist and database is not create yet
        if (this.database == null) {
            this.database = FileDatabase.newBuilder()
                    .name(databaseParameters.getName())
                    .lastUpdate(new Date())
                    .build();
            this.database.addObserver(this);
        }

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

    /**
     * Observer override update method, receive change notifications from FileDatabase
     *
     * @param o - Observable object instance (FileDatabase)
     * @param arg - changed data (optional)
     */
    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof FileDatabase){

            try {

                switch (((FileDatabase)o).getState()){

                    case CHANGED:
                        this.save();

                }

            } catch (Exception e) {

            }
       }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabaseRepositoryFactory getRepositoryFactory() {
        return repositoryFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> Repository<E> getRepository(Project project) {
        return repositoryFactory.getRepository(this.getDatabase(), project);
    }
}
