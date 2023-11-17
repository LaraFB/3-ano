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
    static final String fileName = "DataBase.dat";
    private static Context instance;
    private ContextData contextData;

    private Context() {
        contextData = new ContextData();
        loadFromFile();
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public double getBudgetRestante() {
        return contextData.getBudget().getBudgetRestante();
    }

    public double getBudgetGasto() {
        return contextData.getBudget().getBudgetGasto();
    }

    public void addCategory(String name) {
        contextData.getCategoryHandler().addCategory(name);
    }

    public void addCategory(String name, String descripton) {
        contextData.getCategoryHandler().addCategory(name,descripton);
    }

    public boolean isEmpty() {
        return contextData.getCategoryHandler().isEmpty();
    }

    public Category getCategory(int i) {
        return contextData.getCategoryHandler().getCategory(i);
    }

    public Category getCategory(String name) {
        return contextData.getCategoryHandler().getCategory(name);
    }

    public String getCategoryName(int i) {
        return contextData.getCategoryHandler().getCategoryName(i);
    }

    public boolean deleteCategory(String name){
        return contextData.getCategoryHandler().removeCategory(name);
    }

    public void addMontante(int montante){
        contextData.getBudget().setBudgetRestante(contextData.getBudget().getBudgetRestante()+montante);
    }

    /*public String getNomeBolsa(){
        return contextData.getBudget().getBolsa().getNome();
    }
    public double getValorBolsa(){
        return contextData.getBudget().getBolsa().getValor();
    }

    public double getValorGastoBolsa(){
        return contextData.getBudget().getBolsa().getValorGasto();
    }*/


    public boolean addExpense(String name, String category, String description, LocalDate date, float value, boolean recurring) {
        if(value < contextData.getBudget().getBudgetRestante()){
            contextData.getExpensesHistory().addExpense(name, category, description, date, value, recurring);
            contextData.getBudget().adicionarAoBudgetGasto(value);
            return true;
        }
        return false;
    }

    public double getTotalExpenses() {
        return contextData.getExpensesHistory().getTotalExpenses();
    }


    /*public void saveToFileExpenses() {
        contextData.getExpensesHistory().saveToFile();
    }

    public void loadFromFileExpenses() {
        contextData.getExpensesHistory().loadFromFile();
    }*/

    public String ExpensestoString() {
        return contextData.getCategoryHandler().toString();
    }

    public List<Expense> getExpensesHistory() {
        return contextData.getExpensesHistory().getExpenses();
    }

    public void deleteExpense(Expense expense) {
        contextData.getExpensesHistory().deleteExpense(expense);
        contextData.getBudget().retirarDoBudgetGasto(expense.getValue());
    }
   /* public void criarEnvelope(String finalidade,double valor){
        contextData.getBudget().criarEnvelope(finalidade, valor);
    }*/

    public double getBudgetGuardado() {
        return contextData.getBudget().getBudgetGuardado();
    }

    /*public List<Envelope> getEnvelopes(){
        return contextData.getBudget().getEnvelopes();
    }*/

    public void editExpense(Expense expense, float value, LocalDate date, String descripton) {
        contextData.getBudget().adicionarAoBudgetGasto(value-expense.getValue());
        contextData.getExpensesHistory().editExpense(expense,value,date,descripton);
    }


    public void saveToFile() {
        if (contextData != null) {
            contextData.saveToFile(fileName);
        }
    }

    private void loadFromFile() {
        contextData = ContextData.loadFromFile(fileName);
    }
}
