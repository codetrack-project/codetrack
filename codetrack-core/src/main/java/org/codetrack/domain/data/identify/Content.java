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
import com.google.common.base.Strings;
import org.codetrack.annotation.definition.Feature;
import org.codetrack.domain.data.ProjectItem;

/**
 * @author josecmoj at 08/06/15.
 */
@org.codetrack.annotation.identify.Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class Content extends ProjectItem{

    /**
     * Content path
     */
    private Path path;

    /**
     * Content name
     */
    private String name;

    /**
     * Content type
     */
    private ContentType contentType;

    public Content() {
    }

    private Content(Builder builder) {
        setContentType(builder.contentType);
        setPath(builder.path);
        setName(builder.name);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Content)) return false;
        Content content = (Content) o;
        return Objects.equal(getPath(), content.getPath()) &&
                Objects.equal(getName(), content.getName()) &&
                Objects.equal(getContentType(), content.getContentType());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPath(), getName(), getContentType());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("contentType", contentType)
                .add("path", path)
                .add("name", name)
                .toString();
    }

    @Override
    public String getId() {
        return this.name + ((this.path != null) && (!Strings.isNullOrEmpty(this.path.getUrl())) ? this.path.getUrl() : "");
    }

    /**
     * Builder class to Content
     */
    public static final class Builder {
        private ContentType contentType;
        private Path path;
        private String name;

        private Builder() {
        }

        public Builder contentType(final ContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder path(final Path path) {
            this.path = path;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Content build() {
            return new Content(this);
        }
    }
}
