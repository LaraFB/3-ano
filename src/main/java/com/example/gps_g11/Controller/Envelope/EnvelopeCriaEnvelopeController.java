package com.example.gps_g11.Controller.Envelope;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.time.LocalDate;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class EnvelopeCriaEnvelopeController {
    public Label lblError;
    public Label lblError1;
    public Label lblError2;
    public Label lblError3;
    public Label lblError4;
    public Label lValorR;

    public TextField tfValor;
    public TextField tfValorR;

    public TextArea taDescricao;
    public ToggleButton tbtnEnvelopeFechado;
    public ToggleButton tbtnEnvelopeAberto;
    public Button btnOk;
    public TextField nomeEnvelope;
    private SideBarController sideBarController;
    private ToggleGroup envelopeToggleGroup;
    private Context context;
    public CheckBox checkRecorrente;
    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void onBackToEnvelope(){
        sideBarController.onEnvelope();
    }
    public void initialize(){
        lValorR.setVisible(false);
        lblError4.setVisible(false);
        tfValorR.setVisible(false);
        context = Context.getInstance();
        tfValorFormat();
        if(tfValorR !=null)
            tfValorRFormat();
        envelopeToggleGroup = new ToggleGroup();

        tbtnEnvelopeFechado.setToggleGroup(envelopeToggleGroup);
        tbtnEnvelopeAberto.setToggleGroup(envelopeToggleGroup);

        lblError1.setVisible(false);
        lblError2.setVisible(false);
        lblError3.setVisible(false);
        lblError.setVisible(false);

        checkRecorrente.setOnAction(event -> {
            if(checkRecorrente.isSelected()){
                lValorR.setVisible(true);
                lblError4.setVisible(true);
                tfValorR.setVisible(true);
            } else {
                tfValorR.clear();
                lValorR.setVisible(false);
                lblError4.setVisible(false);
                tfValorR.setVisible(false);
            }
        });

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
    private void tfValorRFormat(){
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
        tfValorR.setTextFormatter(textFormatter);

    }

    public void onOk(){
        ToggleButton toggleButtonAtivo = (ToggleButton) envelopeToggleGroup.getSelectedToggle();


        if(tfValor.getText().isEmpty() || tfValorR.getText().isEmpty() || nomeEnvelope.getText().isEmpty() || toggleButtonAtivo == null || taDescricao.getText().isEmpty()){
            lblError.setVisible(true);
            lblError1.setVisible(true);
            lblError2.setVisible(true);
            lblError3.setVisible(true);
            lblError.setTextFill(Color.RED);
            lblError.setText("Preencha os espaços obrigatórios");
        }else{
            lblError.setVisible(true);
            lblError1.setVisible(false);
            lblError2.setVisible(false);
            lblError3.setVisible(false);
            lblError.setText("Envelope adicionado com sucesso");
            lblError.setTextFill(Color.GREEN);

            int res;
            if(checkRecorrente.isSelected() == true)
                res = context.adicionarCategoriaDespesa(Double.parseDouble(tfValor.getText()),nomeEnvelope.getText(),taDescricao.getText(),toggleButtonAtivo == tbtnEnvelopeAberto,checkRecorrente.isSelected(),Double.parseDouble(tfValorR.getText()));
            else
                res = context.adicionarCategoriaDespesa(Double.parseDouble(tfValor.getText()),nomeEnvelope.getText(),taDescricao.getText(),toggleButtonAtivo == tbtnEnvelopeAberto,checkRecorrente.isSelected());

            if(res == -1){
                lblError.setText("Saldo insuficiente");
                lblError.setTextFill(Color.RED);
            }else if(res == -2){
                lblError.setText("Envelope já existe");
                lblError.setTextFill(Color.RED);
            }else{
                    resetCampos();
            }
        }
    }
    private void resetCampos() {
        tfValor.clear();
        if(tfValorR != null)
            tfValorR.clear();
        nomeEnvelope.clear();
        taDescricao.clear();
        envelopeToggleGroup.selectToggle(null);
    }
}