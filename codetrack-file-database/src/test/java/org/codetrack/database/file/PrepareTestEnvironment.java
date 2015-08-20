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

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.database.DatabaseManager;
import org.codetrack.database.DatabaseParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author josecmoj at 22/07/15.
 */
@Product(id = "codetrack-file-database")
@Feature(id = "#4-DATABASE")
@Component
public class PrepareTestEnvironment {

    @Autowired
    private DatabaseManager databaseManager;

    @Autowired
    private DatabaseParameters databaseParameters;

    public void cleanPath(){

        File dir = new File(databaseParameters.getPath() + FileTestConfiguration.FILE_SEPARATOR + "databases");
        if (dir.exists()) {

            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }

            dir.delete();
        }

        dir.mkdirs();
    }

    public void prepareAll() {

        cleanPath();

        if (!databaseManager.isRegistered(databaseParameters))
            databaseManager.register(databaseParameters);

    }
}
