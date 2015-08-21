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

import org.codetrack.annotation.definition.Feature;
import org.codetrack.annotation.identify.Product;

import java.io.Serializable;
import java.util.Date;

/**
 * All items in the project extends of this class
 *
 * @author josecmoj at 25/06/15.
 */
@Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public abstract class ProjectItem implements Serializable {

    private Date createdAt;

    private Date updatedAt;

    private Project project;

    public ProjectItem() {
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project){
        this.project = project;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public abstract String getId();

}
