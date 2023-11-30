package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.Month;

public class HomeResetController {
    public Button btnOk;
    public CheckBox ano;
    public CheckBox dia;
    public CheckBox mes;
    public Label lblError;
    public Label lblError3;
    public TextField diames;
    public TextField freq;
    public SideBarController sideBarController;
    private Context context;
    private String cald;
    public void initialize(){context = Context.getInstance();}
    public void onBackToHomePage() { sideBarController.onHomePage();}
    public void setSideBar(SideBarController sideBarController) {this.sideBarController = sideBarController;}
    public void onOk() throws InterruptedException {
        String auxerror = "";
        int checkGroup = verificaCheckBox();
        LocalDate hoje = LocalDate.now();
        int Dia=0;

        if(!freq.getText().isEmpty()) {
            if (checkGroup == 1) {
                lblError.setVisible(true);
                lblError3.setVisible(false);
                if (!diames.getText().isEmpty()) {
                    if (!VerificaValido())
                        return;
                   Dia = Integer.parseInt(diames.getText());
                }

                int vezes = Integer.parseInt(freq.getText());

                if(vezes <= 0) {
                    lblError.setText("Número de vezes inválido");
                    lblError.setTextFill(Color.RED);
                    return;
                }

                lblError.setText("Realizado com sucesso!");
                lblError.setTextFill(Color.GREEN);
                context.AdicionaDataReset(Dia,vezes,cald,hoje);

                resetCampos();
                //onBackToHomePage();
                return;
            }
        }
        if (checkGroup > 1)
            auxerror = ", várias checkboxes selecionadas";
        if (checkGroup == 0)
            auxerror = ", nenhuma checkbox selecionada";
        msgErro(auxerror);
    }
    private boolean VerificaValido(){
        int Dia =Integer.parseInt(diames.getText());

        if(Dia > 31 || Dia < 1 ) {
            lblError.setText("Dia impossível");
            lblError.setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    private int verificaCheckBox() {
        int count = 0;
        if (dia.isSelected()){
            cald = "dia";
            count++;
        }
        if (mes.isSelected()) {
            cald = "mes";
            count++;
        }
        if (ano.isSelected()){
            cald = "ano";
            count++;
        }
        return count;
    }

    private void msgErro(String auxerror) {
        lblError.setVisible(true);
        lblError3.setVisible(true);
        lblError.setTextFill(Color.RED);
        lblError.setText("Preencha os espaços obrigatórios" + auxerror);
    }

    private void resetCampos() {
       diames.clear();
        freq.clear();
        mes.setSelected(false);
        dia.setSelected(false);
        ano.setSelected(false);
    }
}