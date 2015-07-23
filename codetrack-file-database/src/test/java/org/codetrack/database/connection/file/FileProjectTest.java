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
import org.codetrack.database.DatabaseManager;
import org.codetrack.database.DatabaseParameters;
import org.codetrack.domain.data.Database;
import org.codetrack.domain.data.Project;
import org.codetrack.domain.data.identify.Mark;
import org.codetrack.domain.data.identify.Source;
import org.codetrack.domain.data.temporal.Cycle;
import org.codetrack.domain.data.temporal.Iteration;
import org.codetrack.repository.Repository;
import org.codetrack.repository.SearchOneById;
import org.codetrack.repository.SearchResponse;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author josecmoj at 05/07/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FileTestConfiguration.class})
public class FileProjectTest extends TestCase {

    @Autowired
    private DatabaseManager databaseManager;

    @Autowired
    private DatabaseParameters databaseParameters;

    @Autowired
    private PrepareTestEnvironment prepareTestEnvironment;

    @Override
    public void setUp() throws Exception {

        super.setUp();

        prepareTestEnvironment.cleanPath(databaseParameters);
        databaseManager.register(databaseParameters);

    }

    @Test
    public void testNewBuilder() throws Exception {

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

        databaseManager.uses(databaseParameters.getName());
        Database database = databaseManager.getActiveDatabaseConnection().getDatabase();

        assertNotNull(database);

        FileProject project = FileProject.newBuilder()
                .id("project1")
                .description("project1")
                .database(FileDatabase.newBuilder()
                        .name("database1")
                        .build())
                .build();

        database.addProject(project);

        Source source = Source.newBuilder()
                    .name("source1")
                    .url("source1 url")
                    .description("source1 description")
                    .build();

        Cycle cycle = Cycle.newBuilder()
                .id("cycle1")
                .name("cycle1 name")
                .description("cycle1 description")
                .startAt(new Date())
                .build();

        Repository<Source> sourceRepository = databaseManager.getActiveDatabaseConnection().getRepository(project);

        assertNotNull(sourceRepository);
        sourceRepository.save(source);

        Repository<Cycle> cycleRepository = databaseManager.getActiveDatabaseConnection().getRepository(project);

        assertNotNull(cycleRepository);
        cycleRepository.save(cycle);

        SearchOneById<Source> request1 = SearchOneById.newBuilder()
                .id("source1 url")
                .itemClazz(Source.class)
                .build();

        SearchResponse<Source> response1 = sourceRepository.search(request1);

        assertNotNull(response1);
        assertNotNull(response1.getItem());
        assertEquals(request1,response1.getRequest());

        SearchOneById<Cycle> request2 = SearchOneById.newBuilder()
                .id("cycle1")
                .itemClazz(Cycle.class)
                .build();

        SearchResponse<Cycle> response2 = cycleRepository.search(request2);
        assertNotNull(response2);
        assertNotNull(response2.getItem());
        assertEquals(request2,response2.getRequest());

    }

    @Test
    public void testAdd() throws Exception {

        databaseManager.uses(databaseParameters.getName());
        Database database = databaseManager.getActiveDatabaseConnection().getDatabase();

        assertNotNull(database);

        Project project = FileProject.newBuilder()
                .id("project1")
                .name("project1 name")
                .description("project1 description")
                .database(FileDatabase.newBuilder()
                        .name("database1")
                        .build())
                .build();

        database.addProject(project);

        Repository<Iteration> iterationRepository = databaseManager.getActiveDatabaseConnection().getRepository(project);
        Repository<Mark> markRepository = databaseManager.getActiveDatabaseConnection().getRepository(project);

        for (int i = 0; i < 100; i++) {

            iterationRepository.save(Iteration.newBuilder()
                    .id("iteration" + i)
                    .name("iteration" + i + " name")
                    .startAt(new Date()).build());

            markRepository.save(Mark.newBuilder()
                    .id("mark" + i)
                    .name("mark" + i + " name")
                    .description("mark" + i + " description")
                    .build());
        }

        for (int j = 0; j < 100; j++) {

            SearchOneById<Iteration> iterationSearchOneById = SearchOneById.newBuilder()
                    .id("iteration" + j)
                    .itemClazz(Iteration.class)
                    .build();

            SearchResponse<Iteration> iterationSearchResponse = iterationRepository.search(iterationSearchOneById);
            assertNotNull(iterationSearchResponse);
            assertNotNull(iterationSearchResponse.getItem());

            System.out.println("Found " + iterationSearchResponse.getItem().getId());

        }


    }

    @Test
    @Ignore
    public void testRemove() throws Exception {

        FileProject project = FileProject.newBuilder()
                .id("project1")
                .name("project1 name")
                .description("project1 description")
                .database(FileDatabase.newBuilder()
                        .name("database1")
                        .build())
                .build();

        FileRepository<Iteration> iterationFileRepository = new FileRepository<>();
        iterationFileRepository.setProject(project);

        FileRepository<Mark> markFileRepository = new FileRepository<>();
        markFileRepository.setProject(project);

        for (int i = 0; i < 100; i++) {

            iterationFileRepository.
            project.add(Iteration.newBuilder()
                    .id("iteration" + i)
                    .name("iteration" + i + " name")
                    .startAt(new Date()).build());

            project.add(Mark.newBuilder()
                            .id("mark" + i)
                            .name("mark" + i + " name")
                            .description("mark" + i + " description")
                            .build());
        }

        for (int j = 0; j < 100; j++) {

            //Iteration iteration = (Iteration) project.findById("iteration" + j);
            //assertNotNull(iteration);

            //System.out.println("Removing " + iteration.getId());

            //project.remove(iteration);
            //iteration = (Iteration) project.findById("iteration" + j);
            //assertNull(iteration);

        }

    }
}