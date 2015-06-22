package org.codetrack.database;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author josecmoj at 26/05/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseTestConfiguration.class})
public class DatabaseManagerTest extends TestCase {

    @Autowired
    DatabaseManager databaseManager;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testRegister() throws Exception {

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