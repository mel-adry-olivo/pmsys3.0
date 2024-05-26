package org.pmsys.main.entities.request.status;

public enum AuthRequestStatus implements Status {
    SUCCESS,
    USER_NOT_FOUND,
    USER_EXISTS, WRONG_PASSWORD
}
