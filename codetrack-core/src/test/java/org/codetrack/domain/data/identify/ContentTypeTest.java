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
public class ContentTypeTest extends TestCase {

    private static String ID = "contentTypeID";

    private static String DESCRIPTION = "contentTypeDescription";

    private static String EXTENSION = "EXT";

    @Test
    public void testNewBuilder() throws Exception {

        ContentType contentType1 = ContentType.newBuilder().id(ID).description(DESCRIPTION).extension(EXTENSION).build();

        ContentType contentType2 = new ContentType();
        contentType2.setId(ID);
        contentType2.setDescription(DESCRIPTION);
        contentType2.setExtension(EXTENSION);

        assertNotNull(contentType1);

        assertEquals(contentType1.getId(), contentType2.getId());
        assertEquals(contentType1.getDescription(), contentType2.getDescription());
        assertEquals(contentType1.getExtension(), contentType2.getExtension());

        assertEquals(contentType1, contentType2);

        assertEquals(contentType1.toString(), contentType2.toString());
        assertEquals(contentType1.hashCode(), contentType2.hashCode());

    }
}