package org.pmsys.main.entities;

import org.pmsys.main.entities.request.TaskRequest;

import java.util.UUID;

public class Task {

    private String projectId;
    private String id;
    private String title;
    private String description;
    private String dueDate;
    private String priority;
    private String status;


    // creation
    public Task(String title, String description, String dueDate, String priority, String status) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    public Task(TaskRequest request) {
        this(request.getTitle(), request.getDescription(), request.getDueDate(), request.getPriority(), request.getStatus());
    }

    // loading
    public Task(String projectId, String id, String title, String description, String dueDate, String priority, String status) {
        this.projectId = projectId;
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    public Task(String[] data) {
        this.projectId = data[0];
        this.id = data[1];
        this.title = data[2];
        this.description = data[3];
        this.dueDate = data[4];
        this.priority = data[5];
        this.status = data[6];
    }

    @Override
    public String toString() {
        return projectId + ":::" + id + ":::" + title + ":::" + description +
                ":::" + dueDate + ":::" + priority + ":::" + status;
    }

    public void updateTaskDetails(Task task) {
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
        this.status = task.getStatus();
    }

    public static Task parseLineToTask(String s) {
        String[] data = s.split(":::");
        return new Task(data);
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
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
