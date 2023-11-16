package com.example.gps_g11.Data;

import com.example.gps_g11.Data.Budget.Bolsa;
import com.example.gps_g11.Data.Budget.Budget;
import com.example.gps_g11.Data.Budget.Envelope;
import com.example.gps_g11.Data.Expenses.Expense;
import com.example.gps_g11.Data.Expenses.ExpensesHistory;
import com.example.gps_g11.Data.categoryManagment.Category;
import com.example.gps_g11.Data.categoryManagment.CategoryHandler;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Context {
    private static Context instance;
    private ExpensesHistory expensesHistory;
    private CategoryHandler categoryHandler;
    private Budget budget;

    private Context() {
        this.categoryHandler = new CategoryHandler();
        this.budget = new Budget(200);
        budget.setBolsa(new Bolsa("ISEC",120,"Bolsa de estudo"));
        expensesHistory = new ExpensesHistory();
        expensesHistory.loadFromFile();
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public double getBudgetRestante() {
        return budget.getBudgetRestante();
    }

    public double getBudgetGasto() {
        return budget.getBudgetGasto();
    }

    public void addCategory(String name) {
        categoryHandler.addCategory(name);
    }

    public void addCategory(String name, String descripton) {
        categoryHandler.addCategory(name,descripton);
    }

    public boolean isEmpty() {
        return categoryHandler.isEmpty();
    }

    public Category getCategory(int i) {
        return categoryHandler.getCategory(i);
    }

    public Category getCategory(String name) {
        return categoryHandler.getCategory(name);
    }

    public String getCategoryName(int i) {
        return categoryHandler.getCategoryName(i);
    }

    public boolean deleteCategory(String name){
        return categoryHandler.removeCategory(name);
    }

    public void addMontante(int montante){
        budget.setBudgetRestante(budget.getBudgetRestante()+montante);
    }

    public String getNomeBolsa(){
        return budget.getBolsa().getNome();
    }
    public double getValorBolsa(){
        return budget.getBolsa().getValor();
    }

    public double getValorGastoBolsa(){
        return budget.getBolsa().getValorGasto();
    }


    public void addExpense(String name, String category, String description, LocalDate date, float value, boolean recurring) {
        expensesHistory.addExpense(name, category, description, date, value, recurring);
        budget.adicionarAoBudgetGasto(value);
    }

    public double getTotalExpenses() {
        return expensesHistory.getTotalExpenses();
    }


    public void saveToFileExpenses() {
        expensesHistory.saveToFile();
    }

    public void loadFromFileExpenses() {
        expensesHistory.loadFromFile();
    }

    public String ExpensestoString() {
        return expensesHistory.toString();
    }

    public List<Expense> getExpensesHistory() {
        return expensesHistory.getExpenses();
    }

    public void deleteExpense(Expense expense) {
        expensesHistory.deleteExpense(expense);
    }
    public void criarEnvelope(String finalidade,double valor){
        budget.criarEnvelope(finalidade, valor);
    }

    public double getBudgetGuardado() {
        return budget.getBudgetGuardado();
    }

    public List<Envelope> getEnvelopes(){
        return budget.getEnvelopes();
    }
}
