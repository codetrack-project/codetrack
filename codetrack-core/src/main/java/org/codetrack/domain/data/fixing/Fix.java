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

package org.codetrack.domain.data.fixing;

import com.google.common.base.Objects;
import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.ProjectItem;
import org.codetrack.domain.data.definition.Issue;
import org.codetrack.domain.data.identify.Source;
import org.codetrack.domain.data.temporal.Cycle;
import org.codetrack.domain.data.temporal.Iteration;
import org.codetrack.domain.data.temporal.Sprint;

import java.util.Map;

/**
 * @author josecmoj at 29/05/15.
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class Fix extends ProjectItem {

    private String id;

    private String observation;

    private FixType type;

    private Issue issue;

    private Cycle cycle;

    private Iteration iteration;

    private Sprint sprint;

    private Map<String, Source> sources;

    public Fix(){
        super();
    }

    private Fix(Builder builder) {
        setCycle(builder.cycle);
        setId(builder.id);
        setObservation(builder.observation);
        setType(builder.type);
        setIssue(builder.issue);
        setIteration(builder.iteration);
        setSprint(builder.sprint);
        setSources(builder.sources);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Fix copy) {
        Builder builder = new Builder();
        builder.cycle = copy.cycle;
        builder.id = copy.id;
        builder.observation = copy.observation;
        builder.type = copy.type;
        builder.issue = copy.issue;
        builder.iteration = copy.iteration;
        builder.sprint = copy.sprint;
        builder.sources = copy.sources;
        return builder;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Iteration getIteration() {
        return iteration;
    }

    public void setIteration(Iteration iteration) {
        this.iteration = iteration;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Map<String, Source> getSources() {
        return sources;
    }

    public void setSources(Map<String, Source> sources) {
        this.sources = sources;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public FixType getType() {
        return type;
    }

    public void setType(FixType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fix)) return false;
        Fix fix = (Fix) o;
        return Objects.equal(getId(), fix.getId()) &&
                Objects.equal(getObservation(), fix.getObservation()) &&
                Objects.equal(getType(), fix.getType()) &&
                Objects.equal(getIssue(), fix.getIssue()) &&
                Objects.equal(getCycle(), fix.getCycle()) &&
                Objects.equal(getIteration(), fix.getIteration()) &&
                Objects.equal(getSprint(), fix.getSprint()) &&
                Objects.equal(getSources(), fix.getSources());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public static final class Builder {
        private Cycle cycle;
        private String id;
        private String observation;
        private FixType type;
        private Issue issue;
        private Iteration iteration;
        private Sprint sprint;
        private Map<String, Source> sources;

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

        public Builder observation(final String observation) {
            this.observation = observation;
            return this;
        }

        public Builder type(final FixType type) {
            this.type = type;
            return this;
        }

        public Builder issue(final Issue issue) {
            this.issue = issue;
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

        public Builder sources(final Map<String, Source> sources) {
            this.sources = sources;
            return this;
        }

        public Fix build() {
            return new Fix(this);
        }
    }
}
