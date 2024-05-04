package org.pmsys.main.entity;

import java.util.List;
import java.util.UUID;

public class Project {

    private String id;
    private String title;
    private String description;
    private String status;
    private String dueDate;

    private List<Task> tasks;
    private int taskProgress;

    public Project(ProjectCreateRequest projectCreateRequest) {
        this(projectCreateRequest.getTitle(), projectCreateRequest.getDescription(), "In Progress", projectCreateRequest.getDueDate());
    }

    public Project(String title, String description, String status, String dueDate) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    // loading
    public Project(String id, String title, String description, String status, String dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    // loading
    public Project(String[] data) {
        id = data[0];
        title = data[1];
        description = data[2];
        status = data[3];
        dueDate = data[4];
    }

    public static Project parseToProject(String line) {
        String[] data = line.split(":::");
        return new Project(data);
    }

    @Override
    public String toString() {
        return id + ":::" + title + ":::" + description + ":::" + status + ":::" + dueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getTaskProgress() {
        return taskProgress;
    }

    public void setTaskProgress(int taskProgress) {
        this.taskProgress = taskProgress;
    }
}
