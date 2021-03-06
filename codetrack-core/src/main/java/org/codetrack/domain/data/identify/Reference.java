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

import java.util.Map;

/**
 * @author josecmoj at 29/05/15.
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class Reference extends ProjectItem {

    private String id;

    private String name;

    private Map<String, Source> sources;

    public Reference() {
        super();
    }

    private Reference(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setSources(builder.sources);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Reference copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.name = copy.name;
        builder.sources = copy.sources;
        return builder;
    }

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

    public Map<String, Source> getSources() {
        return sources;
    }

    public void setSources(Map<String, Source> sources) {
        this.sources = sources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reference)) return false;
        Reference reference = (Reference) o;
        return Objects.equal(getId(), reference.getId()) &&
                Objects.equal(getName(), reference.getName()) &&
                Objects.equal(getSources(), reference.getSources());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("sources", sources)
                .toString();
    }

    public static final class Builder {
        private String id;
        private String name;
        private Map<String, Source> sources;

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

        public Builder sources(final Map<String, Source> sources) {
            this.sources = sources;
            return this;
        }

        public Reference build() {
            return new Reference(this);
        }
    }
}
