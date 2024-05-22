package org.pmsys.main.ui.auth;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.ui.CComponent;

/**
 * Login or sign up form
 */
public class AuthPanel extends AbstractAuthPanel implements CComponent {

    public AuthPanel(AuthWindow authWindow, boolean isLogin) {
        super(authWindow, isLogin);
    }

    @Override
    String getTitle() {
        return Provider.getTitle(isLogin);
    }

    @Override
    String getSubtitle() {
        return Provider.getSubtitle(isLogin);
    }

    @Override
    String getButtonName() {
        return Provider.getButtonName(isLogin);
    }

    @Override
    String getQuestion() {
        return Provider.getQuestion(isLogin);
    }

    @Override
    String getLink() {
        return Provider.getLink(isLogin);
    }

    @Override
    public Actions getAction() {
        return Provider.getAction(isLogin);
    }

    private static class Provider {
        public static String getTitle(boolean isLogin) {
            return isLogin ? "Welcome back" : "Create an account";
        }

        public static String getSubtitle(boolean isLogin) {
            return isLogin ? "Please enter your details" : "Enter details below to get started";
        }

        public static String getButtonName(boolean isLogin) {
            return isLogin ? "Login" : "Get Started";
        }

        public static String getQuestion(boolean isLogin) {
            return isLogin ? "Don't have an account?" : "Already have an account?";
        }

        public static String getLink(boolean isLogin) {
            return isLogin ? "Register" : "Login";
        }

        public static Actions getAction(boolean isLogin) {
            return isLogin ? Actions.LOGIN : Actions.REGISTER;
        }
    }
}
