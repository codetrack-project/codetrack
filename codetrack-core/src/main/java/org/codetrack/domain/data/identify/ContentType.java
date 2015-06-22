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

/**
 * @author josecmoj at 08/06/15.
 */
@org.codetrack.annotation.identify.Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class ContentType {

    private String id;

    private String description;

    private String extension;

    public ContentType() {
    }

    private ContentType(Builder builder) {
        setDescription(builder.description);
        setId(builder.id);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContentType)) return false;
        ContentType that = (ContentType) o;
        return Objects.equal(getId(), that.getId()) &&
                Objects.equal(getDescription(), that.getDescription()) &&
                Objects.equal(getExtension(), that.getExtension());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getDescription(), getExtension());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("description", description)
                .add("id", id)
                .add("extension", extension)
                .toString();
    }

    /**
     * Builder class to ContentType
     */
    public static final class Builder {
        private String description;
        private String id;
        private String extension;

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

        public Builder extension(final String extension) {
            this.extension = extension;
            return this;
        }

        public ContentType build() {
            return new ContentType(this);
        }
    }
}
