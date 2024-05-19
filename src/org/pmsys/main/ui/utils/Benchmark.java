package org.pmsys.main.ui.utils;

public class Benchmark {
    public static void of(Runnable runnable, String methodName) {
        long startTime = System.currentTimeMillis();
        runnable.run();
        long endTime = System.currentTimeMillis();
        long saveTime = endTime - startTime;
        System.out.println(methodName + " time elapsed: " + saveTime + " milliseconds");
    }

    public static void of(Runnable runnable) {
        long startTime = System.currentTimeMillis();
        runnable.run();
        long endTime = System.currentTimeMillis();
        long saveTime = endTime - startTime;
        System.out.println("Time elapsed: " + saveTime + " milliseconds");
    }
}
