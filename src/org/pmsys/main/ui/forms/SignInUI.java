package org.pmsys.main.ui.forms;

public class SignInUI extends AbstractAuthUI {

    public SignInUI() {
        super();
    }

    @Override
    public String getUsername() {
        return usernameField.getText();
    }

    @Override
    public char[] getPassword() {
        return passwordField.getPassword();
    }

    @Override
    String getTitle() {
        return "Welcome back";
    }

    @Override
    String getSubtitle() {
        return "Please enter your details";
    }

    @Override
    String getButtonName() {
        return "Sign In";
    }

    @Override
    String getQuestion() {
        return "Don't have an account?";
    }

    @Override
    String getLink() {
        return "Sign up";
    }

}
