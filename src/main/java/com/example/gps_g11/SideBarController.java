package com.example.gps_g11;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SideBarController {

    public Button HomepageBtn;
    public Button CategoryBtn;
    public Button ExpensesBtn;
    public Button BudgetBtn;
    public Button GoalsBtn;
    public Button AddBtn;
    public Button SettingsBtn;
    public Pane ContentPane;

    public void onHomePage(ActionEvent actionEvent) {
        loadFXML("Home.fxml");
    }

    public void onCategory(ActionEvent actionEvent) {
        loadFXML("Category.fxml");
    }

    public void onBudget(ActionEvent actionEvent) {
        loadFXML("BudgetPane.fxml");
    }

    public void onAdd(ActionEvent actionEvent) {
        loadFXML("ExpenseTab.fxml");
    }


    public void onGoals(ActionEvent actionEvent) {
    }

    public void onSettings(ActionEvent actionEvent) {
    }

    private void loadFXML(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Node node = loader.load();
            if (loader != null) {
                switch (fxmlFileName){
                    case "Home.fxml":
                        HomeController homeController = loader.getController();
                        homeController.setSideBar(this);
                        break;
                    case "Category.fxml":
                        CategoryController categoryController = loader.getController();
                        categoryController.setSideBar(this);
                        break;
                    case "BudgetPane.fxml":
                        BudgetPaneController budgetPaneController = loader.getController();
                        budgetPaneController.setSideBar(this);
                        break;
                    case "ExpenseTab.fxml":
                        ExpenseTabController expenseTabController = loader.getController();
                        expenseTabController.setSideBar(this);
                        break;
                }
            }
            ContentPane.getChildren().setAll(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void initialize(){
        loadFXML("Home.fxml");
    }


}
