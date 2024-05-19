package org.pmsys.main.model.result;

import org.pmsys.constants.Status;
import org.pmsys.constants.TaskRequestStatus;
import org.pmsys.main.model.Task;

public class TaskResult extends Result{

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
