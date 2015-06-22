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

package org.codetrack.domain.data.temporal;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.identify.Source;

import java.util.Date;
import java.util.Map;

/**
 * Temporal base class represents time base annotations
 *
 * @author josecmoj at 02/06/15.
 * @see Source
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public abstract class Temporal {

    /**
     * Id of temporal instance
     */
    protected String id;

    /**
     * Name of temporal instance
     */

    protected String name;

    /**
     * Description of temporal instance
     */
    protected String description;

    /**
     * Start date of temporal instance
     */
    protected Date startAt;

    /**
     * End date of temporal instance
     */
    protected Date endAt;

    /**
     * The sources references
     */
    protected Map<String, Source> sources;

    public Temporal() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
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

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Temporal addSource(Source source) {

        if (sources == null)
            sources = Maps.newHashMap();

        sources.put(source.getName(), source);

        return this;
    }

    public Source removeSource(Source source) {

        if (sources != null)
            if (sources.containsKey(source.getName()))
                return sources.remove(source.getName());

        return null;
    }

    public Source findSource(Source source) {

        if (sources != null)
            if (sources.containsKey(source.getName()))
                return sources.get(source.getName());

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temporal temporal = (Temporal) o;
        return Objects.equal(getId(), temporal.getId()) &&
                Objects.equal(getName(), temporal.getName()) &&
                Objects.equal(getDescription(), temporal.getDescription()) &&
                Objects.equal(getStartAt(), temporal.getStartAt()) &&
                Objects.equal(getEndAt(), temporal.getEndAt()) &&
                Objects.equal(getSources(), temporal.getSources());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("description", description)
                .add("endAt", endAt)
                .add("id", id)
                .add("name", name)
                .add("startAt", startAt)
                .toString();
    }

}
