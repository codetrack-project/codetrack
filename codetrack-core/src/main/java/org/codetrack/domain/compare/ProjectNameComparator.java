package org.codetrack.domain.compare;

import org.codetrack.domain.data.Project;

import java.util.Comparator;

/**
 * @author josecmoj at 07/08/15.
 */
public class ProjectNameComparator implements Comparator<Project> {

    @Override
    public int compare(Project p1, Project p2) {
        return p1.getName().compareTo(p2.getName());
    }
}
