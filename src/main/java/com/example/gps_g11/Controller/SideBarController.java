package com.example.gps_g11.Controller;

import com.example.gps_g11.Controller.Budget.BudgetPaneController;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class SideBarController {
    private Context context;
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
        loadFXML("Budget/BudgetPane.fxml");
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
                        putBtnActive(HomepageBtn);
                        break;
                    case "Category.fxml":
                        CategoryController categoryController = loader.getController();
                        categoryController.setSideBar(this);
                        putBtnActive(CategoryBtn);
                        break;
                    case "BudgetPane.fxml":
                        BudgetPaneController budgetPaneController = loader.getController();
                        budgetPaneController.setSideBar(this);
                        putBtnActive(BudgetBtn);
                        break;
                    case "ExpenseTab.fxml":
                        ExpenseTabController expenseTabController = loader.getController();
                        expenseTabController.setSideBar(this);
                        putBtnActive(AddBtn);
                        break;
                }
            }
            ContentPane.getChildren().setAll(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putBtnActive(Button btn) {
        HomepageBtn.getStyleClass().remove("slideItem1");
        CategoryBtn.getStyleClass().remove("slideItem1");
        ExpensesBtn.getStyleClass().remove("slideItem1");
        BudgetBtn.getStyleClass().remove("slideItem1");
        GoalsBtn.getStyleClass().remove("slideItem1");
        AddBtn.getStyleClass().remove("slideItem1");
        SettingsBtn.getStyleClass().remove("slideItem1");
        if (btn.equals(HomepageBtn)) {
            HomepageBtn.getStyleClass().add("slideItem1");
        }else if (btn.equals(CategoryBtn)) {
            CategoryBtn.getStyleClass().add("slideItem1");
        }else if (btn.equals(ExpensesBtn)) {
            ExpensesBtn.getStyleClass().add("slideItem1");
        }else if (btn.equals(BudgetBtn)) {
            BudgetBtn.getStyleClass().add("slideItem1");
        }else if (btn.equals(GoalsBtn)) {
            GoalsBtn.getStyleClass().add("slideItem1");
        }else if (btn.equals(AddBtn)) {
            AddBtn.getStyleClass().add("slideItem1");
        }else if (btn.equals(SettingsBtn)) {
            SettingsBtn.getStyleClass().add("slideItem1");
        }
    }

    public void initialize(){
        context = Context.getInstance();
        loadFXML("Home.fxml");
    }


}


