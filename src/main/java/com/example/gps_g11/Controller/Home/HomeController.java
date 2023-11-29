package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class HomeController {
    public Text saldoRealText;
    public Text saldoDisponivelText;
    public Text totalDespesasText;
    private Context context;
    public BorderPane root;
    private SideBarController sideBarController;

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void initialize(){
        context = Context.getInstance();
        saldoRealText.setText(formatarNumero(context.getSaldoReal()));
        saldoDisponivelText.setText(formatarNumero(context.getSaldoDisponivel()));
        totalDespesasText.setText(formatarNumero(context.getTotalDespesas()));
        /*BalanceText.setText(String.valueOf(context.getBudgetRestante()) + " €");
        TotalExpenseText.setText(String.valueOf(context.getBudgetGasto()) + " €");*/
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

    public void onResetBudget(){sideBarController.resetbudget();}
}
