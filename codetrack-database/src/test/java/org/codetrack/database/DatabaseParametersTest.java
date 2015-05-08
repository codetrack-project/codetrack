package org.codetrack.database;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author josecmoj at 04/05/15.
 */
public class DatabaseParametersTest extends TestCase {

    @Test
    public void testNewBuilder() throws Exception {

        DatabaseParameters.Builder builder = DatabaseParameters.newBuilder();

        builder.name("name")
                .user("user")
                .password("pass")
                .path("path")
                .url("url")
                .engine(DatabaseEngine.FILE);

        DatabaseParameters databaseParameters;

        databaseParameters = new DatabaseParameters();
        databaseParameters.setName("name");
        databaseParameters.setUser("user");
        databaseParameters.setPassword("pass");
        databaseParameters.setPath("path");
        databaseParameters.setUrl("url");
        databaseParameters.setEngine(DatabaseEngine.FILE);

        DatabaseParameters compareDatabaseParameters = builder.build();

        assertEquals(databaseParameters.hashCode(), compareDatabaseParameters.hashCode());
        assertEquals(databaseParameters.toString(), compareDatabaseParameters.toString());
        assertTrue(databaseParameters.equals(compareDatabaseParameters));

    }
}