    package com.example.gps_g11.Controller;

    import com.example.gps_g11.Data.Context;
    import javafx.scene.control.TextField;
    import javafx.scene.control.TextFormatter;
    import javafx.scene.layout.BorderPane;
    import javafx.stage.Stage;
    import javafx.util.converter.IntegerStringConverter;
    import javafx.util.converter.NumberStringConverter;

    import java.text.DecimalFormat;
    import java.text.ParsePosition;
    import java.util.function.UnaryOperator;
    import java.util.regex.Pattern;

    public class BudgetPanePopUpController {
        public BorderPane root;
        BudgetPaneController budgetPaneController;

        public TextField TFMontante;

        private Context context;

        public void initialize(){
            context = Context.getInstance();
            initializeTFMontante();

        }

        public void setBudgetPane(BudgetPaneController budgetPaneController) {
            this.budgetPaneController = budgetPaneController;
        }

        public void onAddBudget() {
            context.addMontante(Integer.parseInt(TFMontante.getText()));

            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();

            budgetPaneController.acaoAoFecharJanelaSecundaria();
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
