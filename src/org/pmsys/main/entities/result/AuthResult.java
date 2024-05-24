package org.pmsys.main.entities.result;


import org.pmsys.main.entities.request.status.AuthRequestStatus;
import org.pmsys.main.entities.request.status.Status;
import org.pmsys.main.entities.User;

public final class AuthResult extends Result {

    private final User user;

    public AuthResult(Status status, String errorMessage, User user) {
        super(status, errorMessage);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public static AuthResult SUCCESS() {
        return new AuthResult(AuthRequestStatus.SUCCESS, "", null);
    }

    public static AuthResult SUCCESS(User user) {
        return new AuthResult(AuthRequestStatus.SUCCESS, "", user);
    }

    public static AuthResult USER_NOT_FOUND() {
        return new AuthResult(AuthRequestStatus.USER_NOT_FOUND, "Invalid username or password", null);
    }

    public static AuthResult WRONG_PASSWORD() {
        return new AuthResult(AuthRequestStatus.WRONG_PASSWORD, "Wrong password", null);
    }
}
