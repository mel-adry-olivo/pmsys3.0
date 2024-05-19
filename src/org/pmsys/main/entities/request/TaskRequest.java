package org.pmsys.main.entities.request;

public final class TaskRequest implements Request {

    private final String id;
    private final String title;
    private final String description;
    private final String dueDate;
    private final String priority;
    private final String status;

    public TaskRequest(String title, String description, String dueDate, String priority, String status) {
        this("", title, description, dueDate, priority, status);
    }

    public TaskRequest(String id, String title, String description, String dueDate, String priority, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
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

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }
}
