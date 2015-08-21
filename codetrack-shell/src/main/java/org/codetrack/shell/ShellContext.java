package org.codetrack.shell;

import org.codetrack.database.Database;
import org.codetrack.database.DatabaseConnection;
import org.codetrack.domain.data.Project;
import org.springframework.stereotype.Service;

/**
 * Save common data used by any shell command classes
 *
 * @author josecmoj at 05/08/15.
 */
@Service
public class ShellContext {

    private Database activeDatabase;

    private Project activeProject;

    private DatabaseConnection activeDatabaseConnection;

    public ShellContext() {
        super();
    }

    public Database getActiveDatabase() {
        return activeDatabase;
    }

    public void setActiveDatabase(Database activeDatabase) {
        this.activeDatabase = activeDatabase;
    }

    public Project getActiveProject() {
        return activeProject;
    }

    public void setActiveProject(Project activeProject) {
        this.activeProject = activeProject;
    }

    public DatabaseConnection getActiveDatabaseConnection() {
        return activeDatabaseConnection;
    }

    public void setActiveDatabaseConnection(DatabaseConnection activeDatabaseConnection) {
        this.activeDatabaseConnection = activeDatabaseConnection;
    }
}
