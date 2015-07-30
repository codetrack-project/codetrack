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

package org.codetrack.domain.data.temporal;

import junit.framework.TestCase;
import org.codetrack.domain.data.identify.GivenIdentify;
import org.codetrack.domain.data.identify.Source;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

/**
 * @author josecmoj at 09/06/15.
 */
public class CycleTest extends TestCase {

    @Test
    public void testNewBuilder() throws Exception {

        Date startDate = new Date();
        Date endDate = new Date();

        Map<String, Sprint> sprintMap = GivenTemporal.getSprintMap();
        Map<String, Iteration> iterationMap = GivenTemporal.getIterationMap();

        Cycle cycle1 = Cycle.newBuilder()
                .id("cycleid")
                .name("cyclename")
                .description("cycledescription")
                .iterations(iterationMap)
                .sprints(sprintMap)
                .startAt(startDate)
                .endAt(endDate)
                .build();

        Cycle cycle2 = new Cycle();
        cycle2.setId("cycleid");
        cycle2.setName("cyclename");
        cycle2.setDescription("cycledescription");
        cycle2.setStartAt(startDate);
        cycle2.setEndAt(endDate);
        cycle2.setIterations(iterationMap);
        cycle2.setSprints(sprintMap);

        assertNotNull(cycle1);
        assertNotNull(cycle1.getId());
        assertNotNull(cycle1.getDescription());
        assertNotNull(cycle1.getStartAt());
        assertNotNull(cycle1.getEndAt());

        assertEquals(cycle1.getId(), cycle2.getId());
        assertEquals(cycle1.getDescription(), cycle2.getDescription());
        assertEquals(cycle1.getName(), cycle2.getName());

        assertNotNull(cycle1.getIterations());
        assertNotNull(cycle1.getSprints());

        assertFalse(cycle1.getIterations().isEmpty());
        assertFalse(cycle1.getSprints().isEmpty());

        assertEquals(cycle1.getIterations(), cycle2.getIterations());
        assertEquals(cycle1.getSprints(), cycle2.getSprints());

        assertEquals(cycle1, cycle2);
        assertEquals(cycle1.hashCode(), cycle2.hashCode());
        assertEquals(cycle1.toString(), cycle2.toString());

        Source source1 = GivenIdentify.getSource();

        cycle1.addSource(source1);
        assertNotNull(cycle1.getSources());
        assertFalse(cycle1.getSources().isEmpty());
        assertTrue(cycle1.getSources().size() == 1);

        Source source2 = cycle1.findSource(source1);
        assertSame(source1, source2);

        Source source3 = cycle1.removeSource(source1);

        assertSame(source1, source3);

    }
}