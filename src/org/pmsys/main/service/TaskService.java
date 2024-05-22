package org.pmsys.main.service;

import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.Task;
import org.pmsys.main.entities.request.TaskRequest;
import org.pmsys.main.entities.result.TaskResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskService extends FileService {

    private Map<String, Task> taskCache = new HashMap<>();

    public void cacheTasks() {
        if (taskCache.isEmpty()) {
            taskCache = getAllTasks();
        }
    }
    public void clearCache() {
        if (taskCache != null) {
            taskCache.clear();
        }
    }

    public void saveToFile(Task task) {
        taskCache.put(task.getId(), task);
        Utils.APPEND(getTaskFileOfCurrentUser(), task.toString());
    }

    public void updateInFile(Task task){
        taskCache.put(task.getId(), task);
        batchSaveTasks();
    }

    public void deleteInFile(Task task) {
        taskCache.remove(task.getId());
        batchSaveTasks();
    }

    public TaskResult validateRequest(TaskRequest taskRequest) {
        if (taskRequest.getTitle().isBlank() || taskRequest.getDescription().isBlank()) {
            return TaskResult.BLANK_FIELDS();
        }
        return TaskResult.SUCCESS(new Task(taskRequest));
    }

    public List<Task> getTasksOf(Project project) {
        cacheTasks();
        return Task.tasksOf(project, taskCache);
    }

    private void batchSaveTasks() {
        String formattedTasks = Utils.FORMAT(taskCache);
        Utils.OVERWRITE(getTaskFileOfCurrentUser(), formattedTasks);
    }

    private Map<String, Task> getAllTasks() {
        List<String> taskLines = Utils.READ(getTaskFileOfCurrentUser());
        return Task.toMap(taskLines);
    }

}
