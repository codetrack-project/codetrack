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

package org.codetrack.database.file;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.database.Database;
import org.codetrack.database.DatabaseConnection;
import org.codetrack.database.exception.DatabaseError;
import org.codetrack.domain.data.Project;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * This is Database class graph object
 *
 * @author josecmoj at 02/05/15.
 */
@Product(id = "codetrack-file-database")
@Feature(id = "#4-DATABASE")
public class FileDatabase extends Observable implements Database {

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

    private transient FileDatabaseState state;
    private transient FileDatabaseConnection connection;

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


    /**
     * Access the last update of database
     *
     * @return the last update date
     */
    @Override
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Setting the last update file
     *
     * @param lastUpdate - the last update date
     */
    @Override
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Project getProject() {
        return project;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Access the FileDatabase state
     * @return State of database
     * @see FileDatabaseState
     */
    public FileDatabaseState getState() {
        return state;
    }

    /**
     * Setting the database state
     * @param state - state of database
     */
    public void setState(FileDatabaseState state) {
        this.state = state;
    }

    /**
     * Indicate if Database graph is modified
     *
     * @return
     */
    @Override
    public boolean isModified() {
        return (FileDatabaseState.CHANGED.equals(this.state));
    }

    /**
     * Mark load time hash code
     * {@inheritDoc}
     */
    @Override
    public void markIsLoaded() {
        this.state = FileDatabaseState.LOADED;
    }

    @Override
    public Project createProject(String id, String name) {
        FileProject project = FileProject.newBuilder()
                .id(id)
                .name(name)
                .build();

        addProject(project);

        return project;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProject(Project project) {
        projectMap.put(project.getId(), project);
        this.changed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeProject(Project project) {
        projectMap.remove(project);
        this.changed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Project findProject(String id) {

        if (projectMap.containsKey(id))
            return projectMap.get(id);
        else
            throw new DatabaseError("FileProject " + id + " not found", DatabaseError.DATABASE_PROJECT_NOT_FOUND);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Project selectProject(String id) {

        if (projectMap.containsKey(id)) {
            project = projectMap.get(id);
            return project;
        } else
            throw new DatabaseError("FileProject " + id + " not found", DatabaseError.DATABASE_PROJECT_NOT_FOUND);

    }

    /**
     * This notify database connection to save database data
     * @see Database#save()
     */
    @Override
    public void save() {

        this.notifyObservers();

    }

    @Override
    public List<Project> allProjects() {
        return Lists.newArrayList(this.projectMap.values());
    }


    @Override
    public DatabaseConnection getConnection() {
        return this.connection;
    }

    @Override
    public void setConnection(DatabaseConnection connection) {
        this.connection = (FileDatabaseConnection) connection;
    }

    /**
     * This method modified database state to CHANGED
     */
    @Override
    public void changed() {
        setLastUpdate(new Date());
        setState(FileDatabaseState.CHANGED);
    }

    /*
     * @see Database#equals(Object)
     */
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

    /*
     * @see Database#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getLastUpdate());
    }

    /*
     * @see Database#toString()
     */
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
