/*
 * Copyright 2015 the original author or authors members of codetrack.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/*
 * Copyright 2015 the original author or authors members of codetrack.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.codetrack.database.data;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * Project class store all information about tracked project
 *
 * @author josecmoj at 02/05/15.
 */
public class Project implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * Project Id
     */
    private String id;

    /**
     * Project name
     */
    private String name;

    /**
     * Project description
     */
    private String description;

    public Project() {
    }

    /**
     * Constructor with Project.Builder instance
     *
     * @param builder instance
     */
    private Project(Builder builder) {
        setDescription(builder.description);
        setId(builder.id);
        setName(builder.name);
    }

    /**
     * Access to new Project.Builder instance
     *
     * @return Project.Builder instance
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (getId() != null ? !getId().equals(project.getId()) : project.getId() != null) return false;
        if (getName() != null ? !getName().equals(project.getName()) : project.getName() != null) return false;
        return !(getDescription() != null ? !getDescription().equals(project.getDescription()) : project.getDescription() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("description", description)
                .add("id", id)
                .add("name", name)
                .toString();
    }

    /**
     * Project.Builder class
     */
    public static final class Builder {
        private String description;
        private String id;
        private String name;

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

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }
}
