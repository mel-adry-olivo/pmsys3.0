package org.pmsys.main.managers;

import org.pmsys.main.entities.User;

public enum SessionManager {

    INSTANCE;

    private User currentUser;

    public void setUser(User user) {
        currentUser = user;
    }

    public User getUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void clearSession() {
        currentUser = null;
    }
}
