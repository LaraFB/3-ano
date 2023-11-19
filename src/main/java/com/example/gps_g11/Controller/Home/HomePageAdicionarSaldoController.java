package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.SideBarController;
import javafx.event.ActionEvent;

public class HomePageAdicionarSaldoController {
    private SideBarController sideBarController;
    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void onBackToHomePage(ActionEvent actionEvent) {
        sideBarController.onHomePage();
    }
}
