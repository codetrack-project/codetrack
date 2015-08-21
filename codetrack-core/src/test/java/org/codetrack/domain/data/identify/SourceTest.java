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
public class SourceTest extends TestCase {

    @Test
    public void testNewBuilder() throws Exception {

        Mark mark1 = GivenIdentify.getMark();
        Mark mark2 = GivenIdentify.getMark();

        SourceType sourcetype1 = GivenIdentify.getSourceType();

        Content content1 = GivenIdentify.getContent();

        Source source = Source.newBuilder().name("sourcename")
                .description("sourcedescription")
                .observation("sourceobservation")
                .url("sourceurl")
                .type(sourcetype1)
                .content(content1)
                .addMark(mark1)
                .addMark(mark2)
                .build();


        Source source2 = new Source();
        source2.setName("sourcename");
        source2.setDescription("sourcedescription");
        source2.setObservation("sourceobservation");
        source2.setUrl("sourceurl");
        source2.setContent(content1);
        source2.setType(sourcetype1);
        source2.addMark(mark1)
                .addMark(mark2);

        assertNotNull(source);

        assertEquals(source.getName(), source2.getName());
        assertEquals(source.getDescription(), source2.getDescription());
        assertEquals(source.getObservation(), source2.getObservation());
        assertEquals(source.getContent(), source2.getContent());
        assertEquals(source.getType(), source2.getType());

        assertFalse(source.getMarks().isEmpty());
        assertFalse(source2.getMarks().isEmpty());

        assertEquals(source.findMark(mark1.getId()), source2.findMark(mark1.getId()));

        Mark markrm = source.removeMark(source.findMark(mark1.getId()));

        assertNotNull(markrm);
        markrm = source.removeMark(markrm);
        assertNull(markrm);


    }
}