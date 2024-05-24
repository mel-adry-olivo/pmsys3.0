package org.pmsys.main.entities.result;

import org.pmsys.main.entities.request.status.Status;
import org.pmsys.main.entities.request.status.TaskRequestStatus;
import org.pmsys.main.entities.Task;

public final class TaskResult extends Result{

    private final Task task;

    public TaskResult(Status status, String errorMessage, Task task) {
        super(status, errorMessage);
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public static TaskResult SUCCESS(Task task) {
        return new TaskResult(TaskRequestStatus.SUCCESS, null, task);
    }

    public static TaskResult BLANK_FIELDS() {
        return new TaskResult(TaskRequestStatus.BLANK_FIELDS, "Fields cannot be blank!", null);
    }
}
