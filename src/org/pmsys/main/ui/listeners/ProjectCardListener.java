package org.pmsys.main.ui.listeners;

import org.pmsys.main.entity.Project;

@FunctionalInterface
public interface ProjectCardListener {
    void onClick(Project project);
}
