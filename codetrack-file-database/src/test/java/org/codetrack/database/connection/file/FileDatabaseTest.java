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

import com.google.common.collect.Maps;
import junit.framework.TestCase;
import org.codetrack.domain.data.Project;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

/**
 * @author josecmoj at 06/05/15.
 */
public class FileDatabaseTest extends TestCase {

    private static String DATABASE_TEXT_NAME_MODIFIED = "DATABASE_NAME_MODIFIED";

    private static String PROJECT_NAME = "FileProject Name";

    private static String PROJECT_ID = "FileProject Id";

    private static String PROJECT_TEXT_DESCRIPTION = "PROJECT DESCRIPTION";

    @Test
    public void testNewBuilder() throws Exception {

        FileDatabase.Builder builder = FileDatabase.newBuilder();

        Date lastUpdate = new Date();

        Project project1 = FileProject.newBuilder()
                .name(PROJECT_NAME + 1)
                .description(PROJECT_TEXT_DESCRIPTION)
                .id(PROJECT_ID + 1)
                .build();

        Project project2 = FileProject.newBuilder()
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

        Project project1 = FileProject.newBuilder()
                .name(PROJECT_NAME + 1)
                .description(PROJECT_TEXT_DESCRIPTION)
                .id(PROJECT_ID + 1)
                .build();

        Project project2 = FileProject.newBuilder()
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

        Project project1 = FileProject.newBuilder()
                .name(PROJECT_NAME + 1)
                .description(PROJECT_TEXT_DESCRIPTION)
                .id(PROJECT_ID + 1)
                .build();

        Project project2 = FileProject.newBuilder()
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