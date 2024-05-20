package org.pmsys.main.ui.components.base;

import org.pmsys.constants.AppColors;

public class CFieldFactory {

    public static CTextField createTextField(String placeholder) {
        return new CTextField(false)
                .setFocusedBackgroundColor(AppColors.WHITE)
                .setBorderColor("darken(#ffffff, 5%)")
                .setBackgroundColor("darken(#ffffff, 5%)")
                .setPlaceholder(placeholder)
                .setFontStyle(CLabel.DEFAULT)
                .setTextMargin(10,12,10,12)
                .applyFlatStyle();
    }

    public static CTextField createBorderlessTextField(String placeholder) {
        return new CTextField(true)
                .setBorder("null")
                .setFocusedBackgroundColor(AppColors.WHITE)
                .setBackgroundColor("darken(#ffffff, 5%)")
                .setPlaceholder(placeholder)
                .setFontStyle(CLabel.DEFAULT)
                .setTextMargin(10,12,10,12)
                .applyFlatStyle();
    }

    public static CPasswordField createPasswordField(String placeholder) {
        return new CPasswordField()
                .setFocusedBackgroundColor(AppColors.WHITE)
                .setBorderColor("darken(#ffffff, 5%)")
                .setBackgroundColor("darken(#ffffff, 5%)")
                .setPlaceholder(placeholder)
                .setFontStyle(CLabel.DEFAULT)
                .setTextMargin(10,12,10,12)
                .applyFlatStyle();
    }
}
