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

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
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
