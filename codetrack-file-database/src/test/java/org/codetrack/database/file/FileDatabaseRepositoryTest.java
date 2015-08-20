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
import org.codetrack.domain.data.temporal.Cycle;
import org.codetrack.repository.Repository;
import org.codetrack.repository.SearchOneById;
import org.codetrack.repository.SearchResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author josecmoj at 22/07/15.
 */
@Product(id = "codetrack-file-database")
@Feature(id = "#4-DATABASE")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FileTestConfiguration.class})
public class FileDatabaseRepositoryTest extends TestCase {

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
    public void testName() throws Exception {

        databaseManager.uses(databaseParameters.getName());
        DatabaseConnection connection = databaseManager.getActiveDatabaseConnection();

        Database database = connection.getDatabase();
        FileProject project = FileProject.newBuilder()
            .id("project1")
            .name("project1 name")
            .description("project1 desc")
            .build();

        database.addProject(project);

        Repository<Cycle> cycleRepository = connection.getRepository(project);

        assertNotNull(cycleRepository);

        Cycle cycle = Cycle.newBuilder()
                .id("cycle1")
                .name("cycle1 name")
                .description("cycle1 desc")
                .startAt(new Date())
                .endAt(new Date())
                .build();

        cycleRepository.save(cycle);

        SearchOneById<Cycle> searchOneById = SearchOneById.newBuilder()
                                                .id("cycle1")
                                                .itemClazz(Cycle.class)
                .build();

        SearchResponse<Cycle> searchResponse = cycleRepository.search(searchOneById);

        assertNotNull(searchResponse);
        assertNotNull(searchResponse.getItem());
        assertEquals(cycle,searchResponse.getItem());


    }

}
