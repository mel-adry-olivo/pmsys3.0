package org.pmsys.main.entities.request;

public final class ProjectRequest implements Request{

    private final String id;
    private final String title;
    private final String description;
    private final String dueDate;

    public ProjectRequest(String name, String description, String dueDate) {
        this("", name, description, dueDate);
    }

    public ProjectRequest(String id, String title, String description, String dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "ProjectRequest{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

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
