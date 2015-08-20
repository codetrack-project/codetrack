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

package org.codetrack.database;

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;
import org.codetrack.domain.data.Project;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author josecmoj at 05/05/15.
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface Database extends Serializable {

    Date getLastUpdate();

    void setLastUpdate(Date lastUpdate);

    String getName();

    void setName(String name);

    Project getProject();

    void setProject(Project project);

    boolean isModified();

    void markIsLoaded();

    Project createProject(String id, String name);

    void addProject(Project project);

    void removeProject(Project project);

    Project findProject(String id);

    Project selectProject(String id);

    void changed();

    void save();

    List<Project> allProjects();

    DatabaseConnection getConnection();

    void setConnection(DatabaseConnection connection);
}
