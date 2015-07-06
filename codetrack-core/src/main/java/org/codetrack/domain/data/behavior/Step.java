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

package org.codetrack.domain.data.behavior;

import com.google.common.base.Objects;
import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.identify.Source;

import java.util.Map;

/**
 * @author josecmoj at 04/06/15.
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class Step extends Behavior {


    private Map<String, Condition> conditions;

    public Step() {
    }

    private Step(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
        setSources(builder.sources);
        conditions = builder.conditions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Step copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.name = copy.name;
        builder.description = copy.description;
        builder.sources = copy.sources;
        builder.conditions = copy.conditions;
        return builder;
    }

    public static class Builder {
        private String id;
        private String name;
        private String description;
        private Map<String, Source> sources;
        private Map<String, Condition> conditions;

        private Builder() {
        }

        public Step build() {
            return new Step();
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
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

        public Builder conditions(final Map<String, Condition> conditions) {
            this.conditions = conditions;
            return this;
        }
    }

    public Map<String, Condition> getConditions() {

        return conditions;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Step)) return false;
        if (!super.equals(o)) return false;
        Step step = (Step) o;
        return Objects.equal(conditions, step.conditions);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("conditions", conditions)
                .toString();
    }


}
