package com.example.gps_g11.Data;

import com.example.gps_g11.Data.Budget.Bolsa;
import com.example.gps_g11.Data.Budget.Budget;
import com.example.gps_g11.Data.Expenses.ExpensesHistory;
import com.example.gps_g11.Data.categoryManagment.CategoryHandler;

import java.io.*;

public class ContextData implements Serializable {
    private ExpensesHistory expensesHistory;
    private CategoryHandler categoryHandler;
    private Budget budget;

    public ContextData() {
        this.categoryHandler = new CategoryHandler();
        this.budget = new Budget(0);
        expensesHistory = new ExpensesHistory();
    }
    public ExpensesHistory getExpensesHistory() {
        return expensesHistory;
    }

    public CategoryHandler getCategoryHandler() {
        return categoryHandler;
    }

    public Budget getBudget() {
        return budget;
    }
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("ContextData saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ContextData loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ContextData) ois.readObject();
        } catch (FileNotFoundException ignored) {
            return new ContextData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ContextData();
        }
    }

}
