package com.example.gps_g11.Controller.Objetivo;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Objetivo.Objetivo;
import com.example.gps_g11.Data.Transacao.Despesa;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    //@FXML
    //private Button btnAdcDinheiro;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Slider sPrioridade;
    @FXML
    private DatePicker dpData;
    @FXML
    private Label lPrioridade;
    @FXML
    private Label lProgresso;

    public void setSideBar(SideBarController sideBarController) {this.sideBarController = sideBarController;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.context = Context.getInstance();
        msgError.setVisible(false);
        update();

        sPrioridade.valueProperty().addListener((observable, oldValue, newValue) -> {
            lPrioridade.setText(newValue.intValue()+"/10");
        });
    }

    public void onBackToObjetivos(){ sideBarController.onObjetivos();}
    //public void onBackToHomePage(){ sideBarController.onHomePage();}
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

            context.getListaObjetivos().getObjetivo(index).setDataLimite(dpData.getValue());
            context.getListaObjetivos().getObjetivo(index).setPrioridade((int)sPrioridade.getValue());

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
            //btnAdcDinheiro.setDisable(true);
            btnEliminar.setDisable(true);
            btnSalvar.setDisable(true);
            tfNome.setDisable(true);
            tfValor.setDisable(true);
            taDescricao.setDisable(true);

            return;
        }
        context.getListaObjetivos().sort(context.getData());

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
        sPrioridade.setValue(context.getListaObjetivos().getObjetivo(index).getPrioridade());
        lPrioridade.setText(context.getListaObjetivos().getObjetivo(index).getPrioridade() + "/10");
        dpData.setValue(context.getListaObjetivos().getObjetivo(index).getDataLimite());

        // valor        --> 1
        // valor obtido --> ?
        //
        // ?% = (valor obtido * 1) / valor

        pbObjetivo.setProgress( context.getListaObjetivos().getObjetivo(index).getCurrentValue() / context.getListaObjetivos().getObjetivo(index).getValor());
        lProgresso.setText((context.getListaObjetivos().getObjetivo(index).getCurrentValue() / context.getListaObjetivos().getObjetivo(index).getValor())*100 +"%");
        msgError.setVisible(false);
        //btnAdcDinheiro.setDisable(false);
        btnSalvar.setDisable(false);
        btnEliminar.setDisable(false);
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
            lTitulo.setText("Objetivo");
            tfNome.setText("");
            tfValor.setText("");
            taDescricao.setText("");
            sPrioridade.setValue(0);
            dpData.setValue(null);
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

    public void onAdcDinheiro(){/*
        int EnvelopeObjetivos = -1;
        if(context.getCategoriasListDespesas().isEmpty()){ //se n houverem envelopes mostra um aviso e sai
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Aviso");
            alert.setContentText("Não existem envelopes de onde retirar dinheiro.");
            alert.showAndWait();
            return;
        }
        boolean haAbertos = false;
        for (int x = 0; x < context.getCategoriasListDespesas().size(); x++) {
            if(context.getCategoriasListDespesas().get(x).getNome().toLowerCase().contains("objetivo"))
                EnvelopeObjetivos = x;
            if (context.getCategoriasListDespesas().get(x).isAberto()) {
                System.out.println(context.getCategoriasListDespesas().get(x).getNome() + " esta aberto");
                haAbertos = true;
                break;
            }
        }
        //se saiu e n ha abertos
        if(haAbertos == false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Aviso");
            alert.setContentText("Não existem envelopes de onde retirar dinheiro.");
            alert.showAndWait();
            return;
        }
        else{
            if(EnvelopeObjetivos == -1){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setGraphic(null);
                alert.setTitle("Aviso");
                alert.setContentText("Não existe envelope relativo aos objetivos, pode retirar dinheiro de outros envelopes abertos, caso necessário.");
                alert.showAndWait();
                return;
            }
            if(context.getListaObjetivos().getObjetivo(EnvelopeObjetivos).getValor() < 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setGraphic(null);
                alert.setTitle("Aviso");
                alert.setContentText("O envelope de objetivos não tem dinheiro, pode retirar dinheiro de outros envelopes abertos, caso necessário.");
                alert.showAndWait();
                return;
            }
        }



        //pop up:
        Dialog<Pair<String, Integer>> popUp = new Dialog<>();
        popUp.setHeaderText(null);
        popUp.setGraphic(null);
        popUp.setTitle("Adicionar dinheiro");

        popUp.getDialogPane().getStylesheets().add("@../../Style.css");

        ButtonType btnOkType = new ButtonType("Adicionar", ButtonBar.ButtonData.OK_DONE);
        popUp.getDialogPane().getButtonTypes().addAll(btnOkType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        //escolher envelope
        ChoiceBox<String> envelopes = new ChoiceBox<>();
        for(int i=0; i<context.getCategoriasListDespesas().size(); i++)
            if(context.getCategoriasListDespesas().get(i).isAberto()) {
                envelopes.getItems().add(context.getCategoriasListDespesas().get(i).getNome());
                if(envelopes.getValue() == null) envelopes.setValue(context.getCategoriasListDespesas().get(i).getNome());
            }

        envelopes.setStyle("-fx-background-color:  #9FCDFF");

        //escolher valor
        TextField valor = new TextField();
        valor.setPromptText("");
        valor.setStyle("-fx-background-color:  #DEEFFF");

        grid.add(new Label("Envelope:"), 0, 0);
        grid.add(envelopes, 1, 0);
        Label lValor = new Label("Valor (entre 0 e " + context.getCategoriaByName(envelopes.getValue()).getValor() + "):      ");
        grid.add(lValor, 0, 1);
        grid.add(valor, 1, 1);

        Label lCheck = new Label("Retirou o valor em dinheiro? ");
        CheckBox isDinheiro = new CheckBox();
        grid.add(lCheck,0,2);
        grid.add(isDinheiro,1,2);

        Node btnOk = popUp.getDialogPane().lookupButton(btnOkType);
        btnOk.setDisable(true);
        btnOk.getStyleClass().add("btn");

        valor.textProperty().addListener((observable, oldValue, newValue) -> {
            btnOk.setDisable(!isNumber(newValue));
            btnOk.setDisable(newValue.trim().isEmpty());
            btnOk.setDisable(Double.parseDouble(newValue) <= 0);
            //btnOk.setDisable(Double.parseDouble(newValue) > context.getCategoriaByName(envelopes.getValue()).getValor());

        });

        envelopes.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(context.getListaObjetivos().getObjetivo(index).getMissingValue() < context.getCategoriaByName(newValue).getValor()) //se precisa de menos dinheiro q o envelope tem
                lValor.setText("Valor (entre 0 e " + context.getListaObjetivos().getObjetivo(index).getMissingValue() + "): ");
            else //>=
                //lValor.setText("Valor (entre 0 e " + context.getCategoriaByName(newValue).getValor() + "): ");
            //btnOk.setDisable(envelopes.getValue().trim().isEmpty());
            if(!valor.getText().isEmpty())
                btnOk.setDisable(Double.parseDouble(valor.getText()) > context.getCategoriaByName(newValue).getValor());
        });

        popUp.getDialogPane().setContent(grid);
        Optional<Pair<String, Integer>> check = popUp.showAndWait();

        if(check.toString().contains("OK_DONE")){ //apenas se clicou em adicionar
            //fechou o popup: temos os valores:
            double valoraretirar = Double.parseDouble(valor.getText());
            String envelopearetirar = envelopes.getValue();

            //retira o valor do envelope:
            context.adicionarDespesa(envelopearetirar,"Retirados " + valoraretirar +"€ para o objetivo " + tfNome.getText(),LocalDate.now(),valoraretirar,isDinheiro.isSelected());

            //coloca no objetivo
            context.getListaObjetivos().getObjetivo(index).addToGoal(valoraretirar);

            update();
        }*/
    }


}
