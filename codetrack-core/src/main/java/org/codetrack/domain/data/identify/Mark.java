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

package org.codetrack.domain.data.identify;

import com.google.common.base.Objects;
import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.ProjectItem;

/**
 * @author josecmoj at 05/06/15.
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class Mark extends ProjectItem{

    private String id;

    private String name;

    private String description;

    public Mark() {
    }

    private Mark(Builder builder) {
        setDescription(builder.description);
        setId(builder.id);
        setName(builder.name);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (!(o instanceof Mark)) return false;
        Mark mark = (Mark) o;
        return Objects.equal(getId(), mark.getId()) &&
                Objects.equal(getName(), mark.getName()) &&
                Objects.equal(getDescription(), mark.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getDescription());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("description", description)
                .add("id", id)
                .add("name", name)
                .toString();
    }

    /**
     * Builder to Mark
     */
    public static final class Builder {
        private String description;
        private String id;
        private String name;

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

        public Mark build() {
            return new Mark(this);
        }
    }
}
