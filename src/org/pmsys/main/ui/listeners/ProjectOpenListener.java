package org.pmsys.main.ui.listeners;

import org.pmsys.main.model.Project;

@FunctionalInterface
public interface ProjectOpenListener {
    void onProjectOpened(Project project);
}
