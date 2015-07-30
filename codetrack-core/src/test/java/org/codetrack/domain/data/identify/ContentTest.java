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
public class ContentTest extends TestCase {

    private static String PATHID = "pathId";

    private static String PATHDESC = "pathDescription";


    @Test
    public void testNewBuilder() throws Exception {

        Content content1 = Content.newBuilder().name("content1")
                .path(Path.newBuilder().id("pathid").url("pathurl").build())
                .contentType(ContentType.newBuilder().id("contenttypeid").extension("EXT").description("contenttypedesc").build())
                .build();

        Path path = new Path();
        path.setId("pathid");
        path.setUrl("pathurl");

        ContentType contentType = new ContentType();
        contentType.setId("contenttypeid");
        contentType.setDescription("contenttypedesc");
        contentType.setExtension("EXT");

        Content content2 = new Content();
        content2.setName("content1");
        content2.setPath(path);
        content2.setContentType(contentType);

        assertNotNull(content1);

        assertEquals(content1.getName(), content2.getName());
        assertEquals(content1.getContentType(), content2.getContentType());
        assertEquals(content1.getPath(), content2.getPath());


    }
}