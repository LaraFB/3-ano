package com.example.gps_g11.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Homepage_FXMLController implements Initializable {

    //Botoes Side Bar
    @FXML
    private Button HomepageBtn, CategoryBtn, ExpensesBtn, BudgetBtn, GoalsBtn, AddBtn, SettingsBtn;
    @FXML
    private VBox SideBar;

    @FXML
    private TabPane tab;
    @FXML
    private Tab AddIncomePane,ExpenseTab;;

    //Pane Expenses
    @FXML
    private AnchorPane AddExpensesPane;
    @FXML
    private Button   OkExpensesBtn,CancelExpensesBtn;
    @FXML
    private Text BalanceText, TotalBudgetText, TotalExpenseText;
    @FXML
    private ChoiceBox<String> CategoriaCheckbox;
    private String[] Categorias ={"Cafe","Compras","Propinas","Renda","Refeiçoes na Cantina","Outra"};
    @FXML
    private DatePicker DatePicker;

    //Pane Income
    @FXML
    private AnchorPane IncomeTab;
    //Homepage
    @FXML
    private AnchorPane HomepagePane;
    @FXML
    private PieChart BudgetChart;
    @FXML
    private Button AddExpenseBtn, AddIncomeBtn, ViewBalanceBtn, ViewExpensesBtn, ViewBudgetBtn;

    //Botoes Category bar
    @FXML
    private Button Categoria_Cafes, Categoria_Compras, Categoria_More, Categoria_Propinas, Categoria_Refeicoes_Cantina,Categoria_Renda;
    @FXML
    private HBox Category_Bar;

    //Category Pane
    @FXML
    private AnchorPane CategoryPane;

    //Budget Pane
    @FXML
    private AnchorPane BudgetPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategoryPane.setVisible(false);
        tab.setVisible(false);
        CategoriaCheckbox.getItems().addAll(Categorias);
        DatePicker.setValue(LocalDate.now());
        BudgetPane.setVisible(false);

        CategoryBtn.setOnMouseClicked(mouseEvent -> {
            CategoryPane.setVisible(true);
            HomepagePane.setVisible(false);
            BudgetPane.setVisible(false);
            tab.setVisible(false);
        });
        HomepageBtn.setOnMouseClicked(mouseEvent -> {
            HomepagePane.setVisible(true);
            CategoryPane.setVisible(false);
            BudgetPane.setVisible(false);
            tab.setVisible(false);
        });
        AddBtn.setOnMouseClicked(mouseEvent -> {
            HomepagePane.setVisible(false);
            CategoryPane.setVisible(false);
            BudgetPane.setVisible(false);
            tab.setVisible(true);
        });
        AddExpenseBtn.setOnMouseClicked(mouseEvent -> {
            HomepagePane.setVisible(false);
            CategoryPane.setVisible(false);
            BudgetPane.setVisible(false);
            tab.setVisible(true);
        });
        BudgetBtn.setOnMouseClicked(mouseEvent -> {
            BudgetPane.setVisible(true);
            HomepagePane.setVisible(false);
            CategoryPane.setVisible(false);
            tab.setVisible(false);
        });
        ViewBudgetBtn.setOnMouseClicked(mouseEvent -> {
            BudgetPane.setVisible(true);
            HomepagePane.setVisible(false);
            CategoryPane.setVisible(false);
            tab.setVisible(false);
        });
    }
}

