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

import com.google.common.base.Objects;
import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.identify.Source;

import java.util.Date;
import java.util.Map;

/**
 * One Cycle in software development process
 *
 * @author josecmoj at 29/05/15.
 * @see org.codetrack.annotation.temporal.Cycle
 * @see Iteration
 * @see Sprint
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class Cycle extends Temporal {

    /**
     * Map of Iteration in the Cycle
     */
    protected Map<String, Iteration> iterations;

    /**
     * Map of Sprint in the Cycle
     */
    protected Map<String, Sprint> sprints;

    public Cycle() {
        super();
    }

    private Cycle(Builder builder) {
        setDescription(builder.description);
        setId(builder.id);
        setName(builder.name);
        setStartAt(builder.startAt);
        setEndAt(builder.endAt);
        setSources(builder.sources);
        setIterations(builder.iterations);
        setSprints(builder.sprints);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Map<String, Iteration> getIterations() {
        return iterations;
    }

    public void setIterations(Map<String, Iteration> iterations) {
        this.iterations = iterations;
    }

    public Map<String, Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(Map<String, Sprint> sprints) {
        this.sprints = sprints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cycle)) return false;
        if (!super.equals(o)) return false;
        Cycle cycle = (Cycle) o;
        return Objects.equal(getIterations(), cycle.getIterations()) &&
                Objects.equal(getSprints(), cycle.getSprints());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("iterations", iterations)
                .add("sprints", sprints)
                .toString();
    }

    public static final class Builder {
        private String description;
        private String id;
        private String name;
        private Date startAt;
        private Date endAt;
        private Map<String, Source> sources;
        private Map<String, Iteration> iterations;
        private Map<String, Sprint> sprints;

        private Builder() {
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder startAt(final Date startAt) {
            this.startAt = startAt;
            return this;
        }

        public Builder endAt(final Date endAt) {
            this.endAt = endAt;
            return this;
        }

        public Builder sources(final Map<String, Source> sources) {
            this.sources = sources;
            return this;
        }

        public Builder iterations(final Map<String, Iteration> iterations) {
            this.iterations = iterations;
            return this;
        }

        public Builder sprints(final Map<String, Sprint> sprints) {
            this.sprints = sprints;
            return this;
        }

        public Cycle build() {
            return new Cycle(this);
        }
    }
}
