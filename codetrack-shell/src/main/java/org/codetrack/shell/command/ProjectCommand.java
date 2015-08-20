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

package org.codetrack.shell.command;

import org.codetrack.database.Database;
import org.codetrack.database.DatabaseConnection;
import org.codetrack.database.exception.DatabaseError;
import org.codetrack.domain.compare.ProjectIdComparator;
import org.codetrack.domain.compare.ProjectNameComparator;
import org.codetrack.domain.data.Project;
import org.codetrack.domain.data.identify.Path;
import org.codetrack.repository.Repository;
import org.codetrack.repository.SearchAll;
import org.codetrack.repository.SearchOneById;
import org.codetrack.repository.SearchResponse;
import org.codetrack.shell.ShellContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * @author josecmoj at 03/08/15.
 */
@Component
public class ProjectCommand implements CommandMarker {

    private Logger logger = LoggerFactory.getLogger(ProjectCommand.class);

    /**
     * Shell global data context service
     */
    @Autowired
    private ShellContext shellContext;

    private void preConfig(String command) {

        logger.debug("Configure command [" + command + "]");

    }

    /**
     * Verify availability of "create" command
     *
     * @return return true if an database instance in shell context activeDatabase property
     */
    @CliAvailabilityIndicator({"project create"})
    public boolean isProjectCreateAvailable() {
        return shellContext.getActiveDatabase() != null;
    }


    /**
     * Create a project in the active database
     *
     * @param id          - id of project
     * @param name        - name of project
     * @param description - optional description of project
     * @return String with operation text result
     */
    @CliCommand(value = "project create", help = "Create a project in the active database")
    public String create(@CliOption(key = "id", mandatory = true, help = "Id of the project")
                         String id,
                         @CliOption(key = "name", mandatory = true, help = "Name of the project")
                         String name,
                         @CliOption(key = "description", mandatory = false, help = "Description of the project")
                         String description) {

        preConfig("project create");

        Database database = shellContext.getActiveDatabase();

        if (database == null)
            return "Can not create a project without a database";

        try {

            Project project = database.findProject(id);
            shellContext.setActiveProject(project);
            return "Project " + id + " already exist in the database... is now selected";

        } catch (DatabaseError dbe) {

            Project project = database.createProject(id, name);
            project.setDescription(description);

            shellContext.setActiveProject(project);

            return "Project " + id + " - " + name + " is created in " + database.getName() + " database";
        }
    }

    @CliCommand(value = "project list", help = "List all projects on active database")
    public String list(@CliOption(key = "order", mandatory = false, help = "Order options", specifiedDefaultValue = "id asc")
                       String order) {

        List<Project> result = shellContext.getActiveDatabase().allProjects();

        if ("id desc".equals(order)) {
            Collections.sort(result, (new ProjectIdComparator()).reversed());
        } else if ("name asc".equals(order)) {
            Collections.sort(result, (new ProjectNameComparator()));
        } else if ("name desc".equals(order)) {
            Collections.sort(result, (new ProjectNameComparator().reversed()));
        } else if ((order == null) || ("id asc".equals(order))) {
            Collections.sort(result, (new ProjectNameComparator()));
        }

        StringBuilder builder = new StringBuilder();

        for (Project project : result) {
            builder.append("project: ")
                    .append(project.getId())
                    .append(" [")
                    .append(project.getName())
                    .append("]\n");
        }

        return builder.toString();
    }

    @CliCommand(value = "project active", help = "List all projects on active database")
    public String active() {

        preConfig("project active");

        Project project = shellContext.getActiveProject();
        if (project == null) {
            return "Not project is active state";
        } else
            return "Active project is " + project.getId();

    }

    @CliCommand(value = "project use", help = "Select a project to be used")
    public String use(@CliOption(key = "id", mandatory = true, help = "Project id")
                      String id) {

        preConfig("project active");

        Database database = shellContext.getActiveDatabase();

        if (database == null)
            return "Can not find a project without a database... try database use command first";

        try {

            Project project = database.findProject(id);
            shellContext.setActiveProject(project);
            return "Project " + id + " is now selected";

        } catch (DatabaseError dbe) {

            return "Can not find project " + id;
        }

    }

    @CliAvailabilityIndicator({"project add path"})
    public boolean isAddPathAvailability() {
        return (shellContext.getActiveProject() != null);
    }

    @CliCommand(value = "project add path", help = "Add path to active project in the active database")
    public String addPath(@CliOption(key = "id", mandatory = true, help = "Path id")
                          String pathId,
                          @CliOption(key = "url", mandatory = false, help = "Path url")
                          String url) {

        DatabaseConnection conn = shellContext.getActiveDatabase().getConnection();
        Repository<Path> repository = conn.getRepository(shellContext.getActiveProject());

        SearchOneById<Path> searchOneById = SearchOneById.newBuilder().id(pathId).itemClazz(Path.class).build();
        SearchResponse<Path> searchResponse = repository.search(searchOneById);

        if (searchResponse.getItem() != null) {
            return "Path " + pathId + " exist in project. Nothing to do...";
        } else {

            File filePath = new File(url);
            if (filePath.exists()) {

                Path path = Path.newBuilder()
                        .id(pathId)
                        .url(url).build();

                Path savedPath = repository.save(path);
                return "Path " + savedPath.getId() + " is saved";

            } else {

                return "Not found the url " + url;
            }
        }
    }

    @CliAvailabilityIndicator({"project clear paths"})
    public boolean isClearPathsAvailability() {
        return (shellContext.getActiveProject() != null);
    }

    @CliCommand(value = "project clear paths", help = "Clear paths of active project in the active database")
    public String clearPaths() {

        DatabaseConnection conn = shellContext.getActiveDatabase().getConnection();
        Repository<Path> repository = conn.getRepository(shellContext.getActiveProject());

        SearchAll<Path> searchAll = SearchAll.newBuilder().itemClazz(Path.class).build();

        try {
            SearchResponse<Path> searchResponse = repository.search(searchAll);

            List<Path> result = repository.remove(searchResponse);

            return result.size() + " path are removed from project";

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @CliAvailabilityIndicator({"project list paths"})
    public boolean isListPathsAvailability() {
        return (shellContext.getActiveProject() != null);
    }

    @CliCommand(value = "project list paths", help = "List paths of active project in the active database")
    public String listPaths() {

        DatabaseConnection conn = shellContext.getActiveDatabase().getConnection();
        Repository<Path> repository = conn.getRepository(shellContext.getActiveProject());

        SearchAll<Path> searchAll = SearchAll.newBuilder().itemClazz(Path.class).build();

        SearchResponse<Path> searchResponse = repository.search(searchAll);

        if (searchResponse.getItems().isEmpty()) {
            return "Not exist path in active project";
        } else {
            try {
                StringBuffer buffer = new StringBuffer();

                for (Path path : searchResponse.getItems()) {

                    buffer.append("path:")
                            .append(path.getId())
                            .append(" - url:")
                            .append(path.getUrl())
                            .append("\n");
                }

                return buffer.toString();

            } catch (Exception e) {

                return e.getMessage();

            }

        }
    }

}

