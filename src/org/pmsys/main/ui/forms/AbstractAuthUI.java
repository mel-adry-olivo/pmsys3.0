package org.pmsys.main.ui.forms;

import org.pmsys.constants.AppColors;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.listeners.ClickListener;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class AbstractAuthUI extends FlatPanel {

    private FlatLabel linkLabel;
    private FlatLabel messageLabel;
    protected FlatTextField usernameField;
    protected FlatPasswordField passwordField;
    protected FlatButton button;
    private FlatLabel titleLabel;
    private FlatLabel subtitleLabel;
    private FlatLabel usernameLabel;
    private FlatLabel passwordLabel;
    private FlatLabel questionLabel;

    public AbstractAuthUI() {
        super("insets 10% 14% 10% 12%, fillx", "[]", "[]2%[]2%[]2%[]2%[]4%[]2%[]8%[]4%[]");
        setupTitles();
        setupInput();
        setupButton();
        setupQuestion();
    }

    abstract String getTitle();
    abstract String getSubtitle();
    abstract String getButtonName();
    abstract String getQuestion();
    abstract String getLink();

    abstract String getUsername();
    abstract char[] getPassword();

    public void handleLinkClick(ClickListener listener) {
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.onClick();
            }
        });
    }

    public void handleButtonClick(ActionListener listener) {
        button.addActionListener(listener);
    }

    public void resetFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void showErrorMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setForegroundColor(AppColors.ERROR).applyFlatStyle();
    }
    public void resetErrorMessage() {
        messageLabel.setText("This is a message label");
        messageLabel.setForegroundColor(AppColors.WHITE).applyFlatStyle();
    }

    public void showErrorInput(boolean username, boolean password) {
        if(username) {
            usernameField
                    .setBorderColor(AppColors.ERROR)
                    .applyFlatStyle();
        }

        if(password) {
            passwordField
                    .setBorderColor(AppColors.ERROR)
                    .applyFlatStyle();
        }
    }
    public void resetErrorInput() {
        usernameField.setBorderColor("darken(#ffffff, 5%)")
                .applyFlatStyle();
        passwordField.setBorderColor("darken(#ffffff, 5%)")
                .applyFlatStyle();
    }

    protected FlatLabel createLabel(String text, String fontStyle, String foregroundColor) {
        return new FlatLabel(text)
                .setFontStyle(fontStyle)
                .setForegroundColor(foregroundColor)
                .applyFlatStyle();
    }
    protected FlatButton createButton(String text) {
        return new FlatButton(text, false)
                .setArc(100)
                .setBorderWidth(2)
                .setHoverBorderColor(AppColors.ACCENT)
                .setBorderColor(AppColors.ACCENT)
                .setPressedBackgroundColor(AppColors.ACCENT)
                .setPressedForegroundColor(AppColors.WHITE)
                .setHoverBackgroundColor(AppColors.WHITE)
                .setHoverForegroundColor(AppColors.ACCENT)
                .setBackgroundColor(AppColors.ACCENT)
                .setForegroundColor(AppColors.WHITE)
                .setFontStyle(FlatLabel.SEMIBOLD)
                .setTextMargin(10,18,10,18)
                .applyFlatStyle();
    }

    private void setupTitles() {
        titleLabel = createLabel(getTitle(), FlatLabel.H1_120, AppColors.BLACK);
        subtitleLabel = createLabel(getSubtitle(), FlatLabel.DEFAULT, AppColors.DARK_GREY);

        add(titleLabel, "al left, wrap, growx");
        add(subtitleLabel, "al left, wrap, growx");
    }
    private void setupInput() {
        usernameLabel = createLabel("Username", FlatLabel.DEFAULT, AppColors.DARK_GREY);
        messageLabel = createLabel("This is a message label", FlatLabel.DEFAULT, AppColors.WHITE);
        usernameField = FlatFieldFactory.createTextField("Enter your username");

        passwordLabel = createLabel("Password", FlatLabel.DEFAULT, AppColors.DARK_GREY);
        passwordField = FlatFieldFactory.createPasswordField("Enter your password");

        add(messageLabel, "h 0%, al left, wrap, growx");
        add(usernameLabel, "al left, wrap, growx");
        add(usernameField, "al left, wrap, growx");

        add(passwordLabel, "al left, wrap, growx");
        add(passwordField, "al left, wrap, growx");
    }
    private void setupButton() {
        button = createButton(getButtonName());
        add(button, "al left, wrap, growx");
    }
    private void setupQuestion() {
        questionLabel = createLabel(getQuestion(), FlatLabel.DEFAULT, AppColors.DARK_GREY);
        linkLabel = createLabel(getLink(), FlatLabel.DEFAULT, AppColors.ACCENT).isLink();

        add(questionLabel, "al center, split 2");
        add(linkLabel, "al center, wrap");
    }

}
