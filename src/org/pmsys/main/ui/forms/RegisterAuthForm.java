package org.pmsys.main.ui.forms;

import org.pmsys.main.actions.ActionManager;
import org.pmsys.main.ui.components.base.FlatButton;
import org.pmsys.main.ui.components.base.FlatLabel;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class RegisterAuthForm extends AbstractAuthForm {

    public RegisterAuthForm() {
        super();
    }

    @Override
    void handleButtonClick(ActionEvent e) {
        ActionManager.executeAction("register", (FlatButton) e.getSource(), this);
    }

    @Override
    void handleLinkClick(MouseEvent e) {
        ActionManager.executeAction("switchAuthForm", (FlatLabel) e.getSource(), this);
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
        return "Login";
    }
}
