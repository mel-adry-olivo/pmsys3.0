package org.pmsys.main.entities.result;

import org.pmsys.main.entities.request.status.Status;

public abstract class Result {

    protected final Status status;
    protected final String errorMessage;

    public Result(Status status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public Status getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
