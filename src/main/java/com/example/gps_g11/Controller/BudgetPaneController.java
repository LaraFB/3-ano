package com.example.gps_g11.Controller;

import com.example.gps_g11.Data.Context;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

public class BudgetPaneController {
    public PieChart pieChartBudget;
    public PieChart pieChartBolsa;
    public BorderPane root;
    SideBarController sideBarController;
    private Context context;

    public void initialize(){
        context = Context.getInstance();
        update();

    }



    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }


    public void onAddBudget() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBudgetPopUp.fxml"));
                Pane secondaryPane = loader.load();

                Stage secondaryStage = new Stage();
                secondaryStage.initModality(Modality.WINDOW_MODAL);
                secondaryStage.initOwner(root.getScene().getWindow());
                Scene secondaryScene = new Scene(secondaryPane);

                BudgetPanePopUpController budgetPanePopUpController = loader.getController();
                budgetPanePopUpController.setBudgetPane(this);

                secondaryStage.setScene(secondaryScene);
                secondaryStage.setTitle("Montante");
                secondaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void update() {
        PieChart.Data slice1 = new PieChart.Data("Budget restante", context.getBudgetRestante());
        PieChart.Data slice2 = new PieChart.Data("Budget gasto", context.getBudgetGasto());

        slice1.setName("Budget restante: " + context.getBudgetRestante() + "€");
        slice1.setPieValue(context.getBudgetRestante());

        slice2.setName("Budget gasto: " +context.getBudgetGasto()+"€");
        slice2.setPieValue(context.getBudgetGasto());

        pieChartBudget.setLabelsVisible(false);
        pieChartBudget.getData().setAll(slice1, slice2);

        PieChart.Data slice3 = new PieChart.Data("Bolsa restabte: ", context.getValorBolsa());
        PieChart.Data slice4 = new PieChart.Data("Bolsa gasto: ", context.getValorGastoBolsa());

        slice3.setName("Bolsa restante: " + context.getValorBolsa() + "€");
        slice3.setPieValue(context.getValorBolsa());


        slice4.setName("Bolsa gasto: " + context.getValorGastoBolsa() + "€");
        slice4.setPieValue(context.getValorGastoBolsa());

        pieChartBolsa.setTitle(context.getNomeBolsa());
        pieChartBolsa.setLabelsVisible(false);
        pieChartBolsa.getData().setAll(slice3,slice4);
    }

    public void acaoAoFecharJanelaSecundaria() {
        update();
    }
}
