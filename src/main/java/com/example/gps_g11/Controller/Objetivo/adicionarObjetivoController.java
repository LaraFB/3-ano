package com.example.gps_g11.Controller.Objetivo;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class adicionarObjetivoController implements Initializable {
    private SideBarController sideBarController;
    private Context context;

    @FXML
    private TextField tfNome, tfValor;
    @FXML
    private TextArea taDescricao;
    @FXML
    private Label msgError;
    @FXML
    private Slider sPrioridade;
    @FXML
    private DatePicker dpData;
    @FXML
    private Label lPrioridade;

    public void setSideBar(SideBarController sideBarController) {this.sideBarController = sideBarController;}

    private boolean isNumber(String text){
        try{
            Double.parseDouble(text);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public void onAdicionar(){
        if(tfNome.getText().isEmpty() ){
            msgError.setVisible(true);
            msgError.setText("Insira um nome!");
            return;
        }
        if(tfValor.getText().isEmpty() || !isNumber(tfValor.getText()) ){
            msgError.setVisible(true);
            msgError.setText("Insira um valor numérico!");
            return;
        }

        if(context.getListaObjetivos().getObjetivo(tfNome.getText()) != null){
            msgError.setVisible(true);
            msgError.setText("Objetivo já existe!");
            return;
        }

        if(Double.parseDouble(tfValor.getText()) <= 0 ){
            msgError.setVisible(true);
            msgError.setText("Insira um valor positivo!");
            return;
        }

        try{
            if(!taDescricao.getText().isEmpty()) //tem descricao
            {
                if(dpData.getValue() == null)
                    context.getListaObjetivos().addObjetivo(tfNome.getText(),taDescricao.getText(),Double.parseDouble(tfValor.getText()),(int)sPrioridade.getValue());
                else
                    context.getListaObjetivos().addObjetivo(tfNome.getText(),taDescricao.getText(),Double.parseDouble(tfValor.getText()),(int)sPrioridade.getValue(),dpData.getValue());

            }
            else {
                if(dpData.getValue() == null)
                    context.getListaObjetivos().addObjetivo(tfNome.getText(), Double.parseDouble(tfValor.getText()),(int)sPrioridade.getValue());
                else
                    context.getListaObjetivos().addObjetivo(tfNome.getText(), Double.parseDouble(tfValor.getText()),(int) sPrioridade.getValue(),dpData.getValue());
            }
            msgError.setVisible(true);
            msgError.setTextFill(Color.GREEN);
            msgError.setText("Objetivo adicionado com sucesso!");

            boolean temEnvelopeObjetivos = false;
            if(!context.getCategoriasListDespesas().isEmpty())
                for(int i=0; i< context.getCategoriasListDespesas().size(); i++){
                    if(context.getCategoriasListDespesas().get(i).getNome().toLowerCase().contains("objetivo")){
                        temEnvelopeObjetivos = true;
                        break;
                    }
                }


            if(context.getCategoriasListDespesas().isEmpty() || temEnvelopeObjetivos == false)
                context.adicionarCategoriaDespesa(0.0,"Objetivos","Para concetrização de objetivos", false);

        }catch (Exception e){
            msgError.setVisible(true);
            msgError.setText("Falha ao inserir objetivo...");
        }
    }

    public void onBackToObjetivos(){ sideBarController.onObjetivos();}
    //public void onBackToHomePage(){ sideBarController.onHomePage();}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = Context.getInstance();
        msgError.setVisible(false);

        sPrioridade.valueProperty().addListener((observable, oldValue, newValue) -> {
            lPrioridade.setText(newValue.intValue()+"/10");
        });
    }
}
