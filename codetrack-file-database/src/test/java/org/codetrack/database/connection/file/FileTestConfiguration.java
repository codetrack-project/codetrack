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

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.database.DatabaseEngine;
import org.codetrack.database.DatabaseManager;
import org.codetrack.database.DatabaseParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author josecmoj at 20/07/15.
 */
@Product(id = "codetrack-file-database")
@Feature(id = "#4-DATABASE")
@Configuration
@ComponentScan({"org.codetrack.database","org.codetrack.database.connection.file"})
public class FileTestConfiguration {

    public static String FILE_SEPARATOR = System.getProperty("file.separator");

    public static String CODETRACK_CONFIG_PATH = System.getProperty("user.home") + FILE_SEPARATOR + ".codetrack" + FILE_SEPARATOR + "tests";

    public static String DATABASE_TEXT_NAME = "DATABASE_NAME";

    public static String DATABASE_TEXT_NAME_MODIFIED = "DATABASE_NAME_MODIFIED";

    public static String PROJECT_NAME = "FileProject Name";

    public static String PROJECT_ID = "FileProject Id";

    public static String PROJECT_TEXT_DESCRIPTION = "PROJECT DESCRIPTION";

    public static String PROJECT_TEXT_DESCRIPTION_MODIFIED = "PROJECT DESCRIPTION MODIFIED PROJECT DESCRIPTION MODIFIED PROJECT DESCRIPTION MODIFIED PROJECT DESCRIPTION MODIFIED"
            + "PROJECT DESCRIPTION MODIFIED PROJECT DESCRIPTION MODIFIED PROJECT DESCRIPTION MODIFIED PROJECT DESCRIPTION MODIFIED";

    @Autowired
    private DatabaseManager databaseManager;

    @Bean
    public DatabaseParameters databaseParameters() {

        DatabaseParameters databaseParameters = DatabaseParameters.newBuilder()
                .name(DATABASE_TEXT_NAME)
                .user("user")
                .password("password")
                .url("url")
                .path(CODETRACK_CONFIG_PATH)
                .engine(DatabaseEngine.FILE)
                .build();

        this.databaseManager.register(databaseParameters);
        return databaseParameters;

    }

}
