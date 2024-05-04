package org.pmsys.main.ui.components.base;

import org.pmsys.constants.AppColors;

public class FlatFieldFactory {

    public static FlatTextField createTextField(String placeholder) {
        return new FlatTextField()
                .setFocusedBackgroundColor(AppColors.WHITE)
                .setBorderColor("darken(#ffffff, 5%)")
                .setBackgroundColor("darken(#ffffff, 5%)")
                .setPlaceholder(placeholder)
                .setFontStyle(FlatLabel.DEFAULT)
                .setTextMargin(10,12,10,12)
                .applyFlatStyle();
    }

    public static FlatPasswordField createPasswordField(String placeholder) {
        return new FlatPasswordField()
                .setFocusedBackgroundColor(AppColors.WHITE)
                .setBorderColor("darken(#ffffff, 5%)")
                .setBackgroundColor("darken(#ffffff, 5%)")
                .setPlaceholder(placeholder)
                .setFontStyle(FlatLabel.DEFAULT)
                .setTextMargin(10,12,10,12)
                .applyFlatStyle();
    }
}
