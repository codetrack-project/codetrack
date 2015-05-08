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

package org.codetrack.database.connection.file;

import com.google.common.base.Objects;
import org.codetrack.database.data.Database;
import org.codetrack.database.data.Project;

import java.util.Date;

/**
 * This is Database class graph object
 *
 * @author josecmoj at 02/05/15.
 */
public class FileDatabase implements Database {

    private static final long serialVersionUID = -1L;

    /**
     * Name of database
     */
    private String name;

    /**
     * Reference to project
     */
    private Project project;

    /**
     * Last update date
     */
    private Date lastUpdate;

    /**
     * Hash code on load database time
     */
    private transient int loadedHashCode;

    public FileDatabase() {
    }

    /**
     * Constructor with builder instance
     *
     * @param builder
     */
    private FileDatabase(Builder builder) {
        setLastUpdate(builder.lastUpdate);
        setName(builder.name);
        setProject(builder.project);
    }

    /**
     * Access to new Builder instance
     *
     * @return new Database.Builder instance
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public Date getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
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

    /**
     * Indicate if Database graph is modified
     *
     * @return
     */
    @Override
    public boolean isModified() {
        return (loadedHashCode != hashCode());
    }

    /**
     * Mark load time hash code
     */
    @Override
    public void markIsLoaded() {
        loadedHashCode = hashCode();
    }

    /**
     * Builder class to Database
     */
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

        public FileDatabase build() {
            FileDatabase ndb = new FileDatabase(this);
            ndb.markIsLoaded();
            return ndb;
        }
    }
}
