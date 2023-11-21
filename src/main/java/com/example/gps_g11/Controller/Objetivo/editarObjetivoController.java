package com.example.gps_g11.Controller.Objetivo;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class editarObjetivoController implements Initializable{
    private SideBarController sideBarController;
    private Context context;
    private int index = 0;

    @FXML
    private Button btnPrev, btnNext;
    @FXML
    private Label lTitulo;
    @FXML
    private TextField tfNome, tfValor;
    @FXML
    private TextArea taDescricao;
    @FXML
    private ProgressBar pbObjetivo;
    @FXML
    private Label msgError;

    public void setSideBar(SideBarController sideBarController) {this.sideBarController = sideBarController;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.context = Context.getInstance();
        msgError.setVisible(false);
        update();
    }

    public void onBackToObjetivos(){ sideBarController.onObjetivos();}
    public void onBackToHomePage(){ sideBarController.onHomePage();}
    public void onGuardar(){
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

        if(Double.parseDouble(tfValor.getText()) <= 0 ){
            msgError.setVisible(true);
            msgError.setText("Insira um valor positivo!");
            return;
        }

        try{
            context.getListaObjetivos().getObjetivo(index).setNome(tfNome.getText());
            context.getListaObjetivos().getObjetivo(index).setValor(Double.parseDouble(tfValor.getText()));

            if(!taDescricao.getText().isEmpty())
                context.getListaObjetivos().getObjetivo(index).setDescricao(taDescricao.getText());
            else context.getListaObjetivos().getObjetivo(index).setDescricao("");

            update();

        }catch (Exception e){
            msgError.setVisible(true);
            msgError.setText("Falha ao alterar objetivo...");
        }
    }

    public void onNext(){
        index++;
        update();
    }
    public void onPrevious(){
        index--;
        update();
    }

    public void update(){
        if(context.getListaObjetivos().isEmpty()){
            btnPrev.setVisible(false);
            btnNext.setVisible(false);

            return;
        }

        try{
            context.getListaObjetivos().getObjetivo(index+1);
            btnNext.setDisable(false);
        }
        catch (Exception e) {//se n ha prox objt
            btnNext.setDisable(true);
        }

        try{
            context.getListaObjetivos().getObjetivo(index-1);
            btnPrev.setDisable(false);
        }
        catch (Exception e) {//se n ha prox objt
            btnPrev.setDisable(true);
        }

        try{
            context.getListaObjetivos().getObjetivo(index); //just in case? reset
        }
        catch (Exception e) {//se n ha prox objt
            index=0;
        }



        lTitulo.setText("Objetivo " + context.getListaObjetivos().getObjetivo(index).getNome());
        tfNome.setText(context.getListaObjetivos().getObjetivo(index).getNome());
        tfValor.setText(String.valueOf(context.getListaObjetivos().getObjetivo(index).getValor()));
        taDescricao.setText(context.getListaObjetivos().getObjetivo(index).getDescricao());

        // valor        --> 1
        // valor obtido --> ?
        //
        // ?% = (valor obtido * 1) / valor

        pbObjetivo.setProgress( context.getListaObjetivos().getObjetivo(index).getCurrentValue() / context.getListaObjetivos().getObjetivo(index).getValor());
        msgError.setVisible(false);
    }

    private boolean isNumber(String text){
        try{
            Double.parseDouble(text);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }


    public void onDelete(){
        if(context.getListaObjetivos().getSize() == 1){ //so ha este
            try{
                context.getListaObjetivos().deleteObjetivo(index);

            }catch (Exception e){
                msgError.setVisible(true);
                msgError.setText("Falha ao eliminar objetivo...");
            }

            update();
            tfNome.setText("");
            tfValor.setText("");
            taDescricao.setText("");
            pbObjetivo.setProgress(0);
        }
        else {
            try{
                context.getListaObjetivos().deleteObjetivo(index);

            }catch (Exception e){
                msgError.setVisible(true);
                msgError.setText("Falha ao eliminar objetivo...");
            }
            update();
        }
    }


}
