package com.example.gps_g11.Controller.Envelope;

import com.example.gps_g11.Controller.SideBarController;
import javafx.scene.control.*;
public class EnvelopeGuardaDinheiroController {
    public Button btnCancelar;

    public Button btnOk;

    public ChoiceBox<?> cbEnvelope;

    public Label lblError1;

    public Label lblError2;

    public TextArea taDescricao;

    public TextField tfValor;
    private SideBarController sideBarController;

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }
    public void onBackToEnvelope() {sideBarController.onEnvelope();}
    public void initialize(){}
}