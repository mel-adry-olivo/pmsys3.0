package org.pmsys.main.utils;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static boolean isPastDate(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    public static LocalDate addDaysToCurrentDate(int days) {
        return LocalDate.now().plusDays(days);
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("M-d-yyyy"));
    }

    public static LocalDate parseFormattedDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }

    public static String getFormattedDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }

    public static Integer getCurrentYear() {
        return Year.now().getValue();
    }

    public static String[] getMonths() {
        return new String[]{ "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };
    }

    public static Integer[] getYearsStartingToday(int yearCount) {
        Integer[] years = new Integer[yearCount];
        int currentYear = Year.now().getValue();

        for (int i = 0; i < yearCount; i++) {
            years[i] = currentYear + i;
        }

        return years;
    }

    public static Integer[] getDaysInMonth(int year, int month) {
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
        Integer[] days = new Integer[daysInMonth];

        for (int i = 0; i < daysInMonth; i++) {
            days[i] = i + 1;
        }

        return days;
    }

    public static long getDaysBetweenDates(String dueDate) {
        LocalDate date = parseFormattedDate(dueDate);
        LocalDate today = LocalDate.now();

        return ChronoUnit.DAYS.between(today, date);
    }
}
