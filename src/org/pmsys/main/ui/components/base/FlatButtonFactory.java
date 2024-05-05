package org.pmsys.main.ui.components.base;

import org.pmsys.constants.AppColors;

import javax.swing.*;

public class FlatButtonFactory {
    public static FlatButton createDefaultButton(String text) {
        return new FlatButton(text, false)
                .setForegroundColor(AppColors.DARK_GREY)
                .setTextMargin(6,14,6,14)
                .setHoverBackgroundColor("#FAFAFA")
                .applyFlatStyle();
    }
    public static FlatButton createDefaultButton(String text, Icon icon) {
        return createDefaultButton(text)
                .setTextMargin(6,10,6,14)
                .applyFlatStyle()
                .setSVGIcon(icon);
    }

    public static FlatButton createFilledButton(String text) {
        return new FlatButton(text, false)
                .setForegroundColor(AppColors.WHITE)
                .setBackgroundColor(AppColors.ACCENT)
                .setTextMargin(6,14,6,14)
                .applyFlatStyle();
    }

    public static FlatButton createBorderlessButton(String text) {
        return new FlatButton(text, true)
                .setForegroundColor(AppColors.DARK_GREY)
                .applyFlatStyle();
    }

    public static FlatButton createFilledButton(String text, Icon icon) {
        return createFilledButton(text)
                .setTextMargin(6,10,6,14)
                .applyFlatStyle()
                .setSVGIcon(icon);
    }

    public static FlatButton createIconButton(Icon icon) {
        return new FlatButton(icon)
                .setHoverBackgroundColor("null")
                .setArc(8)
                .applyFlatStyle();
    }

    public static FlatButton createHoverableIconButton(Icon icon) {
        return new FlatButton(icon);
    }
}
