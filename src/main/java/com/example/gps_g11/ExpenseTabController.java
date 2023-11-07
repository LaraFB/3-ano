package com.example.gps_g11;

import javafx.scene.control.ChoiceBox;

public class ExpenseTabController {
    public ChoiceBox CategoriaCheckbox;
    SideBarController sideBarController;
    private String[] categorias ={"Cafe","Compras","Propinas","Renda","Refeiçoes na Cantina","Outra"};

    public void initialize(){
        CategoriaCheckbox.getItems().setAll(categorias);

    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }
}
