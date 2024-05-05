package org.pmsys.main.entity;

import java.util.UUID;

public class Task {

    private String id;
    private String title;
    private String description;
    private String dueDate;
    private String priority;
    private String status;

    public Task(String title, String description, String dueDate, String priority, String status) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
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

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    // TODO
}
