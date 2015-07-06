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
import org.codetrack.domain.data.ProjectItem;

/**
 * @author josecmoj at 06/06/15.
 */
@org.codetrack.annotation.identify.Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class Path extends ProjectItem {

    /**
     * Path id
     */
    private String id;

    /**
     * Path url
     */
    private String url;

    public Path() {
    }

    private Path(Builder builder) {
        setId(builder.id);
        setUrl(builder.url);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path)) return false;
        Path path = (Path) o;
        return Objects.equal(getId(), path.getId()) &&
                Objects.equal(getUrl(), path.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getUrl());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("url", url)
                .toString();
    }

    /**
     * Builder class to Path
     */
    public static final class Builder {
        private String id;
        private String url;

        private Builder() {
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder url(final String url) {
            this.url = url;
            return this;
        }

        public Path build() {
            return new Path(this);
        }
    }
}
