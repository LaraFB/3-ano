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

    public TextField tfValor;

    public TextArea taDescricao;
    public ChoiceBox cbEnvelope;
    public ToggleButton tbtnEnvelopeFechado;
    public ToggleButton tbtnEnvelopeAberto;
    public Button btnCancelar;
    public Button btnOk;
    public TextField nomeEnvelope;
    private SideBarController sideBarController;
    private ToggleGroup envelopeToggleGroup;
    private Context context;
    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void onBackToEnvelope(){
        sideBarController.onEnvelope();
    }
    public void initialize(){
        context = Context.getInstance();
        tfValorFormat();
        envelopeToggleGroup = new ToggleGroup();

        tbtnEnvelopeFechado.setToggleGroup(envelopeToggleGroup);
        tbtnEnvelopeAberto.setToggleGroup(envelopeToggleGroup);

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
        ToggleButton toggleButtonAtivo = (ToggleButton) envelopeToggleGroup.getSelectedToggle();
        if(tfValor.getText().isEmpty() || nomeEnvelope.getText().isEmpty() || toggleButtonAtivo == null){
            lblError.setVisible(true);
            lblError1.setVisible(true);
            lblError2.setVisible(true);
            lblError.setTextFill(Color.RED);
            lblError.setText("Preencha os espaços obrigatórios");
        }else{
            lblError.setVisible(true);
            lblError1.setVisible(false);
            lblError2.setVisible(false);
            lblError.setText("Saldo adicionao com sucesso");
            lblError.setTextFill(Color.GREEN);
            if(taDescricao.getText().isEmpty()){
                if( context.adicionarCategoriaPorNome(Double.parseDouble(tfValor.getText()),nomeEnvelope.getText(),toggleButtonAtivo == tbtnEnvelopeAberto) == -1){
                    lblError.setText("Saldo insuficiente");
                    lblError.setTextFill(Color.RED);
                }else{
                    resetCampos();
                }
            }else{
                if(context.adicionarCategoriaNomeDescricao(Double.parseDouble(tfValor.getText()),nomeEnvelope.getText(),taDescricao.getText(),toggleButtonAtivo == tbtnEnvelopeAberto) == -1){
                    lblError.setText("Saldo insuficiente");
                    lblError.setTextFill(Color.RED);
                }else{
                    resetCampos();
                }
            }
        }
    }
    private void resetCampos() {
        tfValor.clear();
        nomeEnvelope.clear();
        taDescricao.clear();
        envelopeToggleGroup.selectToggle(null);
    }
}