package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    public Label lblError;
    public Label lblError1;
    public Label lblError2;
    public Label lblError3;
    public Label lblError4;
    public Label lblError5;
    public ChoiceBox <String>cbTipoPagamento;
    public ChoiceBox <String> cbTipoEntrada;
    public BorderPane root;
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
        lblError.setVisible(false);
        lblError1.setVisible(false);
        lblError2.setVisible(false);
        lblError3.setVisible(false);
        lblError4.setVisible(false);
        lblError5.setVisible(false);
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
        update();
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
        if(tfValor.getText().isEmpty() || selectedDate == null || taDescricao.getText().isEmpty() || cbTipoEntrada.getValue().equals("Escolhe") || cbTipoPagamento.getValue().equals("Escolhe")){
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
            lblError.setText("Saldo adicionado com sucesso");
            lblError.setTextFill(Color.GREEN);
            context.adicionarEntrada(cbTipoEntrada.getValue(),taDescricao.getText(),selectedDate, Double.parseDouble(tfValor.getText()),cbTipoPagamento.getValue().equals("Numerário"));
            resetCampos();
        }

    }

    private void resetCampos() {
        tfValor.clear();
        taDescricao.clear();
        dataPicker.setValue(null);
    }

    public void onActionAddCategoriaEntrada() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeAddCategoriaEntradaPopUp.fxml"));
            Pane secondaryPane = loader.load();

            Stage secondaryStage = new Stage();
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initOwner(root.getScene().getWindow());
            Scene secondaryScene = new Scene(secondaryPane);

            HomeAddCategoriaEntradaPopUp homeAddCategoriaEntradaPopUp = loader.getController();
            homeAddCategoriaEntradaPopUp.setHomePageAdicionarSaldoPane(this);

            secondaryStage.setScene(secondaryScene);
            secondaryStage.setTitle("Montante");
            secondaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void acaoAoFecharJanelaSecundaria() {
        update();
    }

    private void update() {
        cbTipoEntrada.getItems().clear();
        if(context.getCategoriasListEntradas().isEmpty()){
            cbTipoEntrada.setDisable(true);
        }else{
            cbTipoEntrada.setDisable(false);
            for (String categoriaEntradasNome : context.getCategoriaEntradasNomes()) {
                cbTipoEntrada.getItems().add(categoriaEntradasNome);
            }
        }
        cbTipoEntrada.setValue("Escolhe");
    }


}
