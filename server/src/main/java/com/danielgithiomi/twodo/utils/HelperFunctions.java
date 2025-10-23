package com.danielgithiomi.twodo.utils;

import java.util.concurrent.TimeUnit;

public class HelperFunctions {

    public static Long hrsToMillis(int timeInHrs) {
        return TimeUnit.HOURS.toMillis(timeInHrs);
    }

    public static Long minutesToMillis(int timeInMinutes) {
        return TimeUnit.MINUTES.toMillis(timeInMinutes);
    }

}
