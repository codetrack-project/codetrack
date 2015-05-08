package org.codetrack.database.data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author josecmoj at 05/05/15.
 */
public interface Database extends Serializable {

    Date getLastUpdate();

    void setLastUpdate(Date lastUpdate);

    String getName();

    void setName(String name);

    Project getProject();

    void setProject(Project project);

    boolean isModified();

    void markIsLoaded();
}
