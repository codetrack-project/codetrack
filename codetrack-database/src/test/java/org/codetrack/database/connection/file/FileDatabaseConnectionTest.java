package org.codetrack.database.connection.file;

import junit.framework.TestCase;
import org.codetrack.database.DatabaseEngine;
import org.codetrack.database.DatabaseParameters;
import org.codetrack.database.data.Database;
import org.junit.Test;

import java.io.File;

/**
 * @author josecmoj at 06/05/15.
 */
public class FileDatabaseConnectionTest extends TestCase {

    private static String FILE_SEPARATOR = System.getProperty("file.separator");

    private static String CODETRACK_CONFIG_PATH = System.getProperty("user.home") + FILE_SEPARATOR + ".codetrack" + FILE_SEPARATOR + "tests";

    private static String DATABASE_TEXT_NAME = "DATABASE_NAME";

    private static String DATABASE_TEXT_NAME_MODIFIED = "DATABASE_NAME_MODIFIED";

    private static String PROJECT_TEXT_DESCRIPTION = "PROJECT DESCRIPTION";

    private static String PROJECT_TEXT_DESCRIPTION_MODIFIED = "PROJECT DESCRIPTION MODIFIED PROJECT DESCRIPTION MODIFIED PROJECT DESCRIPTION MODIFIED PROJECT DESCRIPTION MODIFIED"
            + "PROJECT DESCRIPTION MODIFIED PROJECT DESCRIPTION MODIFIED PROJECT DESCRIPTION MODIFIED PROJECT DESCRIPTION MODIFIED";

    private static DatabaseParameters getNewParameters() {

        DatabaseParameters databaseParameters = DatabaseParameters.newBuilder()
                .name(DATABASE_TEXT_NAME)
                .user("user")
                .password("password")
                .url("url")
                .path(CODETRACK_CONFIG_PATH)
                .engine(DatabaseEngine.FILE)
                .build();

        return databaseParameters;

    }

    private static FileDatabaseConnection getNewConnection() {
        return new FileDatabaseConnection(getNewParameters());
    }


    @Override
    public void setUp() throws Exception {
        super.setUp();

        DatabaseParameters databaseParameters = getNewParameters();

        File dir = new File(databaseParameters.getPath() + FILE_SEPARATOR + "databases");
        if (dir.exists()) {

            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }

            dir.delete();
        }

        dir.mkdirs();

    }

    @Test
    public void testOpen() throws Exception {

        FileDatabaseConnection fileDatabaseConnection = getNewConnection();

        fileDatabaseConnection.open();
        assertNotNull(fileDatabaseConnection.getFileName());

        String expectedFileName = fileDatabaseConnection.getDatabaseParameters().getPath()
                + FILE_SEPARATOR
                + "databases"
                + FILE_SEPARATOR
                + fileDatabaseConnection.getDatabaseParameters().getName()
                + FileDatabaseConnection.DATABASE_EXTENSION;

        assertNotNull(fileDatabaseConnection.getFileName());
        assertEquals(fileDatabaseConnection.getFileName(), expectedFileName);

        // Testing load without file
        fileDatabaseConnection.load();

        Database database = fileDatabaseConnection.getDatabase();
        assertNotNull(database);
        assertEquals(fileDatabaseConnection.getDatabaseParameters().getName(), database.getName());
        assertEquals(fileDatabaseConnection.getDatabaseParameters().getName(), database.getProject().getName());

        database.getProject().setDescription(PROJECT_TEXT_DESCRIPTION);

        // Testing first time save
        fileDatabaseConnection.save();

        File firstFile = new File(expectedFileName);
        assertTrue(firstFile.exists());
        assertTrue(firstFile.isFile());

        long firstLength = firstFile.length();
        long firstModified = firstFile.lastModified();

        database.setName(DATABASE_TEXT_NAME_MODIFIED);
        database.getProject().setDescription(PROJECT_TEXT_DESCRIPTION_MODIFIED);
        fileDatabaseConnection.save();

        File savedFile = new File(expectedFileName);
        assertTrue(savedFile.exists());
        assertTrue(savedFile.isFile());

        fileDatabaseConnection.close();

        // Create a new connection
        FileDatabaseConnection newFileDatabaseConnection = getNewConnection();

        newFileDatabaseConnection.open();
        newFileDatabaseConnection.load();

        assertFalse(newFileDatabaseConnection.getDatabase().getName().equals(DATABASE_TEXT_NAME));
        assertTrue(newFileDatabaseConnection.getDatabase().getProject().getDescription().equals(PROJECT_TEXT_DESCRIPTION_MODIFIED));

        String state = newFileDatabaseConnection.state();

        assertTrue(state.contains("database"));
        assertTrue(state.contains("engine"));
        assertTrue(state.contains("file"));
        assertTrue(state.contains("size"));

        newFileDatabaseConnection.close();
        newFileDatabaseConnection.delete();

        File fileDeleted = new File(expectedFileName);

        assertFalse(fileDeleted.exists());

    }

}