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
import java.util.Date;

/**
 * @author josecmoj at 02/05/15.
 */
public class Database implements Serializable {

    private static final long serialVersionUID = -1L;

    private String name;

    private Project project;

    private Date lastUpdate;

    private transient int loadedHashCode;

    public Database() {
    }

    private Database(Builder builder) {
        setLastUpdate(builder.lastUpdate);
        setName(builder.name);
        setProject(builder.project);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Database database = (Database) o;

        if (getName() != null ? !getName().equals(database.getName()) : database.getName() != null) return false;
        if (getProject() != null ? !getProject().equals(database.getProject()) : database.getProject() != null)
            return false;
        return !(getLastUpdate() != null ? !getLastUpdate().equals(database.getLastUpdate()) : database.getLastUpdate() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getProject() != null ? getProject().hashCode() : 0);
        result = 31 * result + (getLastUpdate() != null ? getLastUpdate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("lastUpdate", lastUpdate)
                .add("name", name)
                .add("project", project)
                .toString();
    }


    public static final class Builder {
        private Date lastUpdate;
        private String name;
        private Project project;

        private Builder() {
        }

        public Builder lastUpdate(final Date lastUpdate) {
            this.lastUpdate = lastUpdate;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder project(final Project project) {
            this.project = project;
            return this;
        }

        public Database build() {
            Database ndb = new Database(this);
            ndb.setLastUpdate(new Date());
            ndb.markIsLoaded();
            return ndb;
        }
    }

    public boolean isModified() {
        return (loadedHashCode != hashCode());
    }

    public void markIsLoaded() {
        loadedHashCode = hashCode();
    }
}
