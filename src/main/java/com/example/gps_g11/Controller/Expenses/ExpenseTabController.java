package com.example.gps_g11.Controller.Expenses;

import com.example.gps_g11.Controller.Category.CategoryController;
import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Expenses.ExpensesHistory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

public class ExpenseTabController {

    public TextField Value;
    public TextField Name;
    public DatePicker DatePicker;
    public CheckBox Recurring;
    public ChoiceBox CategoriaCheckbox;
    public Label lblResult;
    public TextArea Descripton;
    private SideBarController sideBarController;

    public TextField CategoryName;
    public TextField CategoryDescription;
    public Label errorMsg;
    public Label sucessMsg;

    private Context context;
    //private String[] categorias ={"Cafe","Compras","Propinas","Renda","Refeiçoes na Cantina","Outra"};

    public void initialize(){
        context = Context.getInstance();
        int i=0;
        CategoriaCheckbox.getItems().add("Empty");
        CategoriaCheckbox.setValue("Empty");
        while(context.getCategory(i) != null){
            CategoriaCheckbox.getItems().add(context.getCategoryName(i));
            i++;
        }

        errorMsg.setVisible(false);
        sucessMsg.setVisible(false);

        Value.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                AddExpense();
            }
        });
        Name.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                AddExpense();
            }
        });
        Descripton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                AddExpense();
            }
        });
        CategoryName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                AddCategory();
            }
        });
        CategoryDescription.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                AddCategory();
            }
        });

    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void AddExpense() {
        if(Name.getText().isEmpty() || Value.getText().isEmpty() || CategoriaCheckbox.getValue().toString().equals("Empty") || DatePicker.getValue().toString().isEmpty())
            lblResult.setText("Preencha todos os campos");
        else{
            boolean result = context.addExpense(Name.getText(), CategoriaCheckbox.getValue().toString(), Descripton.getText(), DatePicker.getValue(), Float.parseFloat(Value.getText()), Recurring.isSelected());
            if(result){
                lblResult.setText("Despesa adicionada com sucesso");
            }else{
                lblResult.setText("Não tem saldo suficiente para adicionar esta despesa");
            }
        }
    }

    public void CancelExpense() {
        Name.setText("");
        Value.setText("");
        Descripton.setText("");
        DatePicker.setValue(null);
        Recurring.setSelected(false);
        CategoriaCheckbox.setValue(null);
        lblResult.setText("");
    }

    //category tab
    public void AddCategory(){
        if(CategoryName.getText().isEmpty()){
            errorMsg.setVisible(true);
            return;
        }

        if(CategoryDescription.getText().isEmpty())
            context.addCategory(CategoryName.getText());
        else
            context.addCategory(CategoryName.getText(),CategoryDescription.getText());

        System.out.println(context.getCategory(0).getName());
        CancelCategory();
        sucessMsg.setVisible(true);
    }

    public void CancelCategory(){
        CategoryName.setText("");
        CategoryDescription.setText("");
        errorMsg.setVisible(false);
        sucessMsg.setVisible(false);
    }
}
