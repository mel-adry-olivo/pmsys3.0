package org.pmsys.main.entity;

public class Result {
    public enum Status { SUCCESS, USER_NOT_FOUND, WRONG_PASSWORD, VALIDATION_ERROR }

    private final Status status;
    private final String errorMessage;
    private final User user;

    public Result(Status status, String errorMessage, User user) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static Result SUCCESS() {
        return new Result(Status.SUCCESS, "", null);
    }

    public static Result SUCCESS(User user) {
        return new Result(Status.SUCCESS, "", user);
    }

    public static Result USER_NOT_FOUND() {
        return new Result(Status.USER_NOT_FOUND, "Invalid username or password", null);
    }

    public static Result WRONG_PASSWORD() {
        return new Result(Status.WRONG_PASSWORD, "Wrong password", null);
    }
}
