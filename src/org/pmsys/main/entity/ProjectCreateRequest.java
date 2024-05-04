package org.pmsys.main.entity;

public final class ProjectCreateRequest {

    private String title;
    private String description;
    private String dueDate;

    public ProjectCreateRequest(String name, String description, String dueDate) {
        this.title = name;
        this.description = description;
        this.dueDate = dueDate;
    }

    // TODO: IMPLEMENT DATA VALIDATION

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }
}
