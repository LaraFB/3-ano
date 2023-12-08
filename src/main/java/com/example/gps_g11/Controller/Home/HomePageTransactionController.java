package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageTransactionController implements Initializable {

    @FXML ChoiceBox cb1;
    @FXML ChoiceBox cb2;
    @FXML Spinner amount;
    @FXML Label lblError;

    private Context context;
    private SideBarController sideBarController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = Context.getInstance();
        cb1.getItems().addAll("Dinheiro", "Banco");
        cb2.getItems().addAll("Dinheiro", "Banco");
        cb1.setValue("Banco");
        cb2.setValue("Dinheiro");
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, (int) Math.max(context.getSaldo().getBudgetDinheiro().getSaldoReal(), context.getSaldo().getBudgetContaBancaria().getSaldoReal()), 1);
        amount.setValueFactory(valueFactory);
    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void Transferir(ActionEvent actionEvent) {
        Double valor = (Double) amount.getValue();

        if(cb1.getValue().equals("Dinheiro") && cb2.getValue().equals("Banco")){
            if (context.getSaldo().getBudgetContaBancaria().getSaldoReal() < valor) {
                lblError.setText("Saldo insuficiente");
            }else {
                context.getSaldo().getBudgetContaBancaria().setSaldoReal(context.getSaldo().getBudgetContaBancaria().getSaldoReal() - valor);
                context.getSaldo().getBudgetDinheiro().setSaldoReal(context.getSaldo().getBudgetDinheiro().getSaldoReal() + valor);
            }
        }else {
            if (context.getSaldo().getBudgetDinheiro().getSaldoReal() < valor) {
                lblError.setText("Saldo insuficiente");
            }else {
                context.getSaldo().getBudgetDinheiro().setSaldoReal(context.getSaldo().getBudgetDinheiro().getSaldoReal() - valor);
                context.getSaldo().getBudgetContaBancaria().setSaldoReal(context.getSaldo().getBudgetContaBancaria().getSaldoReal() + valor);
            }
        }
    }

    public void change2(ActionEvent actionEvent) {
        if(cb1.getValue().equals("Dinheiro")){
            cb2.setValue("Banco");
        }else {
            cb2.setValue("Dinheiro");
        }
    }

    public void change1(ActionEvent actionEvent) {
        if(cb2.getValue().equals("Dinheiro")){
            cb1.setValue("Banco");
        }else {
            cb1.setValue("Dinheiro");
        }
    }

    public void onBackToHomePage(ActionEvent actionEvent) {
        sideBarController.onHomePage();
    }
}
