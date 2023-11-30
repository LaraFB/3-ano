    package com.example.gps_g11.Controller.NaoVaiSerPreciso.Budget;

    import com.example.gps_g11.Data.Context;
    import javafx.scene.control.Label;
    import javafx.scene.control.TextField;
    import javafx.scene.control.TextFormatter;
    import javafx.scene.input.KeyCode;
    import javafx.scene.layout.BorderPane;
    import javafx.stage.Stage;
    import javafx.util.converter.IntegerStringConverter;

    import java.util.function.UnaryOperator;
    import java.util.regex.Pattern;

    public class BudgetPanePopUpController {
        public BorderPane root;
        public Label LError;
        BudgetPaneController budgetPaneController;

        public TextField TFMontante;

        private Context context;

        public void initialize(){
            context = Context.getInstance();
            initializeTFMontante();
            TFMontante.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    onAddBudget();
                }
            });
        }

        public void setBudgetPane(BudgetPaneController budgetPaneController) {
            this.budgetPaneController = budgetPaneController;
        }

        public void onAddBudget() {
            if(TFMontante.getText().isEmpty() || TFMontante.getText().equals("0")){
                LError.setVisible(true);
                LError.setText("Preenche o campo com o valor");
            }else{
                onClose();
            }
        }

        public void onClose() {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();

            budgetPaneController.acaoAoFecharJanelaSecundaria();
        }

        private void initializeTFMontante() {
            Pattern validInput = Pattern.compile("\\d*");

            UnaryOperator<TextFormatter.Change> filter = change -> {
                if (validInput.matcher(change.getControlNewText()).matches()) {
                    return change;
                } else {
                    return null;
                }
            };

            TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, filter);
            TFMontante.setTextFormatter(textFormatter);

        }
    }
