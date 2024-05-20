package org.pmsys.main.ui.components.base;

import org.pmsys.constants.AppColors;

import javax.swing.*;

public class CButtonFactory {
    public static CButton createDefaultButton(String text) {
        return new CButton(text, false)
                .setForegroundColor(AppColors.DARK_GREY)
                .setTextMargin(6,14,6,14)
                .setHoverBackgroundColor("#FAFAFA")
                .applyFlatStyle();
    }
    public static CButton createDefaultButton(String text, Icon icon) {
        return createDefaultButton(text)
                .setTextMargin(6,10,6,14)
                .applyFlatStyle()
                .setSVGIcon(icon);
    }

    public static CButton createFilledButton(String text) {
        return new CButton(text, false)
                .setForegroundColor(AppColors.WHITE)
                .setBackgroundColor(AppColors.ACCENT)
                .setTextMargin(9,14,9,14)
                .applyFlatStyle();
    }

    public static CButton createBorderlessButton(String text) {
        return new CButton(text, true)
                .setHoverBackgroundColor("null")
                .setForegroundColor(AppColors.DARK_GREY)
                .applyFlatStyle();
    }

    public static CButton createFilledButton(String text, Icon icon) {
        return createFilledButton(text)
                .setTextMargin(6,10,6,14)
                .applyFlatStyle()
                .setSVGIcon(icon);
    }

    public static CButton createIconButton(Icon icon) {
        return new CButton(icon)
                .setHoverBackgroundColor("null")
                .setArc(8)
                .applyFlatStyle();
    }

    public static CButton createHoverableIconButton(Icon icon) {
        return new CButton(icon);
    }
}
