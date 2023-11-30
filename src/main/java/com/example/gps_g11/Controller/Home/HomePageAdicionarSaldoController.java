package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class HomePageAdicionarSaldoController {
    public TextField tfValor;
    public DatePicker dataPicker;
    public TextArea taDescricao;
    public Label lblError3;
    public Label lblError1;
    public Label lblError;
    private SideBarController sideBarController;
    private Context context;
    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void onBackToHomePage(ActionEvent actionEvent) {
        sideBarController.onHomePage();
    }

    public void onAdicionarBolsa(){
        sideBarController.adicionarBolsa();
    }

    public void initialize(){
        context = Context.getInstance();
        tfValorFormat();
        lblError1.setVisible(false);
        lblError3.setVisible(false);
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
        LocalDate selectedDate = dataPicker.getValue();
        if(tfValor.getText().isEmpty() || selectedDate == null){
            lblError.setVisible(true);
            lblError1.setVisible(true);
            lblError3.setVisible(true);
            lblError.setTextFill(Color.RED);
            lblError.setText("Preencha os espaços obrigatórios");
        }else{
            lblError.setVisible(true);
            lblError1.setVisible(false);
            lblError3.setVisible(false);
            lblError.setText("Saldo adicionao com sucesso");
            lblError.setTextFill(Color.GREEN);
            context.adicionarTransacao("Entrada",taDescricao.getText(),selectedDate,Double.parseDouble(tfValor.getText()) );
            context.getBudget().setSaldoReal(context.getBudget().getSaldoReal()+Double.parseDouble(tfValor.getText()));
            context.getBudget().setSaldoDisponivel(context.getBudget().getSaldoDisponivel()+Double.parseDouble(tfValor.getText()));
            resetCampos();
        }

    }

    private void resetCampos() {
        tfValor.clear();
        taDescricao.clear();
        dataPicker.setValue(null);
    }
}
