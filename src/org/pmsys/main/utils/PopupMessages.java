package org.pmsys.main.utils;

import javax.swing.*;

public class PopupMessages {
    public static boolean CONFIRM_DELETION(String itemType) {
        int result = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete this " + itemType + "?",
                "Confirm " + itemType + " Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        return result == JOptionPane.YES_OPTION;
    }
}
