package com.example.gps_g11.Data.Expenses;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpensesHistory {
    private List<Expense> expenses;

    static final String fileName = "DataBase.dat";

    public ExpensesHistory() {
        expenses = new ArrayList<>();
    }

    public void addExpense(String name, String category, String description, LocalDate date, float value, boolean recurring) {
        Expense expense = new Expense(name, category, description, date, value, recurring);
        expenses.add(expense);

    }

    public double getTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getValue();
        }
        return total;
    }

    public List<Expense> getExpensesByCategory(String targetCategory) {
        List<Expense> result = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(targetCategory)) {
                result.add(expense);
            }
        }
        return result;
    }

    public List<Expense> getExpensesByDate(Date targetDate) {
        List<Expense> result = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getDate().equals(targetDate)) {
                result.add(expense);
            }
        }
        return result;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(expenses);
            System.out.println("Expenses saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            expenses = (List<Expense>) ois.readObject();
            System.out.println("Expenses loaded from " + fileName);
        }catch (FileNotFoundException ignored) {

        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Expense expense : expenses) {
            sb.append(expense.toString()).append("\n");
        }
        return sb.toString();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void deleteExpense(Expense expense) {
        expenses.remove(expense);
    }

    public boolean editExpense(Expense expense,float value,LocalDate date,String descripton){
        for (Expense expens : expenses) {
            if(expens == expense){
                expens.setValue(value);
                expens.setDate(date);
                expens.setDescription(descripton);
                return true;
            }
        }
        return false;
    }
}
