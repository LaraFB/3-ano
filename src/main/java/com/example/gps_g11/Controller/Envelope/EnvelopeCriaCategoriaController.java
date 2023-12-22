package com.example.gps_g11.Controller.Envelope;

import com.example.gps_g11.Controller.Home.HomePageAdicionarSaldoController;
import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class EnvelopeCriaCategoriaController {

    public Button btnOk;
    public Label lblError;
    public Label lblError2;
    public Label lblError3;
    public TextField nomeCategoria;
    public TextArea taDescricao;
    private SideBarController sideBarController;
    HomePageAdicionarSaldoController homePageAdicionarSaldoController;
    EnvelopeController envelopeController;
    private Context context;

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }
    public void initialize(){
        context = Context.getInstance();
        lblError2.setVisible(false);
        lblError3.setVisible(false);
        lblError.setVisible(false);
    }
    @FXML
    void onBackCategoria() {
       sideBarController.onEnvelope(false);
    }

    @FXML
    void onOk(ActionEvent event) {
        if(nomeCategoria.getText().isEmpty() || taDescricao.getText().isEmpty()){
            lblError.setVisible(true);
            lblError2.setVisible(true);
            lblError3.setVisible(true);
            lblError.setText("Preenche todos os campos");
            lblError.setTextFill(Color.RED);
        }else{
            if(context.adicionarCategoriaEntrada(nomeCategoria.getText(),taDescricao.getText()) == 1){
                lblError.setVisible(true);
                lblError.setText("Categoria adicionada com sucesso");
                lblError.setTextFill(Color.GREEN);
                resetCampos();
            }else{
                lblError.setVisible(true);
                lblError.setText("Esta categoria de entrada já existe");
                lblError.setTextFill(Color.RED);
            }
        }
    }
    private void resetCampos(){
        nomeCategoria.clear();
        taDescricao.clear();
    }

}


