package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.SideBarController;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class HomePageAdicionarDividaController {
    public Label lblError1;
    public TextField tfValor;
    public TextArea taDescricao;
    public Label lblError2;
    public ChoiceBox cbEnvelope;
    public Label lblError3;
    public DatePicker dataPicker;
    public Button btnCancelar;
    public Button btnOk;
    private SideBarController sideBarController;

    public void onBackToHomePage(ActionEvent actionEvent) {
        sideBarController.onHomePage();
    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }
}
