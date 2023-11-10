package com.example.gps_g11.Controller;

import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Expenses.ExpensesHistory;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class ExpenseTabController {

    public TextField Value;
    public TextField Description;
    public TextField Name;
    public DatePicker DatePicker;
    public CheckBox Recurring;
    public ChoiceBox CategoriaCheckbox;
    public Label lblResult;
    SideBarController sideBarController;

    private Context context;
    private String[] categorias ={"Cafe","Compras","Propinas","Renda","Refeiçoes na Cantina","Outra"};

    public void initialize(){
        context = Context.getInstance();
        CategoriaCheckbox.getItems().setAll(categorias);

    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void AddExpense(ActionEvent actionEvent) {
        if(Name.getText().isEmpty() || Value.getText().isEmpty() || CategoriaCheckbox.getValue().toString().isEmpty() || DatePicker.getValue().toString().isEmpty())
            lblResult.setText("Preencha todos os campos");
        else{
            context.addExpense(Name.getText(), CategoriaCheckbox.getValue().toString(), Description.getText(), DatePicker.getValue(), Float.parseFloat(Value.getText()), Recurring.isSelected());
            lblResult.setText("Despesa adicionada com sucesso");
        }
    }

    public void CancelExpense(ActionEvent actionEvent) {
        Name.setText("");
        Value.setText("");
        Description.setText("");
        DatePicker.setValue(null);
        Recurring.setSelected(false);
        CategoriaCheckbox.setValue(null);
        lblResult.setText("");
    }
}
