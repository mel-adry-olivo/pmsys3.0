package org.pmsys.main.ui.components;

import org.pmsys.main.managers.SessionManager;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.components.constants.ColorConstants;
import org.pmsys.main.ui.components.constants.IconConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserAvatar extends CLabel {

    public UserAvatar() {
        super(UserAvatar.createUserIcon());
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PopupMenu popupMenu = new PopupMenu();
                popupMenu.show(e.getComponent(), e.getX() - popupMenu.getWidth(), e.getY());
            }
        });
    }

    private static AvatarIcon createUserIcon() {
        return new AvatarIcon(IconConstants.USER_ICON, 38,38,999);
    }

    private static class PopupMenu extends JPopupMenu {

        private final CPanel panel;

        public PopupMenu() {
            panel = new CPanel("insets 10 15 15 15, fillx");
            panel.setPreferredSize(new Dimension(150, 115));

            panel.add(new UserAvatar(), "w 0%, h 0%, split 2");
            panel.add(CLabelFactory.createSemiBoldLabel(SessionManager.INSTANCE.getUser().getUsername()), "wrap");
            panel.add(new JSeparator(), "wrap, grow, span 2 1");
            panel.add(createLogoutButton(), "h 30, w 35,grow, span 2 1");

            add(panel);

        }

        private CButton createLogoutButton() {
            CButton logoutButton = CButtonFactory.createBorderlessButton("Log out")
                    .setHoverBackgroundColor(ColorConstants.LIGHT_GREY)
                    .setTextMargin(0,12,0,0)
                    .applyStyles();
            logoutButton.setHorizontalAlignment(SwingConstants.LEFT);
            logoutButton.addActionListener(e -> SessionManager.INSTANCE.logout());
            return logoutButton;
        }

        @Override
        public int getWidth() {
            return panel.getPreferredSize().width;
        }
    }
}
