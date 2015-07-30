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
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.ProjectItem;

import java.util.Map;

/**
 * Author entity data class
 * @author josecmoj at 29/05/15.
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public class Author extends ProjectItem{

    private String name;

    private String fullName;

    private Email email;

    private Map<String, Source> sources;

    public Author() {
        super();
    }

    private Author(Builder builder) {
        setEmail(builder.email);
        setName(builder.name);
        setFullName(builder.fullName);
        setSources(builder.sources);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Author copy) {
        Builder builder = new Builder();
        builder.email = copy.email;
        builder.name = copy.name;
        builder.fullName = copy.fullName;
        builder.sources = copy.sources;
        return builder;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equal(getName(), author.getName()) &&
                Objects.equal(getFullName(), author.getFullName()) &&
                Objects.equal(getEmail(), author.getEmail()) &&
                Objects.equal(getSources(), author.getSources());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getFullName(), getEmail());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("email", email)
                .add("name", name)
                .add("fullName", fullName)
                .add("sources", sources)
                .toString();
    }

    @Override
    public String getId() {
        return this.name + ((this.email != null) && (!Strings.isNullOrEmpty(this.email.getAddress())) ? this.email.getAddress() : "");
    }

    public static final class Builder {
        private Email email;
        private String name;
        private String fullName;
        private Map<String, Source> sources;

        private Builder() {
        }

        public Builder email(final Email email) {
            this.email = email;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder fullName(final String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder sources(final Map<String, Source> sources) {
            this.sources = sources;
            return this;
        }

        public Author build() {
            return new Author(this);
        }
    }
}
