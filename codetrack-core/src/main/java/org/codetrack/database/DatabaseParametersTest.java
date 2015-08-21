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
import org.junit.Test;

/**
 * @author josecmoj at 04/05/15.
 */
@Product(id = "codetrack-database")
@Feature(id = "#4-DATABASE")
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