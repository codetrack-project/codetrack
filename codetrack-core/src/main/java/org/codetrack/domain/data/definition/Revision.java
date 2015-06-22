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
import org.codetrack.domain.data.identify.Author;

import java.util.Date;
import java.util.List;

/**
 * @author josecmoj at 06/06/15.
 */
public class Revision {

    protected String id;

    protected String description;

    protected Date date;

    protected List<Author> author;

    public Revision() {
    }

    private Revision(Builder builder) {
        author = builder.author;
        id = builder.id;
        description = builder.description;
        date = builder.date;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Revision)) return false;
        Revision revision = (Revision) o;
        return Objects.equal(id, revision.id) &&
                Objects.equal(description, revision.description) &&
                Objects.equal(date, revision.date) &&
                Objects.equal(author, revision.author);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, description);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("author", author)
                .add("id", id)
                .add("description", description)
                .add("date", date)
                .toString();
    }

    public static final class Builder {
        private List<Author> author;
        private String id;
        private String description;
        private Date date;

        private Builder() {
        }

        public Builder author(final List<Author> author) {
            this.author = author;
            return this;
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

        public Revision build() {
            return new Revision(this);
        }
    }
}
