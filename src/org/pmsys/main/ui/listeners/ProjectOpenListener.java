package org.pmsys.main.ui.listeners;

import org.pmsys.main.entities.Project;

@FunctionalInterface
public interface ProjectOpenListener {
    void onProjectOpened(Project project);
}
