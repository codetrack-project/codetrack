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

package org.codetrack.domain.data.identify;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author josecmoj at 09/06/15.
 */
public class PathTest extends TestCase {

    private static String ID = "pathid";

    private static String URL = "file://teste.jar";

    @Test
    public void testNewBuilder() throws Exception {

        Path path1 = Path.newBuilder().id(ID).url(URL).build();

        assertNotNull(path1);

        Path path2 = new Path();
        path2.setId(ID);
        path2.setUrl(URL);

        assertNotNull(path2);

        assertEquals(path1, path2);

    }
}