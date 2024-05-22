package org.pmsys.main.ui.components.base;


import org.pmsys.main.ui.CComponent;
import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;

public class CButton extends JButton implements CComponent, FlatStyleable<CButton> {

    private FlatStyler styler;

    public CButton(Icon icon) {
        this("", true);
        setIcon(icon);
    }

    public CButton(String text, boolean isIcon) {
        super(text);
        styler = new FlatStyler();

        setDefaults(isIcon);
    }

    @Override
    public CButton getComponent() {
        return this;
    }

    @Override
    public FlatStyler getStyler() {
        if(styler == null) {
            styler = new FlatStyler();
        }
        return styler;
    }

    public CButton setSVGIcon(Icon icon) {
        setIcon(icon);
        return this;
    }

    private void setDefaults(boolean isIcon) {
        setFocusPainted(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        if (isIcon) {
            setBorderPainted(false);
        }
    }
}
