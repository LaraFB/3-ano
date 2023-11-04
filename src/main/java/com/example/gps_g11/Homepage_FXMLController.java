package com.example.gps_g11;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Category_Bar.setVisible(false);
        tab.setVisible(false);
        CategoriaCheckbox.getItems().addAll(Categorias);
        DatePicker.setValue(LocalDate.now());

        CategoryBtn.setOnMouseClicked(mouseEvent -> {
            Category_Bar.setVisible(true);
            HomepagePane.setVisible(false);
            tab.setVisible(false);
        });
        HomepageBtn.setOnMouseClicked(mouseEvent -> {
            HomepagePane.setVisible(true);
            Category_Bar.setVisible(false);
            tab.setVisible(false);
        });
        AddBtn.setOnMouseClicked(mouseEvent -> {
            HomepagePane.setVisible(false);
            Category_Bar.setVisible(false);
            tab.setVisible(true);
        });
        AddExpenseBtn.setOnMouseClicked(mouseEvent -> {
            HomepagePane.setVisible(false);
            Category_Bar.setVisible(false);
            tab.setVisible(true);
        });
    }
}
