package org.pmsys.main.ui.components.base;

import org.pmsys.constants.AppColors;

public class FlatLabelFactory {

    private static FlatLabel createFlatLabel(String text, String fontStyle, String foregroundColor) {
        return new FlatLabel(text)
                .setFontStyle(fontStyle)
                .setForegroundColor(foregroundColor)
                .applyFlatStyle();
    }

    public static FlatLabel createH1Label(String text) {
        return createFlatLabel(text, FlatLabel.H1, AppColors.BLACK);
    }

    public static FlatLabel createH2Label(String text) {
        return createFlatLabel(text, FlatLabel.H2, AppColors.BLACK);
    }

    public static FlatLabel createH3Label(String text) {
        return createFlatLabel(text, FlatLabel.H3, AppColors.BLACK);
    }

    public static FlatLabel createLargeLabel(String text, String colorCode) {
        return createFlatLabel(text, FlatLabel.LARGE, colorCode);
    }

    public static FlatLabel createLargeLabel(String text) {
        return createFlatLabel(text, FlatLabel.LARGE, AppColors.BLACK);
    }

    public static FlatLabel createDefaultLabel(String text, String colorCode) {
        return createFlatLabel(text, FlatLabel.DEFAULT, colorCode);
    }

    public static FlatLabel createDefaultLabel(String text) {
        return createFlatLabel(text, FlatLabel.DEFAULT, AppColors.DARK_GREY);
    }

    public static FlatLabel createMediumLabel(String text, String colorCode) {
        return createFlatLabel(text, FlatLabel.MEDIUM, colorCode);
    }

    public static FlatLabel createMediumLabel(String text) {
        return createFlatLabel(text, FlatLabel.MEDIUM, AppColors.DARK_GREY);
    }



    public static FlatLabel createSmallLabel(String text) {
        return createFlatLabel(text, FlatLabel.SMALL, AppColors.DARK_GREY);
    }




}
