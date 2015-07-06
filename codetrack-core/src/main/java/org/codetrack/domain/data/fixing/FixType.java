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

/**
 * @author josecmoj at 04/06/15.
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class FixType {

    private String name;

    private String description;

    public FixType(String name, String description) {
        this.description = description;
        this.name = name;
    }

    private FixType(Builder builder) {
        setName(builder.name);
        setDescription(builder.description);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(FixType copy) {
        Builder builder = new Builder();
        builder.name = copy.name;
        builder.description = copy.description;
        return builder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FixType)) return false;
        FixType fixType = (FixType) o;
        return Objects.equal(getName(), fixType.getName()) &&
                Objects.equal(getDescription(), fixType.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getDescription());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("description", description)
                .toString();
    }


    public static final class Builder {
        private String name;
        private String description;

        private Builder() {
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public FixType build() {
            return new FixType(this);
        }
    }
}
