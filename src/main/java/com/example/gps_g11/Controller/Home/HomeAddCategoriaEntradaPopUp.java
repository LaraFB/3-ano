package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Data.Context;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class HomeAddCategoriaEntradaPopUp {
    public BorderPane root;
    public Label LError;
    public TextArea taDescricao;
    HomePageAdicionarSaldoController homePageAdicionarSaldoController;

    public TextField tfNome;

    private Context context;

    public void initialize(){
        context = Context.getInstance();
        /*TFMontante.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onAddBudget();
            }
        });*/
    }

    public void setHomePageAdicionarSaldoPane(HomePageAdicionarSaldoController homePageAdicionarSaldoController) {
        this.homePageAdicionarSaldoController = homePageAdicionarSaldoController;
    }

    public void onAddBudget() {
        if(tfNome.getText().isEmpty() || taDescricao.getText().isEmpty()){
            LError.setVisible(true);
            LError.setText("Preenche o campo com o valor");
        }else{
            if(context.adicionarCategoriaEntrada(tfNome.getText(),taDescricao.getText()) == 1){
                onClose();
            }else{
                LError.setVisible(true);
                LError.setText("Esta categoria de entrada já existe");
            }
        }
    }

    public void onClose() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();

        homePageAdicionarSaldoController.acaoAoFecharJanelaSecundaria();
    }

}
