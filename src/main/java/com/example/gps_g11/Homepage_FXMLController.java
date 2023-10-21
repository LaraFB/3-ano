package com.example.gps_g11;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Homepage_FXMLController implements Initializable {

    @FXML
    private Button Menu,MenuBack;

    @FXML
    private AnchorPane SideBar,AddExpensesPane;

    //Botoes do menu item
    @FXML
    private Button AddExpenses,Category,Expenses,Goals,SaveMoney,Settings,OkExpensesBtn;

    //Botoes da barra de navegação (cima)
    @FXML
    private Button Account, Categoria_Cafes, Categoria_Compras, Categoria_More, Categoria_Propinas, Categoria_Refeicoes_Cantina, Categoria_Renda;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SideBar.setVisible(false);
        MenuBack.setVisible(false);
        AddExpensesPane.setVisible(false);

        Menu.setOnMouseClicked(mouseEvent -> {
            SideBar.setVisible(true);
            MenuBack.setVisible(true);
            Menu.setVisible(false);
        });
        MenuBack.setOnMouseClicked(mouseEvent -> {
            SideBar.setVisible(false);
            MenuBack.setVisible(false);
            Menu.setVisible(true);
        });

        AddExpenses.setOnMouseClicked(mouseEvent -> {
            AddExpensesPane.setVisible(true);
        });
    }
}
