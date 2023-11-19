package com.example.gps_g11.Controller.Envelope;

import com.example.gps_g11.Controller.SideBarController;
import javafx.scene.control.*;

public class EnvelopeCriaEnvelopeController {
    public Label lblError1;
    public TextField tfValor;
    public TextArea taDescricao;
    public Label lblError2;
    public ChoiceBox cbEnvelope;
    public ToggleButton tbtnEnvelopeFechado;
    public ToggleButton tbtnEnvelopeAberto;
    public Button btnCancelar;
    public Button btnOk;
    private SideBarController sideBarController;

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void onBackToEnvelope(){
        sideBarController.onEnvelope();
    }
}
