package org.pmsys.main.ui.components.base;

import org.pmsys.main.utils.DateUtils;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;


public class FlatDatePicker extends FlatPanel{

    private FlatComboBox<String> monthPicker;
    private FlatComboBox<Integer> dayPicker;
    private FlatComboBox<Integer> yearPicker;

    public FlatDatePicker() {
        setupComponent();
        setupDayPickerEvent();
    }

    public LocalDate getSelectedDate() {
        return DateUtils.parseDate(getSelectedMonthIndex() + "-" + getSelectedDay() + "-" + getSelectedYear());
    }

    public String getFormattedDate() {
        return DateUtils.getFormattedDate(getSelectedDate());
    }

    public void resetDates() {
        monthPicker.setSelectedIndex(0);
        dayPicker.setSelectedIndex(0);
        yearPicker.setSelectedIndex(0);
    }

    public FlatDatePicker setBorderColor(String color) {
        monthPicker.setBorderColor(color);
        dayPicker.setBorderColor(color);
        yearPicker.setBorderColor(color);
        return this;
    }

    @Override
    public FlatPanel applyFlatStyle() {
        super.applyFlatStyle();
        monthPicker.applyFlatStyle();
        dayPicker.applyFlatStyle();
        yearPicker.applyFlatStyle();
        return this;
    }

    private void setupDayPickerEvent() {
        ActionListener daysUpdater = e -> updateDaysPicker();
        ActionListener borderReset = e -> resetBorders();

        monthPicker.addActionListener(daysUpdater);
        dayPicker.addActionListener(borderReset);
        yearPicker.addActionListener(daysUpdater);
    }
    private void updateDaysPicker() {
        Integer[] days = DateUtils.getDaysInMonth(getSelectedYear(), getSelectedMonthIndex());
        dayPicker.setModel(new DefaultComboBoxModel<>(days));
        resetBorders();
    }

    private void resetBorders() {
        monthPicker.setBorderColor("darken(#ffffff, 23%)").applyFlatStyle();
        dayPicker.setBorderColor("darken(#ffffff, 23%)").applyFlatStyle();
        yearPicker.setBorderColor("darken(#ffffff, 23%)").applyFlatStyle();
    }

    private void setupComponent() {
        setConstraints("insets 0, fillx");

        String[] months = DateUtils.getMonths();
        Integer[] years = DateUtils.getYearsStartingToday(10);
        Integer[] days = DateUtils.getDaysInMonth(DateUtils.getCurrentYear(), 1);

        monthPicker = new FlatComboBox<>(months);
        dayPicker = new FlatComboBox<>(days);
        yearPicker = new FlatComboBox<>(years);

        add(monthPicker, "w 100%");
        add(dayPicker, "w 100%");
        add(yearPicker, "w 100%");
    }

    private Integer getSelectedYear() {
        return (Integer) yearPicker.getSelectedItem();
    }
    private int getSelectedMonthIndex() {
        return monthPicker.getSelectedIndex() + 1;
    }
    private int getSelectedDay() {
        return dayPicker.getSelectedIndex() + 1;
    }

    public void setDate(String dueDate) {
        LocalDate date = DateUtils.parseFormattedDate(dueDate);

        int monthIndex = date.getMonthValue() - 1;
        int day = date.getDayOfMonth();
        int year = date.getYear();

        monthPicker.setSelectedIndex(monthIndex);
        yearPicker.setSelectedItem(year);
        dayPicker.setSelectedItem(day);
    }

    private int findYearIndex(int year) {
        for (int i = 0; i < yearPicker.getItemCount(); i++) {
            if (yearPicker.getItemAt(i) == year) {
                return i;
            }
        }
        return 0;
    }
}
