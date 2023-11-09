package com.example.gps_g11.Controller;

import com.example.gps_g11.Data.Context;
import javafx.scene.control.ChoiceBox;

public class ExpenseTabController {
    public ChoiceBox CategoriaCheckbox;
    SideBarController sideBarController;

    private Context context;
    private String[] categorias ={"Cafe","Compras","Propinas","Renda","Refeiçoes na Cantina","Outra"};

    public void initialize(){
        context = Context.getInstance();
        CategoriaCheckbox.getItems().setAll(categorias);

    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }
}
