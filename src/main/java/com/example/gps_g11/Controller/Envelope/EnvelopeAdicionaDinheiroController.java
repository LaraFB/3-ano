package com.example.gps_g11.Controller.Envelope;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class EnvelopeAdicionaDinheiroController {
    public Label lblError1;
    public Label lblError2;
    public ChoiceBox<String> cbEnvelope;

    public TextField tfValor;
    public Label lblError;
    private SideBarController sideBarController;
    private Context context;
    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }
    public void onBackToEnvelope() {sideBarController.onEnvelope();}
    public void initialize(){
        context = Context.getInstance();
        ObservableList<String> nomesEnvelopes = FXCollections.observableArrayList(context.getCategoriaDespesasNomes());
        cbEnvelope.setItems(nomesEnvelopes);
        tfValorFormat();
        lblError1.setVisible(false);
        lblError2.setVisible(false);
        lblError.setVisible(false);
    }
    private void tfValorFormat(){
        Pattern pattern = Pattern.compile("^\\d*\\.?\\d{0,2}$");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (pattern.matcher(newText).matches()) {
                return change;
            }
            return null;
        };
        StringConverter<String> converter = new StringConverter<>() {
            @Override
            public String fromString(String string) {
                return string;
            }

            @Override
            public String toString(String object) {
                return object;
            }
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(converter, "", filter);
        tfValor.setTextFormatter(textFormatter);
    }
    public void onOk(){
        if(tfValor.getText().isEmpty() || cbEnvelope.getValue() == null){
            lblError.setVisible(true);
            lblError1.setVisible(true);
            lblError2.setVisible(true);
            lblError.setTextFill(Color.RED);
            lblError.setText("Preencha os espaços obrigatórios");
        }else{
            lblError.setVisible(true);
            lblError1.setVisible(false);
            lblError2.setVisible(false);
            int res = context.adicionarDinheiroCategoriaDespesa(Double.parseDouble(tfValor.getText()),cbEnvelope.getValue());
            if(res == -1){
                lblError.setText("Saldo insuficiente");
                lblError.setTextFill(Color.RED);
            }else if(res == -2){
                lblError.setText("Não existe este envelope");
                lblError.setTextFill(Color.RED);
            }else{
                lblError.setText("Adicionou dinheiro com sucesso");
                lblError.setTextFill(Color.GREEN);
                resetCampos();
            }
        }
    }
    private void resetCampos() {
        tfValor.clear();
        cbEnvelope.getSelectionModel().clearSelection();
    }


}