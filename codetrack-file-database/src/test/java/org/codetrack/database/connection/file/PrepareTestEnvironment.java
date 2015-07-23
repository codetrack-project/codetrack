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

import org.codetrack.database.DatabaseParameters;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author josecmoj at 22/07/15.
 */
@Component
public class PrepareTestEnvironment {

    public void cleanPath(DatabaseParameters databaseParameters){

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
}
