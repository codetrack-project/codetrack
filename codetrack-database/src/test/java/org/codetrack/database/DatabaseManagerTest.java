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

import junit.framework.TestCase;
import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.mockito.Mockito.*;

/**
 * @author josecmoj at 26/05/15.
 */
@Product(id = "codetrack-database")
@Feature(id = "#4-DATABASE")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseTestConfiguration.class})
public class DatabaseManagerTest extends TestCase {

    @Autowired
    DatabaseManager databaseManager;

    @Mock
    Database mockDatabase;

    @Mock
    DatabaseConnection mockDatabaseConnection;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

    }

    @Ignore
    @Test
    public void testRegister() throws Exception {

        mockDatabase = mock(Database.class);
        when(mockDatabase.getName()).thenReturn("database1");
        when(mockDatabase.getLastUpdate()).thenReturn(new Date());

        mockDatabaseConnection = mock(DatabaseConnection.class);
        when(mockDatabaseConnection.getDatabase()).thenReturn(mockDatabase);

        DatabaseManager spyDatabaseManager = spy(databaseManager);

        doReturn(spyDatabaseManager.getActiveDatabaseConnection()).doReturn(mockDatabaseConnection);

        assertNotNull(databaseManager.getDatabaseConfiguration());

        databaseManager.register(DatabaseParameters.newBuilder()
                .engine(DatabaseEngine.FILE)
                .user("user")
                .password("password")
                .url("url")
                .name("database")
                .path(databaseManager.getDatabaseConfiguration().getCodetrackConfigPath()).build());


        databaseManager.uses("database");
        assertNotNull(databaseManager.getRegisteredDatabases());
        assertEquals(databaseManager.getRegisteredDatabases().size(), 1);
        assertNotNull(databaseManager.getActiveDatabase());
        assertEquals(databaseManager.getActiveDatabase().getName(), "database");

        assertNotNull(databaseManager.getActiveDatabaseConnection());
        assertFalse(databaseManager.getConnections().isEmpty());
        assertEquals(databaseManager.getActiveDatabaseConnection().getDatabase().getName(), "database");

        databaseManager.close();
        assertNull(databaseManager.getActiveDatabaseConnection());

    }

}