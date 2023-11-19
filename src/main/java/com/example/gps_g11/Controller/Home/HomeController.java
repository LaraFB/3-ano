package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;


import java.io.IOException;

public class HomeController {
    public Button ViewBudgetBtn;
    public Text BalanceText;
    public Text TotalExpenseText;
    private Context context;

    public BorderPane root;

    private SideBarController sideBarController;


    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }



    public void initialize(){
        context = Context.getInstance();
        BalanceText.setText(String.valueOf(context.getBudgetRestante()) + " €");
        TotalExpenseText.setText(String.valueOf(context.getBudgetGasto()) + " €");
    }

    public void onAdicionarDespesa(){
        sideBarController.adicionarDespesa();
    }

    public void onAdicionarSaldo(ActionEvent actionEvent) {
        sideBarController.adicionarSaldo();
    }
}
