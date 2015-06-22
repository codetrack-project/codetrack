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
 * One Sprint in software development cycle
 *
 * @author josecmoj at 29/05/15.
 * @see org.codetrack.annotation.temporal.Sprint
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class Sprint extends Temporal {

    /**
     * Reference to Cycle (optional)
     */
    protected Cycle cycle;

    public Sprint() {
        super();
    }

    private Sprint(Builder builder) {
        setDescription(builder.description);
        setId(builder.id);
        setName(builder.name);
        setStartAt(builder.startAt);
        setEndAt(builder.endAt);
        setSources(builder.sources);
        setCycle(builder.cycle);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;
        if (!super.equals(o)) return false;
        Sprint sprint = (Sprint) o;
        return Objects.equal(getCycle(), sprint.getCycle());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("cycle", cycle)
                .toString();
    }

    public static final class Builder {
        private String description;
        private String id;
        private String name;
        private Date startAt;
        private Date endAt;
        private Map<String, Source> sources;
        private Cycle cycle;

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

        public Builder cycle(final Cycle cycle) {
            this.cycle = cycle;
            return this;
        }

        public Sprint build() {
            return new Sprint(this);
        }
    }
}
