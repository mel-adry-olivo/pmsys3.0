package org.pmsys.main.ui.forms;

import org.pmsys.main.actions.ActionManager;
import org.pmsys.main.ui.components.base.FlatButton;
import org.pmsys.main.ui.components.base.FlatLabel;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class LoginAuthForm extends AbstractAuthForm {

    public LoginAuthForm() {
        super();
    }

    @Override
    void handleButtonClick(ActionEvent e) {
        ActionManager.executeAction("login", (FlatButton) e.getSource(), this);
    }

    @Override
    void handleLinkClick(MouseEvent e) {
        ActionManager.executeAction("switchAuthForm", (FlatLabel) e.getSource(), this);
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
        return "Login";
    }

    @Override
    String getQuestion() {
        return "Don't have an account?";
    }

    @Override
    String getLink() {
        return "Register";
    }
}
