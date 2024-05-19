package org.pmsys.main.managers;

import org.pmsys.main.entities.User;

public class SessionManager {

    private static final SessionManager instance = new SessionManager();

    private User currentUser;

    private SessionManager() { /* empty */ }

    public static void setUser(User user) {
        instance.currentUser = user;
    }

    public static User getUser() {
        return instance.currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void clearSession() {
        currentUser = null;
    }
}
