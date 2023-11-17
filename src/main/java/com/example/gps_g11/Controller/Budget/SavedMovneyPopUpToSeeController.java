package com.example.gps_g11.Controller.Budget;

import com.example.gps_g11.Data.Budget.Envelope;
import com.example.gps_g11.Data.Context;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class SavedMovneyPopUpToSeeController {
    public BorderPane root;
    public TextField TFFinalidade;
    public TextField TFValor;
    public Label LError;
    private Context context;
    private BudgetPaneController budgetPaneController;

    private Envelope envelope;
    public void initialize(){
        context = Context.getInstance();
    }

    public void setBudgetPane(BudgetPaneController budgetPaneController, Envelope envelope) {
        this.budgetPaneController = budgetPaneController;
        this.envelope = envelope;
        TFValor.setText(String.valueOf(envelope.getValor()));
        TFFinalidade.setText(envelope.getFinalidade());
    }

    public void onClose() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();

        budgetPaneController.acaoAoFecharJanelaSecundaria();
    }


}
