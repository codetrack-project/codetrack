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

package org.codetrack.domain.data.definition;

import com.google.common.base.Objects;
import org.codetrack.domain.data.ProjectItem;
import org.codetrack.domain.data.identify.Author;

import java.util.Date;
import java.util.List;

/**
 * @author josecmoj at 06/06/15.
 */
public class Revision extends ProjectItem{

    protected String id;

    protected String description;

    protected Date date;

    protected List<Author> authors;

    public Revision() {
    }

    private Revision(Builder builder) {
        setId(builder.id);
        setDescription(builder.description);
        setDate(builder.date);
        setAuthors(builder.authors);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Revision copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.description = copy.description;
        builder.date = copy.date;
        builder.authors = copy.authors;
        return builder;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Revision)) return false;
        Revision revision = (Revision) o;
        return Objects.equal(getId(), revision.getId()) &&
                Objects.equal(getDescription(), revision.getDescription()) &&
                Objects.equal(getDate(), revision.getDate()) &&
                Objects.equal(getAuthors(), revision.getAuthors());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getDescription(), getDate());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("description", description)
                .add("date", date)
                .add("authors", authors)
                .toString();
    }


    public static final class Builder {
        private String id;
        private String description;
        private Date date;
        private List<Author> authors;

        private Builder() {
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder date(final Date date) {
            this.date = date;
            return this;
        }

        public Builder authors(final List<Author> authors) {
            this.authors = authors;
            return this;
        }

        public Revision build() {
            return new Revision(this);
        }
    }
}
