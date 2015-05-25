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
import com.google.common.collect.Maps;
import org.codetrack.database.data.Database;
import org.codetrack.database.data.Project;
import org.codetrack.database.exception.DatabaseError;

import java.util.Date;
import java.util.Map;

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
     * Reference to selected project
     */
    private Project project;

    /**
     * Map of all database projects
     */
    private Map<String, Project> projectMap;

    /**
     * Last update date
     */
    private Date lastUpdate;

    /**
     * Hash code on load database time
     */
    private transient int loadedHashCode;

    public FileDatabase() {
        projectMap = Maps.newTreeMap();
    }

    private FileDatabase(Builder builder) {
        setLastUpdate(builder.lastUpdate);
        setName(builder.name);
        projectMap = Maps.newTreeMap();
        for (Project project : builder.projectMap.values())
            addProject(project);
    }

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

    @Override
    public void addProject(Project project) {
        projectMap.put(project.getId(), project);
        setLastUpdate(new Date());
    }

    @Override
    public void removeProject(Project project) {
        projectMap.remove(project);
        setLastUpdate(new Date());
    }

    @Override
    public Project findProject(String id) {

        if (projectMap.containsKey(id))
            return projectMap.remove(id);
        else
            throw new DatabaseError("Project " + id + " not found", DatabaseError.DATABASE_PROJECT_NOT_FOUND);

    }

    @Override
    public Project selectProject(String id) {

        if (projectMap.containsKey(id)) {
            project = projectMap.get(id);
            return project;
        } else
            throw new DatabaseError("Project " + id + " not found", DatabaseError.DATABASE_PROJECT_NOT_FOUND);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileDatabase that = (FileDatabase) o;
        return Objects.equal(loadedHashCode, that.loadedHashCode) &&
                Objects.equal(getName(), that.getName()) &&
                Objects.equal(getProject(), that.getProject()) &&
                Objects.equal(projectMap, that.projectMap) &&
                Objects.equal(getLastUpdate(), that.getLastUpdate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getLastUpdate());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("lastUpdate", lastUpdate)
                .toString();
    }


    public static final class Builder {
        private Date lastUpdate;
        private String name;
        private Map<String, Project> projectMap = Maps.newTreeMap();

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

        public Builder projectMap(final Map<String, Project> projectMap) {
            this.projectMap = projectMap;
            return this;
        }

        public Builder putProject(Project project) {

            this.projectMap.put(project.getId(), project);

            return this;
        }

        public FileDatabase build() {
            return new FileDatabase(this);
        }
    }
}
