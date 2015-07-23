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

package org.codetrack.repository;

import com.google.common.base.Objects;

/**
 * @author josecmoj at 11/07/15.
 */
public class SearchOneById<T> implements SearchRequest{

    private Class<T> itemClazz;

    private String id;

    private SearchOneById(Builder builder) {
        setItemClazz(builder.itemClazz);
        setId(builder.id);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(SearchOneById copy) {
        Builder builder = new Builder();
        builder.itemClazz = copy.itemClazz;
        builder.id = copy.id;
        return builder;
    }

    public Class<T> getItemClazz() {
        return itemClazz;
    }

    public void setItemClazz(Class<T> itemClazz) {
        this.itemClazz = itemClazz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchOneById)) return false;
        SearchOneById<?> that = (SearchOneById<?>) o;
        return Objects.equal(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public static final class Builder {
        private Class itemClazz;
        private String id;

        private Builder() {
        }

        public Builder itemClazz(final Class itemClazz) {
            this.itemClazz = itemClazz;
            return this;
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public SearchOneById build() {
            return new SearchOneById(this);
        }
    }
}
