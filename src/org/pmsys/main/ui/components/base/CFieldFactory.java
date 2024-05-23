package org.pmsys.main.ui.components.base;

import org.pmsys.main.ui.components.constants.ColorConstants;

public class CFieldFactory {

    public static CTextField createTextField(String placeholder) {
        return new CTextField(false)
                .setFocusedBackgroundColor(ColorConstants.WHITE)
                .setBorderColor("darken(#ffffff, 5%)")
                .setBackgroundColor("darken(#ffffff, 5%)")
                .setPlaceholder(placeholder)
                .setFontStyle(CLabel.DEFAULT)
                .setTextMargin(10,12,10,12)
                .applyStyles();
    }

    public static CTextField createBorderlessTextField(String placeholder) {
        return new CTextField(true)
                .setBorder("null")
                .setFocusedBackgroundColor(ColorConstants.WHITE)
                .setBackgroundColor("darken(#ffffff, 5%)")
                .setPlaceholder(placeholder)
                .setFontStyle(CLabel.DEFAULT)
                .setTextMargin(10,12,10,12)
                .applyStyles();
    }

    public static CPasswordField createPasswordField(String placeholder) {
        return new CPasswordField()
                .setFocusedBackgroundColor(ColorConstants.WHITE)
                .setBorderColor("darken(#ffffff, 5%)")
                .setBackgroundColor("darken(#ffffff, 5%)")
                .setPlaceholder(placeholder)
                .setFontStyle(CLabel.DEFAULT)
                .setTextMargin(10,12,10,12)
                .applyStyles();
    }
}
