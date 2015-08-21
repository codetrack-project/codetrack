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

package org.codetrack.domain.data.behavior;

import com.google.common.base.Objects;
import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.ProjectItem;
import org.codetrack.domain.data.identify.Source;

import java.util.Map;

/**
 * @author josecmoj at 04/06/15.
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public abstract class Behavior extends ProjectItem {

    protected String id;

    protected String name;

    protected String description;

    protected Map<String, Source> sources;

    public Behavior() {
        super();
    }

    @Override
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(o instanceof Behavior)) return false;
        Behavior behavior = (Behavior) o;
        return Objects.equal(getId(), behavior.getId()) &&
                Objects.equal(getName(), behavior.getName()) &&
                Objects.equal(getDescription(), behavior.getDescription()) &&
                Objects.equal(getSources(), behavior.getSources());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("sources", sources)
                .toString();
    }

}
