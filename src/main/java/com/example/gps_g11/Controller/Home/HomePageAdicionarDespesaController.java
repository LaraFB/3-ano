package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class HomePageAdicionarDespesaController {
    public TextField tfValor;
    public TextArea taDescricao;
    public ChoiceBox<String> cbEnvelope;
    public DatePicker dataPicker;
    public Label lblError;
    public Label lblError1;
    public Label lblError2;
    public Label lblError3;
    public Label lblError4;
    public Label lblError5;
    public ChoiceBox <String>cbTipoPagamento;
    private SideBarController sideBarController;
    private Context context;

    public void onBackToHomePage(ActionEvent actionEvent) {
        sideBarController.onHomePage();
    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void initialize(){
        context = Context.getInstance();
        ObservableList<String> nomesEnvelopes = FXCollections.observableArrayList(context.getCategoriaDespesasNomes());
        cbEnvelope.setItems(nomesEnvelopes);
        cbEnvelope.setValue("Escolhe");
        tfValorFormat();
        lblError1.setVisible(false);
        lblError2.setVisible(false);
        lblError3.setVisible(false);
        lblError4.setVisible(false);
        lblError.setVisible(false);
        cbTipoPagamento.getItems().addAll( "Débito", "Numerário");
        cbTipoPagamento.setValue("Escolhe");

        dataPicker.setValue(context.getData());
        dataPicker.setDayCellFactory(datePicker -> new DateCell(){
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                    if(localDate.isBefore(context.getData().minusDays(4)) || localDate.isAfter(context.getData())){
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
            }
        });

    }

    private void tfValorFormat() {
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
        LocalDate selectedDate = dataPicker.getValue();
        if(tfValor.getText().isEmpty() || cbEnvelope.getValue() == null || cbEnvelope.getValue().equals("Escolhe") || selectedDate == null || taDescricao.getText().isEmpty() || cbTipoPagamento.getValue().equals("Escolhe")){
            lblError.setVisible(true);
            lblError1.setVisible(true);
            lblError2.setVisible(true);
            lblError3.setVisible(true);
            lblError4.setVisible(true);
            lblError5.setVisible(true);
            lblError.setTextFill(Color.RED);
            lblError.setText("Preencha os espaços obrigatórios");
        }else{
            lblError.setVisible(true);
            lblError1.setVisible(false);
            lblError2.setVisible(false);
            lblError3.setVisible(false);
            lblError4.setVisible(false);
            lblError5.setVisible(false);
            //Adicionar um transação do tipo despesa
            int res = context.adicionarDespesa(cbEnvelope.getValue(),taDescricao.getText(),selectedDate, Double.parseDouble(tfValor.getText()),cbTipoPagamento.getValue().equals("Numerário"));
            if(res == -2){
                lblError.setText("Não existe este envelope");
                lblError.setTextFill(Color.RED);
            }else if(res == -1){
                lblError.setText("Não existe tanto dinheiro no envelope");
                lblError.setTextFill(Color.RED);
            }else if(res == -3){
                lblError.setText("Valor inserido inválido");
                lblError.setTextFill(Color.RED);
            }else if(res == -4){
                lblError.setText("Não existe tanto saldo na Dinheiro");
                lblError.setTextFill(Color.RED);
            }else if(res == -5){
                lblError.setText("Não existe tanto saldo em Conta Bancária");
                lblError.setTextFill(Color.RED);
            }else{
                lblError.setText("Despesa efetuada com sucesso");
                lblError.setTextFill(Color.GREEN);
                resetCampos();
            }
        }
    }
    private void resetCampos() {

        tfValor.clear();
        taDescricao.clear();
        dataPicker.setValue(LocalDate.from(LocalDateTime.now()));
        cbEnvelope.getSelectionModel().clearSelection();
    }
}
