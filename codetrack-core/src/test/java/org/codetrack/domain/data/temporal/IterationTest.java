package org.codetrack.domain.data.temporal;

import junit.framework.TestCase;
import org.codetrack.domain.data.identify.GivenIdentify;
import org.codetrack.domain.data.identify.Source;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

/**
 * @author josecmoj at 17/06/15.
 */
public class IterationTest extends TestCase {

    @Test
    public void testNewBuilder() throws Exception {

        Date startDate = new Date();
        Date endDate = new Date();

        Cycle cycle = GivenTemporal.getCycle();

        Map<String, Source> sourceMap = GivenIdentify.getSourceMap();

        Iteration it1 = Iteration.newBuilder()
                .id("itid")
                .name("itname")
                .description("itdescription")
                .startAt(startDate)
                .endAt(endDate)
                .cycle(cycle)
                .sources(sourceMap)
                .build();

        Iteration it2 = new Iteration();

        it2.setId("itid");
        it2.setName("itname");
        it2.setDescription("itdescription");
        it2.setStartAt(startDate);
        it2.setEndAt(endDate);
        it2.setCycle(cycle);
        it2.setSources(sourceMap);

        assertNotNull(it1);
        assertEquals(it1.getId(), it2.getId());
        assertEquals(it1.getName(), it2.getName());
        assertEquals(it1.getDescription(), it2.getDescription());
        assertEquals(it1.getStartAt(), it2.getStartAt());
        assertEquals(it1.getEndAt(), it2.getEndAt());
        assertEquals(it1, it2);

        assertEquals(it1.hashCode(), it2.hashCode());
        assertEquals(it1.toString(), it2.toString());

        assertEquals(it1.getSources(), it2.getSources());

    }
}