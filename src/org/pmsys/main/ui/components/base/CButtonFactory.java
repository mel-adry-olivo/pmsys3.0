package org.pmsys.main.ui.components.base;

import org.pmsys.main.ui.components.constants.ColorConstants;

import javax.swing.*;

public class CButtonFactory {
    public static CButton createDefaultButton(String text) {
        return new CButton(text, false)
                .setForegroundColor(ColorConstants.DARK_GREY)
                .setTextMargin(6,14,6,14)
                .setHoverBackgroundColor("#FAFAFA")
                .applyStyles();
    }
    public static CButton createDefaultButton(String text, Icon icon) {
        return createDefaultButton(text)
                .setTextMargin(6,10,6,14)
                .applyStyles()
                .setSVGIcon(icon);
    }

    public static CButton createFilledButton(String text) {
        return new CButton(text, false)
                .setForegroundColor(ColorConstants.WHITE)
                .setBackgroundColor(ColorConstants.ACCENT)
                .setTextMargin(9,14,9,14)
                .applyStyles();
    }

    public static CButton createBorderlessButton(String text) {
        return new CButton(text, true)
                .setHoverBackgroundColor("null")
                .setForegroundColor(ColorConstants.DARK_GREY)
                .applyStyles();
    }

    public static CButton createFilledButton(String text, Icon icon) {
        return createFilledButton(text)
                .setTextMargin(6,10,6,14)
                .applyStyles()
                .setSVGIcon(icon);
    }

    public static CButton createIconButton(Icon icon) {
        return new CButton(icon)
                .setHoverBackgroundColor("null")
                .setArc(8)
                .applyStyles();
    }

    public static CButton createHoverableIconButton(Icon icon) {
        return new CButton(icon);
    }
}
