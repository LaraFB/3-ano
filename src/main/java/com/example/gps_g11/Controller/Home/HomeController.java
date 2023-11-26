package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;


import java.io.IOException;

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
        saldoRealText.setText(String.valueOf(context.getSaldoReal()));
        saldoDisponivelText.setText(String.valueOf(context.getSaldoDisponivel()));
        /*BalanceText.setText(String.valueOf(context.getBudgetRestante()) + " €");
        TotalExpenseText.setText(String.valueOf(context.getBudgetGasto()) + " €");*/
    }

    public void onAdicionarDespesa(){
        sideBarController.adicionarDespesa();
    }

    public void onAdicionarSaldo(ActionEvent actionEvent) {
        sideBarController.adicionarSaldo();
    }
}
