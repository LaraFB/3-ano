package com.example.gps_g11.Data;

import com.example.gps_g11.Data.categoryManagment.Category;
import com.example.gps_g11.Data.categoryManagment.CategoryHandler;

public class Context {
    private static Context instance;

    private CategoryHandler categoryHandler;
    private Budget budget;

    private Context() {
        this.categoryHandler = new CategoryHandler();
        this.budget = new Budget(200);
        budget.setBolsa(new Bolsa("ISEC",120,"Bolsa de estudo"));
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

    public String getCategoryName(int i) {
        return categoryHandler.getCategoryName(i);
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

}
