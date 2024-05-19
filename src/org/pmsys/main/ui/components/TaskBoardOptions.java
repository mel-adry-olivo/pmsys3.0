package org.pmsys.main.ui.components;

public class TaskBoardOptions {

    public int getTotalHeaderSpace() {
        return Integer.parseInt(getHeaderHeight()) + Integer.parseInt(getCardGap()) + Integer.parseInt(getCardGap());
    }

    private String getHeaderHeight() {
        return "46";
    }

    private String getCardHeight() {
        return "0";
    }

    public String getCardGap() {
        return "16";
    }

    public String getSectionGap() {
        return "16";
    }

    public String getCardConstraints() {
        return "w 100%, h " + getCardHeight() + "%, gapbottom " + getCardGap() + "!";
    }

    public String getHeaderConstraints() {
        return "w 25%, h " + getHeaderHeight()  + "!";
    }

    public String getSectionConstraints() {
        return "w 25%, h 0%, growy";
    }
}
