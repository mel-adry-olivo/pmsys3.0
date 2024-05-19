package org.pmsys.main.manager;

import org.pmsys.main.model.User;

public class SessionManager {

    private static SessionManager instance = new SessionManager();
    public static SessionManager getInstance() { return instance; }

    private User currentUser;

    private SessionManager() { }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void clearSession() {
        currentUser = null;
    }
}
