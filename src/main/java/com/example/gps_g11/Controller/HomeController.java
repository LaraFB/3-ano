package com.example.gps_g11.Controller;

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

    SideBarController sideBarController;

    public void onBudget(ActionEvent actionEvent) throws IOException {
        sideBarController.onBudget(actionEvent);
    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void onExpense(ActionEvent actionEvent) throws IOException {
        sideBarController.onAdd(actionEvent);
    }

    public void initialize(){
        context = Context.getInstance();
        BalanceText.setText(String.valueOf(context.getBudgetRestante()) + " €");
        TotalExpenseText.setText(String.valueOf(context.getBudgetGasto()) + " €");
    }
}
