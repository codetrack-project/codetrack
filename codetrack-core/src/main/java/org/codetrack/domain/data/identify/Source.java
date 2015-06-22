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
import org.codetrack.annotation.temporal.Cycle;
import org.codetrack.annotation.temporal.Cycles;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author josecmoj at 02/06/15.
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
@Cycles({
        @Cycle(id = "1.1-RELEASE")
})
public class Source {

    /**
     * Name of source
     */
    private String name;

    /**
     * Url of source
     */
    private String url;

    /**
     * Description of source (optional)
     */
    private String description;

    /**
     * Observation about source
     */
    private String observation;

    /**
     * Marks of source
     */
    private Map<String, Mark> marks;

    /**
     * Source type
     */
    private SourceType type;

    /**
     * Content where the source is found
     */
    private Content content;

    public Source() {
    }

    private Source(Builder builder) {
        setContent(builder.content);
        setName(builder.name);
        setUrl(builder.url);
        setDescription(builder.description);
        setObservation(builder.observation);
        setMarks(builder.marks);
        setType(builder.type);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Mark> getMarks() {
        return marks;
    }

    public void setMarks(Map<String, Mark> marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public SourceType getType() {
        return type;
    }

    public void setType(SourceType type) {
        this.type = type;
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
        if (!(o instanceof Source)) return false;
        Source source = (Source) o;
        return Objects.equal(getName(), source.getName()) &&
                Objects.equal(getUrl(), source.getUrl()) &&
                Objects.equal(getDescription(), source.getDescription()) &&
                Objects.equal(getObservation(), source.getObservation()) &&
                Objects.equal(getMarks(), source.getMarks()) &&
                Objects.equal(getType(), source.getType()) &&
                Objects.equal(getContent(), source.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getUrl());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("content", content)
                .add("name", name)
                .add("url", url)
                .add("description", description)
                .add("observation", observation)
                .add("marks", marks)
                .add("type", type)
                .toString();
    }

    /**
     * Add an Mark instance
     *
     * @param mark instance to put in map reference
     * @return this
     */
    public Source addMark(Mark mark) {
        if (marks == null)
            marks = new Hashtable<>();

        marks.put(mark.getId(), mark);

        return this;
    }

    /**
     * Remove an Mark instance
     *
     * @param mark instance to remove
     * @return Mark instance or null
     */
    public Mark removeMark(Mark mark) {

        if (marks != null)
            if (marks.containsKey(mark.getId()))
                return marks.remove(mark.getId());

        return null;
    }

    /**
     * Find an Mark instance
     *
     * @param markId of an Mark instance
     * @return Mark instance or null
     */
    public Mark findMark(String markId) {

        if (marks != null)
            if (marks.containsKey(markId))
                return marks.get(markId);

        return null;
    }

    /**
     * Builder to Source
     */
    public static final class Builder {
        private Content content;
        private String name;
        private String url;
        private String description;
        private String observation;
        private Map<String, Mark> marks;
        private SourceType type;

        private Builder() {
        }

        public Builder content(final Content content) {
            this.content = content;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder url(final String url) {
            this.url = url;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder observation(final String observation) {
            this.observation = observation;
            return this;
        }

        public Builder marks(final Map<String, Mark> marks) {
            this.marks = marks;
            return this;
        }

        public Builder type(final SourceType type) {
            this.type = type;
            return this;
        }

        public Builder addMark(Mark mark) {

            if (this.marks == null)
                this.marks = new HashMap<>();

            this.marks.put(mark.getId(), mark);
            return this;
        }

        public Source build() {
            return new Source(this);
        }
    }
}
