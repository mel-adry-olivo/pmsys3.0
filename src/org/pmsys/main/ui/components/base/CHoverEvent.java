package org.pmsys.main.ui.components.base;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CHoverEvent extends MouseAdapter {

    private final CPanel panel;

    public CHoverEvent(CPanel panel) {
        this.panel = panel;
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        panel.setBackgroundColor("darken(#ffffff, 4%)").applyStyles();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        panel.setBackgroundColor("#ffffff").applyStyles();
    }
}
