package com.example.gps_g11.Data.Expenses;

import java.io.Serializable;
import java.time.LocalDate;

public class Expense implements Serializable {
    private String name;
    private String category;
    private String description;
    private LocalDate date;
    private float value;

    private boolean recurring;

    public Expense(String name, String category, String description, LocalDate date, float value, boolean recurring) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.date = date;
        this.value = value;
        this.recurring = recurring;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", value=" + value +
                ", recurring=" + recurring +
                '}';
    }
}
