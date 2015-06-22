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

import com.google.common.base.Objects;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.identify.Source;
import org.codetrack.domain.data.temporal.Cycle;
import org.codetrack.domain.data.temporal.Iteration;
import org.codetrack.domain.data.temporal.Sprint;

import java.util.Map;

/**
 * @author josecmoj at 04/06/15.
 */
@Product(id = "codetrack-core")
@org.codetrack.annotation.definition.Feature(id = "#4-DATABASE")
public abstract class Definition {

    protected String id;

    protected String description;

    protected Map<String, Source> sources;

    protected Cycle cycle;

    protected Iteration iteration;

    protected Sprint sprint;

    public Definition() {
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Iteration getIteration() {
        return iteration;
    }

    public void setIteration(Iteration iteration) {
        this.iteration = iteration;
    }

    public Map<String, Source> getSources() {
        return sources;
    }

    public void setSources(Map<String, Source> sources) {
        this.sources = sources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Definition)) return false;
        Definition that = (Definition) o;
        return Objects.equal(getId(), that.getId()) &&
                Objects.equal(getDescription(), that.getDescription()) &&
                Objects.equal(getSources(), that.getSources()) &&
                Objects.equal(getCycle(), that.getCycle()) &&
                Objects.equal(getIteration(), that.getIteration()) &&
                Objects.equal(getSprint(), that.getSprint());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getDescription());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("cycle", cycle)
                .add("id", id)
                .add("description", description)
                .add("sources", sources)
                .add("iteration", iteration)
                .add("sprint", sprint)
                .toString();
    }


}
