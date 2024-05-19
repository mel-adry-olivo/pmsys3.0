package org.pmsys.main.service;

import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.Task;
import org.pmsys.main.entities.request.TaskRequest;
import org.pmsys.main.entities.result.TaskResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TaskService extends FileService {

    private Map<String, Task> taskCache = null;

    public void cacheTasks() {
        if (taskCache == null) {
            taskCache = getAllTasks();
        }
    }

    public void clearCache() {
        taskCache = null;
    }

    public void saveTask(Task task) {
        try {
            Files.writeString(
                    getTaskFileOfUser(),
                    task.toString() + System.lineSeparator(),
                    StandardOpenOption.APPEND);

            taskCache.put(task.getId(), task);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    private void batchSaveTasks() {
        try {
            StringBuilder content = new StringBuilder();
            for (Task task : taskCache.values()) {
                content.append(task.toString()).append(System.lineSeparator());
            }

            Files.writeString(
                    getTaskFileOfUser(),
                    content.toString(),
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    public void updateTask(Task updatedTask){
        taskCache.put(updatedTask.getId(), updatedTask);
        batchSaveTasks();
    }

    public void deleteTask(Task task) {
        taskCache.remove(task.getId());
        batchSaveTasks();
    }

    public Map<String, Task> getAllTasks() {
        try {
            return Files.readAllLines(getTaskFileOfUser())
                    .stream()
                    .map(Task::parseLineToTask)
                    .collect(Collectors.toMap(Task::getId, task -> task));
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
            return Collections.emptyMap();
        }
    }

    public List<Task> getTasksOf(Project project) {
        cacheTasks();
        return taskCache.values().stream()
                .filter(task -> task.getProjectId().equals(project.getId()))
                .collect(Collectors.toList());
    }

    public TaskResult validateTaskRequest(TaskRequest taskRequest) {
        if (taskRequest.getTitle().isBlank() || taskRequest.getDescription().isBlank()) {
            return TaskResult.BLANK_FIELDS();
        }
        return TaskResult.SUCCESS(new Task(taskRequest));
    }
}
