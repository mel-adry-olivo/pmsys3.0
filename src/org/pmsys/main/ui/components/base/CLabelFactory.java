package org.pmsys.main.ui.components.base;

import org.pmsys.constants.AppColors;

public class CLabelFactory {

    private static CLabel createFlatLabel(String text, String fontStyle, String foregroundColor) {
        return new CLabel(text)
                .setFontStyle(fontStyle)
                .setForegroundColor(foregroundColor)
                .applyFlatStyle();
    }

    public static CLabel createLinkLabel(String text) {
        return createFlatLabel(text, CLabel.DEFAULT, AppColors.ACCENT).isLink();
    }

    public static CLabel createScaledH1Label(String text) {
        return createFlatLabel(text, CLabel.H1_120, AppColors.BLACK);
    }

    public static CLabel createH1Label(String text) {
        return createFlatLabel(text, CLabel.H1, AppColors.BLACK);
    }

    public static CLabel createH2Label(String text) {
        return createFlatLabel(text, CLabel.H2, AppColors.BLACK);
    }

    public static CLabel createH3Label(String text) {
        return createFlatLabel(text, CLabel.H3, AppColors.BLACK);
    }

    public static CLabel createSemiBoldLabel(String text, String colorCode) {
        return createFlatLabel(text, CLabel.SEMIBOLD, colorCode);
    }

    public static CLabel createSemiBoldLabel(String text) {
        return createFlatLabel(text, CLabel.SEMIBOLD, AppColors.BLACK);
    }

    public static CLabel createLargeSemiBoldLabel(String text) {
        return createFlatLabel(text, CLabel.LARGE_SEMIBOLD, AppColors.BLACK);
    }

    public static CLabel createLargeLabel(String text, String colorCode) {
        return createFlatLabel(text, CLabel.LARGE, colorCode);
    }

    public static CLabel createLargeLabel(String text) {
        return createFlatLabel(text, CLabel.LARGE, AppColors.BLACK);
    }

    public static CLabel createDefaultLabel(String text, String colorCode) {
        return createFlatLabel(text, CLabel.DEFAULT, colorCode);
    }

    public static CLabel createDefaultLabel(String text) {
        return createFlatLabel(text, CLabel.DEFAULT, AppColors.DARK_GREY);
    }

    public static CLabel createMediumLabel(String text, String colorCode) {
        return createFlatLabel(text, CLabel.MEDIUM, colorCode);
    }

    public static CLabel createMediumLabel(String text) {
        return createFlatLabel(text, CLabel.MEDIUM, AppColors.DARK_GREY);
    }

    public static CLabel createSmallLabel(String text, String colorCode) {
        return createFlatLabel(text, CLabel.SMALL, colorCode);
    }

    public static CLabel createSmallLabel(String text) {
        return createFlatLabel(text, CLabel.SMALL, AppColors.DARK_GREY);
    }

    public static CLabel createMiniLabel(String text, String colorCode) {
        return createFlatLabel(text, CLabel.MINI, colorCode);
    }



}
