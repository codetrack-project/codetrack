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

/**
 * @author josecmoj at 06/06/15.
 */
@Cycles({
        @Cycle(id = "RELEASE-1.0", startAt = "2015/O5/01", endAt = "2015/07/31"),
        @Cycle(id = "RELEASE-1.1", startAt = "2015/08/01", endAt = "2015/08/30"),
        @Cycle(id = "RELEASE-1.2", startAt = "2015/09/01", endAt = "2015/11/10")
})
@Iterations({
        @Iteration(id = "IT012", cycle = @Cycle(id = "RELEASE-1.0")),
        @Iteration(id = "IT013", cycle = @Cycle(id = "RELEASE-1.0")),
        @Iteration(id = "IT014", cycle = @Cycle(id = "RELEASE-1.1")),
        @Iteration(id = "IT015", cycle = @Cycle(id = "RELEASE-1.1")),
        @Iteration(id = "IT016", cycle = @Cycle(id = "RELEASE-1.2"))
}) package org.codetrack.annotation.sample;

import org.codetrack.annotation.temporal.Cycle;
import org.codetrack.annotation.temporal.Cycles;
import org.codetrack.annotation.temporal.Iteration;
import org.codetrack.annotation.temporal.Iterations;
