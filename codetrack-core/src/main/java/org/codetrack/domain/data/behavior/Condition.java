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
 * @author josecmoj at 06/06/15.
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class Condition extends Behavior {

    private String expression;

    public Condition() {
        super();
    }

    private Condition(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
        setSources(builder.sources);
        setExpression(builder.expression);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Condition copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.name = copy.name;
        builder.description = copy.description;
        builder.sources = copy.sources;
        builder.expression = copy.expression;
        return builder;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Condition)) return false;
        if (!super.equals(o)) return false;
        Condition condition = (Condition) o;
        return Objects.equal(getExpression(), condition.getExpression());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getExpression());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("expression", expression)
                .toString();
    }

    public static final class Builder {
        private String id;
        private String name;
        private String description;
        private Map<String, Source> sources;
        private String expression;

        private Builder() {
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

        public Builder expression(final String expression) {
            this.expression = expression;
            return this;
        }

        public Condition build() {
            return new Condition(this);
        }
    }
}
