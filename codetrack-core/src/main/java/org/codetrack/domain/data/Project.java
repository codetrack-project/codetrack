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

package org.codetrack.domain.data;

import java.io.Serializable;

/**
 * This interface define behavior of an Project instance
 *
 * @author josecmoj at 22/06/15.
 */
@org.codetrack.annotation.identify.Product(id = "codetrack-core")
@org.codetrack.annotation.definition.Feature(id = "#4-DATABASE")
public interface Project extends Serializable {

    /**
     * Access description information
     *
     * @return String description
     */
    String getDescription();

    /**
     * Put description information
     *
     * @param description String information
     */
    void setDescription(String description);

    /**
     * Access id of project
     *
     * @return String id
     */
    String getId();

    /**
     * Configure id of project
     *
     * @param id String
     */
    void setId(String id);

    /**
     * Access name of project
     *
     * @return String name
     */
    String getName();

    /**
     * Configure name of project
     * @param name String
     */
    void setName(String name);

    /**
     * Find first ProjectItem related with id
     *
     * @param id String of ProjectItem
     * @return ProjectItem instance founded or null if not found
     */
    ProjectItem findById(String id);

    /**
     * Add an ProjectItem instance in the project
     * @param projectItem to remove
     * @return this Project instance
     */
    Project add(ProjectItem projectItem);

    /**
     * Remove an ProjectItems instance of project
     * @param projectItem to remove
     * @return this Project instance
     */
    Project remove(ProjectItem projectItem);

}
