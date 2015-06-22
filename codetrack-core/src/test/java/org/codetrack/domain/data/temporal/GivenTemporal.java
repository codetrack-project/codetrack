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

import com.google.common.collect.Maps;
import org.codetrack.domain.data.identify.GivenIdentify;

import java.util.Date;
import java.util.Map;

/**
 * @author josecmoj at 15/06/15.
 */
public class GivenTemporal {

    public static int iterationid = 0;

    public static int sprintid = 0;

    public static int cycleid = 0;

    public static Iteration getIteration() {

        iterationid++;

        return Iteration.newBuilder()
                .id("iterarionid" + iterationid)
                .name("iterationname" + iterationid)
                .description("iterationdescription" + iterationid)
                .startAt(new Date())
                .endAt(new Date())
                .sources(GivenIdentify.getSourceMap())
                .build();

    }

    public static Iteration getFullIteration() {

        Iteration it = getIteration();

        it.setCycle(getCycle());

        return it;
    }

    public static Sprint getSprint() {

        sprintid++;

        return Sprint.newBuilder()
                .id("sprintid" + sprintid)
                .name("sprintname" + sprintid)
                .description("sprintdescription" + sprintid)
                .startAt(new Date())
                .endAt(new Date())
                .sources(GivenIdentify.getSourceMap())
                .build();
    }

    public static Sprint getFullSprint() {

        Sprint sp = getSprint();

        sp.setCycle(getCycle());

        return sp;
    }

    public static Cycle getCycle() {

        cycleid++;

        return Cycle.newBuilder()
                .id("cycleid" + cycleid)
                .name("cyclename" + cycleid)
                .description("cycledescription" + cycleid)
                .startAt(new Date())
                .endAt(new Date())
                .sources(GivenIdentify.getSourceMap())
                .build();
    }

    public static Cycle getFullCycle() {

        Cycle cy = getCycle();

        cy.setIterations(getIterationMap());
        cy.setSprints(getSprintMap());

        return cy;
    }

    public static Map<String, Iteration> getIterationMap() {

        Map<String, Iteration> result = Maps.newHashMap();

        for (int i = 0; i < 4; i++) {

            Iteration it = getIteration();
            result.put(it.getId(), it);

        }

        return result;

    }

    public static Map<String, Sprint> getSprintMap() {

        Map<String, Sprint> result = Maps.newHashMap();

        for (int i = 0; i < 4; i++) {

            Sprint sp = getSprint();
            result.put(sp.getId(), sp);

        }

        return result;

    }
}
