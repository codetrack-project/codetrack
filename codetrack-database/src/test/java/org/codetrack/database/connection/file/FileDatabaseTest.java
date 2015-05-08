package org.codetrack.database.connection.file;

import junit.framework.TestCase;
import org.codetrack.database.data.Project;
import org.junit.Test;

import java.util.Date;

/**
 * @author josecmoj at 06/05/15.
 */
public class FileDatabaseTest extends TestCase {

    @Test
    public void testNewBuilder() throws Exception {

        FileDatabase.Builder builder = FileDatabase.newBuilder();

        Date lastUpdate = new Date();
        Project project = Project.newBuilder()
                .name("projectName")
                .description("projectDescription")
                .id("projectId")
                .build();

        builder.name("name")
                .lastUpdate(lastUpdate)
                .project(project);


        FileDatabase fileDatabase = new FileDatabase();
        fileDatabase.setName("name");
        fileDatabase.setLastUpdate(lastUpdate);
        fileDatabase.setProject(project);

        FileDatabase compareFileDatabase = builder.build();

        assertNotNull(compareFileDatabase);

        assertEquals(fileDatabase.hashCode(), compareFileDatabase.hashCode());
        assertEquals(fileDatabase.toString(), compareFileDatabase.toString());
        assertEquals(fileDatabase, compareFileDatabase);

    }

    @Test
    public void testIsModified() throws Exception {

        FileDatabase.Builder builder = FileDatabase.newBuilder();

        Date lastUpdate = new Date();
        Project project = Project.newBuilder()
                .name("projectName")
                .description("projectDescription")
                .id("projectId")
                .build();

        builder.name("name")
                .lastUpdate(lastUpdate)
                .project(project);

        FileDatabase fileDatabase = builder.build();

        assertFalse(fileDatabase.isModified());

        fileDatabase.setName("name2");
        assertTrue(fileDatabase.isModified());
        fileDatabase.markIsLoaded();
        fileDatabase.getProject().setName("projectName2");
        assertTrue(fileDatabase.isModified());

    }

    @Test
    public void testMarkIsLoaded() throws Exception {

        FileDatabase.Builder builder = FileDatabase.newBuilder();

        Date lastUpdate = new Date();
        Project project = Project.newBuilder()
                .name("projectName")
                .description("projectDescription")
                .id("projectId")
                .build();

        builder.name("name")
                .lastUpdate(lastUpdate)
                .project(project);

        FileDatabase fileDatabase = builder.build();

        fileDatabase.setName("name2");
        fileDatabase.markIsLoaded();
        assertFalse(fileDatabase.isModified());

    }

}