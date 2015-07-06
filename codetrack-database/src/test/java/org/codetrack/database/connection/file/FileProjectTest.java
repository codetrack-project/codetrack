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

import junit.framework.TestCase;
import org.codetrack.domain.data.Project;
import org.codetrack.domain.data.identify.Mark;
import org.codetrack.domain.data.identify.Source;
import org.codetrack.domain.data.temporal.Cycle;
import org.codetrack.domain.data.temporal.Iteration;
import org.junit.Test;

import java.util.Date;

/**
 * @author josecmoj at 05/07/15.
 */
public class FileProjectTest extends TestCase {

    @Test
    public static void testNewBuilder() throws Exception {

        FileDatabase fileDatabase = FileDatabase.newBuilder().name("database1").build();

        FileProject fileProject = FileProject.newBuilder()
                .id("project1")
                .name("project1 name")
                .description("project1 description")
                .database(fileDatabase)
                .build();

        FileProject fileProjectTest = new FileProject();
        fileProjectTest.setId("project1");
        fileProjectTest.setName("project1 name");
        fileProjectTest.setDescription("project1 description");
        fileDatabase.addProject(fileProjectTest);

        assertNotNull(fileProject);
        assertNotNull(fileProject.getId());
        assertNotNull(fileProject.getName());
        assertNotNull(fileProject.getDescription());
        assertNotNull(fileProject.toString());

        assertTrue(fileProject.equals(fileProjectTest));
        assertEquals(fileProject, fileProjectTest);
        assertEquals(fileProject.hashCode(), fileProjectTest.hashCode());

        Project project = fileDatabase.findProject("project1");

        assertNotNull(project);
        assertEquals(fileProject, project);

    }

    @Test
    public void testFindById() throws Exception {

        Project project = FileProject.newBuilder()
                .id("project1")
                .description("project1")
                .database(FileDatabase.newBuilder()
                        .name("database1")
                        .build())
                .build();

        project.add(Source.newBuilder()
                .name("source1")
                .url("source1 url")
                .description("source1 description")
                .build())
                .add(Cycle.newBuilder()
                        .id("cycle1")
                        .name("cycle1 name")
                        .description("cycle1 description")
                        .startAt(new Date())
                        .build());

        Source source = (Source) project.findById("source1 url");
        assertNotNull(source);

        Cycle cycle = (Cycle) project.findById("cycle1");
        assertNotNull(cycle);

    }

    @Test
    public void testAdd() throws Exception {

        Project project = FileProject.newBuilder()
                .id("project1")
                .name("project1 name")
                .description("project1 description")
                .database(FileDatabase.newBuilder()
                        .name("database1")
                        .build())
                .build();


        for (int i = 0; i < 100; i++) {
            project.add(Iteration.newBuilder()
                    .id("iteration" + i)
                    .name("iteration" + i + " name")
                    .startAt(new Date()).build())
                    .add(Mark.newBuilder()
                            .id("mark" + i)
                            .name("mark" + i + " name")
                            .description("mark" + i + " description")
                            .build());
        }

        for (int j = 0; j < 100; j++) {

            Iteration iteration = (Iteration) project.findById("iteration" + j);
            assertNotNull(iteration);

            System.out.println("Found " + iteration.getId());

            Mark mark = (Mark) project.findById("mark" + j);
            assertNotNull(mark);

            System.out.println("Found " + mark.getId());

        }


    }

    @Test
    public void testRemove() throws Exception {

        Project project = FileProject.newBuilder()
                .id("project1")
                .name("project1 name")
                .description("project1 description")
                .database(FileDatabase.newBuilder()
                        .name("database1")
                        .build())
                .build();

        for (int i = 0; i < 100; i++) {
            project.add(Iteration.newBuilder()
                    .id("iteration" + i)
                    .name("iteration" + i + " name")
                    .startAt(new Date()).build())
                    .add(Mark.newBuilder()
                            .id("mark" + i)
                            .name("mark" + i + " name")
                            .description("mark" + i + " description")
                            .build());
        }

        for (int j = 0; j < 100; j++) {

            Iteration iteration = (Iteration) project.findById("iteration" + j);
            assertNotNull(iteration);

            System.out.println("Removing " + iteration.getId());

            project.remove(iteration);
            iteration = (Iteration) project.findById("iteration" + j);
            assertNull(iteration);

        }

    }
}