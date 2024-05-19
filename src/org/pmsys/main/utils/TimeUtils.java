package org.pmsys.main.utils;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static void delayInMillis(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
