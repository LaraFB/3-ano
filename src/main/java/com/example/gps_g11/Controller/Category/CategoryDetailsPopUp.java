package com.example.gps_g11.Controller.Category;

import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Expenses.Expense;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CategoryDetailsPopUp {
    public Label lblCategoria;
    public TextField tfMontante;
    public DatePicker dataPicker;
    public TextArea textArea;
    public Button btnEdit;
    public Button btnDelete;
    public Button btnDone;
    public Button btnCancel;
    public Label lblError;
    public BorderPane root;
    private Expense expense;
    private CategoryController categoryController;
    private Context context;
    public void setData(CategoryController categoryController, Expense expense,boolean isEdit) {
        this.categoryController = categoryController;
        this.expense = expense;
        btnEdit.setDisable(isEdit);
        btnDone.setVisible(isEdit);
        btnCancel.setVisible(isEdit);

        lblCategoria.setText(expense.getCategory());


        tfMontante.setEditable(isEdit);
        tfMontante.setText(String.valueOf(expense.getValue()));
        textArea.setEditable(isEdit);
        textArea.setText(expense.getDescription());
        dataPicker.setDisable(!isEdit);
        dataPicker.setValue(expense.getDate());
        lblError.setVisible(false);
    }
    public void initialize(){
        context = Context.getInstance();
        tfMontante.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onDone();
            }
        });
        textArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onDone();
            }
        });
        dataPicker.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onDone();
            }
        });

    }


    public void onCancel(){
        activateElements(false);
    }
    public void onEdit(){
        activateElements(true);
    }

    public void activateElements(boolean val){
        tfMontante.setEditable(val);
        textArea.setEditable(val);
        dataPicker.setDisable(!val);
        btnEdit.setDisable(val);
        btnDone.setVisible(val);
        btnCancel.setVisible(val);
    }

    public void onDone() {
        String errorMessage = "Preencha os campos todos para proceder a editação";

        if (tfMontante.getText().isEmpty() || textArea.getText().isEmpty() || dataPicker.getValue() == null) {
            lblError.setVisible(true);
            lblError.setText(errorMessage);
        } else {
            categoryController.editExpense(expense, Float.parseFloat(tfMontante.getText()),dataPicker.getValue(),textArea.getText());
            activateElements(false);
            lblError.setVisible(false);
        }
    }

    public void onDelete(){
        categoryController.delete(expense);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
        categoryController.acaoAoFecharJanelaSecundaria();
    }


}
