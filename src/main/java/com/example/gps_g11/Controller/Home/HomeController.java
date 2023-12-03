package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.NaoVaiSerPreciso.Budget.BudgetPanePopUpController;
import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class HomeController {

    public Label lblSaldoRealCB;
    public Label lblSaldoRealD;
    public Label lblSaldoTotal;
    public Label lblSaldoDistribuir;
    public Label lblSaldoEnvelopes;
    public Label lblTotalDespesas;
    private Context context;

    public BorderPane root;
    private SideBarController sideBarController;

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void initialize(){
        context = Context.getInstance();
        lblSaldoRealCB.setText(formatarNumero(context.getSaldo().getBudgetContaBancaria().getSaldoReal()) + " €");
        lblSaldoRealD.setText(formatarNumero(context.getSaldo().getBudgetDinheiro().getSaldoReal()) + " €");
        lblSaldoTotal.setText(formatarNumero(context.getSaldo().getBudgetDinheiro().getSaldoReal()+context.getSaldo().getBudgetContaBancaria().getSaldoReal()) + " €");
        lblSaldoDistribuir.setText(formatarNumero(context.getSaldo().getSaldoPorDistribuir()) + " €");
        lblSaldoEnvelopes.setText(formatarNumero(context.getSaldo().getSaldoNosEnvelopes()) + " €");
        lblTotalDespesas.setText(formatarNumero(context.getSaldo().getTotalDespesas()) + " €");
    }

    private String formatarNumero(double numero) {
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        return formato.format(numero);
    }

    public void onAdicionarDespesa(){
        sideBarController.adicionarDespesa();
    }

    public void onAdicionarSaldo() {
        sideBarController.adicionarSaldo();
    }


}
