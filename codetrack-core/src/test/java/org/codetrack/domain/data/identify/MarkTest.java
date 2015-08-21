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
public class MarkTest extends TestCase {

    @Test
    public void testNewBuilder() throws Exception {

        Mark mark1 = Mark.newBuilder().id("markid").description("markdescription").name("markname").build();

        Mark mark2 = new Mark();
        mark2.setId("markid");
        mark2.setName("markname");
        mark2.setDescription("markdescription");

        assertNotNull(mark1);

        assertEquals(mark1.getId(), mark2.getId());
        assertEquals(mark1.getName(), mark2.getName());
        assertEquals(mark1.getDescription(), mark2.getDescription());

        assertEquals(mark1.hashCode(), mark2.hashCode());
        assertEquals(mark1.toString(), mark2.toString());


    }
}