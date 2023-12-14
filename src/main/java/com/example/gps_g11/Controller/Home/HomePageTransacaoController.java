package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class HomePageTransacaoController {
    public ChoiceBox <String>cb1;
    public TextField tfValor;
    public Label lblError;
    public Label lblError1;
    private Context context;
    private SideBarController sideBarController;

    public void initialize() {
        context = Context.getInstance();
        cb1.getItems().addAll("Depositei dinheiro", "Levantei dinheiro");
        cb1.setValue("Escolhe");
        tfValorFormat();
        lblError.setVisible(false);
        lblError1.setVisible(false);
    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void Transferir() {

        if( tfValor.getText().isEmpty()|| cb1.getValue() == null){
            lblError.setVisible(true);
            lblError1.setVisible(true);
            lblError.setTextFill(Color.RED);
            lblError.setText("Preencha todos os campos");
        }else{
            lblError.setVisible(true);
            lblError1.setVisible(false);
            double valor = Double.parseDouble(tfValor.getText());
            if(valor == 0 ){
                lblError.setVisible(true);
                lblError1.setVisible(true);
                lblError.setTextFill(Color.RED);
                lblError.setText("Introduza um valor positivo");
            }else {
                if (cb1.getValue().equals("Levantamento de dinheiro")) {
                    if (context.getSaldo().getBudgetContaBancaria().getSaldoReal() < valor) {
                        lblError.setTextFill(Color.RED);
                        lblError.setText("Saldo insuficiente");
                    } else {
                        context.getSaldo().getBudgetContaBancaria().setSaldoReal(context.getSaldo().getBudgetContaBancaria().getSaldoReal() - valor);
                        context.getSaldo().getBudgetDinheiro().setSaldoReal(context.getSaldo().getBudgetDinheiro().getSaldoReal() + valor);
                        lblError.setTextFill(Color.GREEN);
                        lblError.setText("Transferência realizada com sucesso");
                    }
                } else {
                    if (context.getSaldo().getBudgetDinheiro().getSaldoReal() < valor) {
                        lblError.setTextFill(Color.RED);
                        lblError.setText("Saldo insuficiente");
                    } else {
                        context.getSaldo().getBudgetDinheiro().setSaldoReal(context.getSaldo().getBudgetDinheiro().getSaldoReal() - valor);
                        context.getSaldo().getBudgetContaBancaria().setSaldoReal(context.getSaldo().getBudgetContaBancaria().getSaldoReal() + valor);
                        lblError.setTextFill(Color.GREEN);
                        lblError.setText("Transferência realizada com sucesso");
                    }
                }
            }
        }
    }

    private void tfValorFormat() {
        Pattern pattern = Pattern.compile("^\\d*\\.?\\d{0,2}$");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (pattern.matcher(newText).matches()) {
                return change;
            }
            return null;
        };
        StringConverter<String> converter = new StringConverter<>() {
            @Override
            public String fromString(String string) {
                return string;
            }

            @Override
            public String toString(String object) {
                return object;
            }
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(converter, "", filter);
        tfValor.setTextFormatter(textFormatter);
    }

    public void onBackToHomePage() {
       sideBarController.onHomePage();
    }
}
