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

package org.codetrack.database.file;

import junit.framework.TestCase;
import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.database.Database;
import org.codetrack.database.DatabaseConnection;
import org.codetrack.database.DatabaseManager;
import org.codetrack.database.DatabaseParameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * @author josecmoj at 06/05/15.
 */
@Product(id = "codetrack-file-database")
@Feature(id = "#4-DATABASE")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FileTestConfiguration.class})
public class FileDatabaseConnectionTest extends TestCase {

    @Autowired
    private DatabaseManager databaseManager;

    @Autowired
    private DatabaseParameters databaseParameters;

    @Autowired
    private PrepareTestEnvironment prepareTestEnvironment;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        prepareTestEnvironment.prepareAll();

    }

    @Test
    public void testOpen() throws Exception {

        databaseManager.uses(databaseParameters.getName());
        DatabaseConnection databaseConnection = databaseManager.getActiveDatabaseConnection();

        assertNotNull(databaseConnection);

        assertTrue(databaseConnection instanceof FileDatabaseConnection);

        FileDatabaseConnection fileDatabaseConnection = (FileDatabaseConnection) databaseConnection;

        assertNotNull(fileDatabaseConnection.getFileName());

        String expectedFileName = fileDatabaseConnection.getDatabaseParameters().getPath()
                + FileTestConfiguration.FILE_SEPARATOR
                + "databases"
                + FileTestConfiguration.FILE_SEPARATOR
                + fileDatabaseConnection.getDatabaseParameters().getName()
                 + FileDatabaseConnection.DATABASE_EXTENSION;

        assertNotNull(fileDatabaseConnection.getFileName());
        assertEquals(fileDatabaseConnection.getFileName(), expectedFileName);

        // Testing load without file
        fileDatabaseConnection.load();

        Database database = fileDatabaseConnection.getDatabase();
        assertNotNull(database);
        assertEquals(fileDatabaseConnection.getDatabaseParameters().getName(), database.getName());

        // Create and Add new project
        database.addProject(FileProject.newBuilder()
                .id(FileTestConfiguration.PROJECT_ID)
                .name(FileTestConfiguration.PROJECT_NAME)
                .description(FileTestConfiguration.PROJECT_TEXT_DESCRIPTION)
                .build());

        // Testing first time save
        fileDatabaseConnection.save();

        File firstFile = new File(expectedFileName);
        assertTrue(firstFile.exists());
        assertTrue(firstFile.isFile());

        long firstLength = firstFile.length();
        long firstModified = firstFile.lastModified();

        database.setName(FileTestConfiguration.DATABASE_TEXT_NAME_MODIFIED);
        database.selectProject(FileTestConfiguration.PROJECT_ID).setDescription(FileTestConfiguration.PROJECT_TEXT_DESCRIPTION_MODIFIED);
        fileDatabaseConnection.save();

        File savedFile = new File(expectedFileName);
        assertTrue(savedFile.exists());
        assertTrue(savedFile.isFile());

        fileDatabaseConnection.close();


    }

}