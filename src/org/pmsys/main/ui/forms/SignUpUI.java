package org.pmsys.main.ui.forms;

import org.pmsys.main.ui.components.base.FlatPasswordField;
import org.pmsys.main.ui.components.base.FlatTextField;

public class SignUpUI extends AbstractAuthUI {

    public SignUpUI() {
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
        return "Create an account";
    }

    @Override
    String getSubtitle() {
        return "Enter details below to get started";
    }

    @Override
    String getButtonName() {
        return "Get Started";
    }

    @Override
    String getQuestion() {
        return "Already have an account?";
    }

    @Override
    String getLink() {
        return "Sign in";
    }

}
