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

/**
 * @author josecmoj at 02/06/15.
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class SourceType {

    private String name;

    private String extension;

    private String description;

    public SourceType() {
    }

    private SourceType(Builder builder) {
        setDescription(builder.description);
        setName(builder.name);
        setExtension(builder.extension);
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
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
        if (!(o instanceof SourceType)) return false;
        SourceType that = (SourceType) o;
        return Objects.equal(getName(), that.getName()) &&
                Objects.equal(getExtension(), that.getExtension());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getExtension());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("description", description)
                .add("name", name)
                .add("extension", extension)
                .toString();
    }

    /**
     * Builder to SourceType
     */
    public static final class Builder {
        private String description;
        private String name;
        private String extension;

        private Builder() {
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder extension(final String extension) {
            this.extension = extension;
            return this;
        }

        public SourceType build() {
            return new SourceType(this);
        }
    }
}
