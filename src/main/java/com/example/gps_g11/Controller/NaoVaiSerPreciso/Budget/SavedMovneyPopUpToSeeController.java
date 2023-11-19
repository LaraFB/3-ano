package com.example.gps_g11.Controller.NaoVaiSerPreciso.Budget;

import com.example.gps_g11.Data.Context;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SavedMovneyPopUpToSeeController {
    public BorderPane root;
    public TextField TFFinalidade;
    public TextField TFValor;
    public Label LError;
    private Context context;
    private BudgetPaneController budgetPaneController;


    public void initialize(){
        context = Context.getInstance();
    }

    public void setBudgetPane(BudgetPaneController budgetPaneController) {
        this.budgetPaneController = budgetPaneController;
    }

    public void onClose() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();

        budgetPaneController.acaoAoFecharJanelaSecundaria();
    }


}
