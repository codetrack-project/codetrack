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

package org.codetrack.domain.data.definition;

import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.identify.Source;
import org.codetrack.domain.data.temporal.Cycle;
import org.codetrack.domain.data.temporal.Iteration;
import org.codetrack.domain.data.temporal.Sprint;

import java.util.Map;

/**
 * @author josecmoj at 29/05/15.
 */
@Product(id = "codetrack-core")
@org.codetrack.annotation.definition.Feature(id = "#4-DATABASE")
public class Issue extends Definition {

    public Issue() {
        super();
    }

    private Issue(Builder builder) {
        setCycle(builder.cycle);
        setId(builder.id);
        setDescription(builder.description);
        setSources(builder.sources);
        setIteration(builder.iteration);
        setSprint(builder.sprint);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Cycle cycle;
        private String id;
        private String description;
        private Map<String, Source> sources;
        private Iteration iteration;
        private Sprint sprint;

        private Builder() {
        }

        public Builder cycle(final Cycle cycle) {
            this.cycle = cycle;
            return this;
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder sources(final Map<String, Source> sources) {
            this.sources = sources;
            return this;
        }

        public Builder iteration(final Iteration iteration) {
            this.iteration = iteration;
            return this;
        }

        public Builder sprint(final Sprint sprint) {
            this.sprint = sprint;
            return this;
        }

        public Issue build() {
            return new Issue(this);
        }
    }
}
