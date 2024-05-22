package org.pmsys.main.ui.utils;

public class Debugger {
    public static void printCallerInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println("Method called from:");
        for (int i = 2; i < stackTrace.length; i++) {
            System.out.println("\tat " + stackTrace[i]);
        }
    }
}
