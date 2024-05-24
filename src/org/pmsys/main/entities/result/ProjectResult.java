package org.pmsys.main.entities.result;

import org.pmsys.main.entities.request.status.ProjectRequestStatus;
import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.request.status.Status;

public final class ProjectResult extends Result {

    private final Project project;

    public ProjectResult(Status status, String errorMessage, Project project) {
        super(status, errorMessage);
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public static ProjectResult SUCCESS(Project project) {
        return new ProjectResult(ProjectRequestStatus.SUCCESS, "", project);
    }

    public static ProjectResult BLANK_FIELDS() {
        return new ProjectResult(ProjectRequestStatus.BLANK_FIELDS, "Fields cannot be blank!", null);
    }

    public static ProjectResult INVALID_DATE() {
        return new ProjectResult(ProjectRequestStatus.INVALID_DATE, "Invalid date. Due date cannot be in the past!", null);
    }

    public static ProjectResult PROJECT_NOT_FOUND() {
        return new ProjectResult(ProjectRequestStatus.PROJECT_NOT_FOUND, "Project not found!", null);
    }
}
