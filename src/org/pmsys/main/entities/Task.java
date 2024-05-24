package org.pmsys.main.entities;

import org.pmsys.main.entities.request.TaskRequest;

import java.util.*;

public class Task {

    private String projectId;
    private String id;
    private String title;
    private String description;
    private String dueDate;
    private String priority;
    private String status;


    // default constructor
    public Task(String title, String description, String dueDate, String priority, String status) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    // for requests
    public Task(TaskRequest request) {
        this(request.title(), request.description(), request.dueDate(), request.priority(), request.status());
    }

    // for loading from file
    public Task(String projectId, String id, String title, String description, String dueDate, String priority, String status) {
        this.projectId = projectId;
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    // for parsing
    public Task(String[] data) {
        this.projectId = data[0];
        this.id = data[1];
        this.title = data[2];
        this.description = data[3];
        this.dueDate = data[4];
        this.priority = data[5];
        this.status = data[6];
    }


    public static HashMap<String, Task> toMap(List<String> list) {
        HashMap<String, Task> taskMap = new HashMap<>();
        for (String line : list) {
            String[] data = line.split(":::");
            Task task = new Task(data);
            taskMap.put(task.getId(), task);
        }
        return taskMap;
    }

    public static List<Task> tasksOf(Project project, Map<String, Task> taskMap) {
        List<Task> list = new ArrayList<>();
        for (Task task : taskMap.values()) {
            if (task.getProjectId().equals(project.getId())) {
                list.add(task);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return projectId + ":::" + id + ":::" + title + ":::" + description +
                ":::" + dueDate + ":::" + priority + ":::" + status;
    }

    public void setUpdateFrom(Task task) {
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
        this.status = task.getStatus();
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

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
