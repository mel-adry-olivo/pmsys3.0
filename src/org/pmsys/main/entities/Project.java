package org.pmsys.main.entities;

import org.pmsys.main.entities.request.ProjectRequest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class Project{

    private String id;
    private String title;
    private String description;
    private String status;
    private String dueDate;

    private List<Task> tasks;
    private int overallTaskCount = 0 ;
    private int taskDoneCount = 0;

    // for create
    public Project(ProjectRequest projectRequest) {
        if(projectRequest.id().isBlank()) {
            id = UUID.randomUUID().toString();
        } else {
            id = projectRequest.id();
        }
        status = "In Progress"; // temp
        title = projectRequest.title();
        description = projectRequest.description();
        dueDate = projectRequest.dueDate();
    }

    public Project(String title, String description, String status, String dueDate) {
        id = UUID.randomUUID().toString();
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
        overallTaskCount = Integer.parseInt(data[5]);
        taskDoneCount = Integer.parseInt(data[6]);
    }

    public static Project EmptyProject() {
        return new Project("", "", "", "");
    }

    public void addTask(Task task) {
        tasks.add(task);
        overallTaskCount++;
        if (task.getStatus().equalsIgnoreCase("done")) {
            taskDoneCount++;
        }
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        overallTaskCount--;
        if (task.getStatus().equalsIgnoreCase("done")) {
            taskDoneCount--;
        }
    }

    public void updateProject(Project project) {
        title = project.getTitle();
        description = project.getDescription();
        status = project.getStatus();
        dueDate = project.getDueDate();
    }

    public void revalidateCounts() {
        overallTaskCount = tasks.size();
        taskDoneCount = 0;
        for (Task task : tasks) {
            if (task.getStatus().equalsIgnoreCase("done")) {
                taskDoneCount++;
            }
        }
    }

    public static LinkedHashMap<String, Project> toMap(List<String> list) {
        LinkedHashMap<String, Project> projectMap = new LinkedHashMap<>();
        for (String line : list) {
            String[] data = line.split(":::");
            Project project = new Project(data);
            projectMap.put(project.getId(), project);
        }
        return projectMap;
    }

    @Override
    public String toString() {
        return id + ":::"
                + title + ":::"
                + description + ":::"
                + status + ":::"
                + dueDate + ":::"
                + overallTaskCount + ":::"
                + taskDoneCount;
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

    public int getOverallTaskCount() {
        return overallTaskCount;
    }

    public int getTaskDoneCount() {
        return taskDoneCount;
    }
}
