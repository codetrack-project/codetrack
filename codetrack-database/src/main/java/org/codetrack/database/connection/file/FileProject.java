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

package org.codetrack.database.connection.file;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import org.codetrack.domain.data.Database;
import org.codetrack.domain.data.Project;
import org.codetrack.domain.data.ProjectItem;

import java.util.SortedMap;
import java.util.concurrent.ConcurrentMap;

/**
 * FileProject class store all information about tracked project
 *
 * @author josecmoj at 02/05/15.
 */
public class FileProject implements Project {

    private static final long serialVersionUID = -1L;

    /**
     * FileProject Id
     */
    private String id;

    /**
     * FileProject name
     */
    private String name;

    /**
     * FileProject description
     */
    private String description;

    private ConcurrentMap<Class,SortedMap<String, ProjectItem>> itemsMap;

    public FileProject() {

        itemsMap = Maps.newConcurrentMap();
    }

    /**
     * Constructor with FileProject.Builder instance
     *
     * @param builder instance
     */
    private FileProject(Builder builder) {
        itemsMap = Maps.newConcurrentMap();
        setDescription(builder.description);
        setId(builder.id);
        setName(builder.name);
        if (builder.database != null)
            builder.database.addProject(this);
    }

    /**
     * Access to new FileProject.Builder instance
     *
     * @return FileProject.Builder instance
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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
     * This method create instance of SortedMap if not
     * exist in the project control itemMap
     *
     * @param projectItem instance
     * @return SortedMap<String,ProjectItem> instance
     */
    private SortedMap<String, ProjectItem> lazyMap(ProjectItem projectItem) {

        SortedMap<String, ProjectItem> map = itemsMap.get(projectItem.getClass());

        if (map == null) {
            map = Maps.newTreeMap();
            itemsMap.put(projectItem.getClass(), map);
        }

        return map;

    }

    /**
     * Find an ProjectItem instance by name
     * @param id String of ProjectItem
     * @return ProjectItem instance. Null if not found
     */
    @Override
    public ProjectItem findById(String id) {

        for (SortedMap<String, ProjectItem> map : itemsMap.values()) {
            if (map.containsKey(id))
                return map.get(id);
        }

        return null;

    }

    /**
     * Add an ProjectItem instance in to project
     * @param projectItem to remove
     * @return this Project
     */
    @Override
    public Project add(ProjectItem projectItem) {

        SortedMap<String, ProjectItem> map = lazyMap(projectItem);

        if (!map.containsKey(projectItem.getId()))
            map.put(projectItem.getId(), projectItem);

        return this;

    }

    /**
     * Remove an ProjectItem instance of project
     *
     * @param projectItem to remove
     * @return this project
     */
    @Override
    public Project remove(ProjectItem projectItem) {

        SortedMap<String, ProjectItem> map = itemsMap.get(projectItem.getClass());

        if (map == null) {
            map = Maps.newTreeMap();
            itemsMap.put(projectItem.getClass(), map);
            return this;
        }

        if (map.containsKey(projectItem.getId()))
            map.remove(projectItem.getId());

        return this;

    }

    /**
     * FileProject.Builder class
     */
    public static final class Builder {
        private String description;
        private String id;
        private String name;
        private Database database;

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

        public Builder database(Database database) {
            this.database = database;
            return this;
        }

        public FileProject build() {
            return new FileProject(this);
        }


    }


}
