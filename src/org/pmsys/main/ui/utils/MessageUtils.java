package org.pmsys.main.ui.utils;

import javax.swing.*;

public class MessageUtils {
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
    public static void SUCCESS(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    public static void ERROR(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
