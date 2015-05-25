package org.codetrack.database.connection.file;

import com.google.common.collect.Maps;
import junit.framework.TestCase;
import org.codetrack.database.data.Project;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

/**
 * @author josecmoj at 06/05/15.
 */
public class FileDatabaseTest extends TestCase {

    private static String DATABASE_TEXT_NAME_MODIFIED = "DATABASE_NAME_MODIFIED";

    private static String PROJECT_NAME = "Project Name";

    private static String PROJECT_ID = "Project Id";

    private static String PROJECT_TEXT_DESCRIPTION = "PROJECT DESCRIPTION";

    @Test
    public void testNewBuilder() throws Exception {

        FileDatabase.Builder builder = FileDatabase.newBuilder();

        Date lastUpdate = new Date();

        Project project1 = Project.newBuilder()
                .name(PROJECT_NAME + 1)
                .description(PROJECT_TEXT_DESCRIPTION)
                .id(PROJECT_ID + 1)
                .build();

        Project project2 = Project.newBuilder()
                .name(PROJECT_NAME + 2)
                .description(PROJECT_TEXT_DESCRIPTION)
                .id(PROJECT_ID + 2)
                .build();

        Map<String, Project> projects = Maps.newHashMap();

        builder.name("name")
                .lastUpdate(lastUpdate)
                .putProject(project1)
                .putProject(project2);

        FileDatabase fileDatabase = new FileDatabase();
        fileDatabase.setName("name");
        fileDatabase.setLastUpdate(lastUpdate);
        fileDatabase.addProject(project1);
        fileDatabase.addProject(project2);
        fileDatabase.selectProject(PROJECT_ID + 1);

        FileDatabase compareFileDatabase = builder.build();

        assertNotNull(compareFileDatabase);
        assertEquals(fileDatabase.getName(), compareFileDatabase.getName());
        assertEquals(fileDatabase.selectProject(PROJECT_ID + 1).getName(), compareFileDatabase.selectProject(PROJECT_ID + 1).getName());

    }

    @Test
    public void testIsModified() throws Exception {

        FileDatabase.Builder builder = FileDatabase.newBuilder();

        Project project1 = Project.newBuilder()
                .name(PROJECT_NAME + 1)
                .description(PROJECT_TEXT_DESCRIPTION)
                .id(PROJECT_ID + 1)
                .build();

        Project project2 = Project.newBuilder()
                .name(PROJECT_NAME + 2)
                .description(PROJECT_TEXT_DESCRIPTION)
                .id(PROJECT_ID + 2)
                .build();

        builder
                .lastUpdate(new Date())
                .putProject(project1)
                .putProject(project2);

        FileDatabase fileDatabase = builder.build();
        assertTrue(fileDatabase.isModified());
        fileDatabase.setName("name2");
        assertTrue(fileDatabase.isModified());
        fileDatabase.markIsLoaded();
        fileDatabase.selectProject(PROJECT_ID + 2);
        assertFalse(fileDatabase.isModified());

    }

    @Test
    public void testMarkIsLoaded() throws Exception {

        FileDatabase.Builder builder = FileDatabase.newBuilder();

        Project project1 = Project.newBuilder()
                .name(PROJECT_NAME + 1)
                .description(PROJECT_TEXT_DESCRIPTION)
                .id(PROJECT_ID + 1)
                .build();

        Project project2 = Project.newBuilder()
                .name(PROJECT_NAME + 2)
                .description(PROJECT_TEXT_DESCRIPTION)
                .id(PROJECT_ID + 2)
                .build();

        builder
                .lastUpdate(new Date())
                .putProject(project1)
                .putProject(project2);

        FileDatabase fileDatabase = builder.build();

        fileDatabase.setName("name2");
        fileDatabase.markIsLoaded();
        assertFalse(fileDatabase.isModified());

    }

}