package com.example.gps_g11.Controller.Envelope;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Categoria.Categoria;
import com.example.gps_g11.Data.Context;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class EnvelopeVisualizarController{
    private SideBarController sideBarController;
    private Categoria categoria;
    private Context context;

    @FXML
    private Label lTitulo;
    @FXML
    private TextField tfValor;
    @FXML
    private TextField tfNome;
    @FXML
    private TextArea taDescricao;
    @FXML
    private ToggleButton tbtnEnvelopeFechado;
    @FXML
    private ToggleButton tbtnEnvelopeAberto;
    @FXML
    private Button btnGuardar;
    @FXML
    private Label lblError;
    @FXML
    private Button btnAdcDinheiro;


    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
        if(categoria == null){
            lblError.setText("Erro ao carregar categoria");
            lblError.setVisible(true);
            return;
        }
        reset();
        context = Context.getInstance();
    }


    public void onEditar(){
        tfValor.setDisable(false);
        tfNome.setDisable(false);
        taDescricao.setDisable(false);
        tbtnEnvelopeAberto.setDisable(false);
        tbtnEnvelopeFechado.setDisable(false);
        btnGuardar.setVisible(true);

        //checks antes de guardar:
        tfValor.textProperty().addListener((observable, oldValue, newValue) -> { //listener: p ver alterações ao texto
            lblError.setText("O valor não pode estar vazio e tem de ser um número!");
            btnGuardar.setDisable(newValue.trim().isEmpty()); //nao consegue guardar se texto estiver vazio
            lblError.setVisible(newValue.trim().isEmpty()); //aparece erro tb

            btnGuardar.setDisable(!isNumber(newValue)); //ou se n for numero
            lblError.setVisible(!isNumber(newValue));

            for(Categoria c : context.getCategoriasList())
                if(c.getNome().compareTo(newValue) == 0){ //caso ja exista um envelope c esse nome
                    btnGuardar.setDisable(true);
                    lblError.setVisible(true);
                    break;
                }
        });
        tfNome.textProperty().addListener((observable, oldValue, newValue) -> {
            lblError.setText("O nome não pode estar vazio!");
            btnGuardar.setDisable(newValue.trim().isEmpty());
            lblError.setVisible(newValue.trim().isEmpty());
        });

        tbtnEnvelopeAberto.selectedProperty().addListener((observable, oldValue, newValue) -> {
            tbtnEnvelopeFechado.setSelected(!newValue);
        });
        tbtnEnvelopeFechado.selectedProperty().addListener((observable, oldValue, newValue) -> {
            tbtnEnvelopeAberto.setSelected(!newValue);
        });
    }

    private boolean isNumber(String text){
        try{
            Double.parseDouble(text);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public void onGuardar(){ //so aparece se params estiverem corretos! se nao o botao fica desativado
        categoria.setNome(tfNome.getText());
        categoria.setDescricao(taDescricao.getText());
        categoria.setValor(Double.parseDouble(tfValor.getText()));

        if(tbtnEnvelopeAberto.isSelected())categoria.setAberto(true);
        else categoria.setAberto(false);

        reset();
    }

    private void reset(){
        lTitulo.setText("Envelope" + categoria.getNome());
        tfValor.setText(String.valueOf(categoria.getValor()));
        tfNome.setText(categoria.getNome());
        taDescricao.setText(categoria.getDescricao());


        tfValor.setDisable(true);
        tfNome.setDisable(true);
        taDescricao.setDisable(true);

        if(categoria.isAberto()) tbtnEnvelopeAberto.setSelected(true);
        else tbtnEnvelopeFechado.setSelected(true);

        tbtnEnvelopeAberto.setDisable(true);
        tbtnEnvelopeFechado.setDisable(true);
        btnGuardar.setVisible(false);

        lblError.setVisible(false);

        //if(!categoria.isAberto())
        //    btnAdcDinheiro.setVisible(false);
        //else btnAdcDinheiro.setVisible(true);
    }

    public void onEliminar(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setGraphic(null);
        alert.setHeaderText(null);
        alert.setTitle("Confirmar eliminação");
        alert.setContentText("Tem a certeza que deseja eliminar o envelope " + categoria.getNome() +"?");

        Optional<ButtonType> result = alert.showAndWait();
        if (!(result.get() == ButtonType.OK)){
            return;
        }

        try {
            context.adicionarTransacao("Envelope eliminado","Saldo reposto do envelope " + categoria.getNome(), LocalDate.now(),categoria.getValor());
            context.getCategoriasList().remove(categoria);
        }catch (Exception e){
            lblError.setText("Erro ao eliminar envelope...");
            lblError.setVisible(true);
            return;
        }
        lTitulo.setText("Envelope Eliminado");
        tfValor.setText("");
        tfNome.setText("");
        taDescricao.setText("");


        tfValor.setDisable(true);
        tfNome.setDisable(true);
        taDescricao.setDisable(true);

        tbtnEnvelopeAberto.setDisable(true);
        tbtnEnvelopeFechado.setDisable(true);
        btnGuardar.setVisible(false);

        lblError.setVisible(false);
        sideBarController.onEnvelope();
    }

    public void onBackToEnvelope(){
        sideBarController.onEnvelope();
    }


    public void onAdcDinheiro(){
        if(context.getCategoriasList().size() == 1){ //se so ha esta categoria
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Aviso");
            alert.setContentText("Não existem envelopes de onde retirar dinheiro.");
            alert.showAndWait();
            return;
        }
        boolean haAbertos = false;
        for (int x = 0; x < context.getCategoriasList().size(); x++)
            if (context.getCategoriasList().get(x).isAberto() && context.getCategoriasList().get(x).getNome().compareTo(categoria.getNome()) != 0 && context.getCategoriasList().get(x).isPagarBolsa() == categoria.isPagarBolsa()) {
                haAbertos = true;
                break;
            }

        //se saiu e n ha+ envelopes abertos
        if(haAbertos == false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Aviso");
            alert.setContentText("Não existem envelopes de onde retirar dinheiro.");
            alert.showAndWait();
            return;
        }


        //pop up:
        Dialog<Pair<String, Integer>> popUp = new Dialog<>();
        popUp.setHeaderText(null);
        popUp.setGraphic(null);
        popUp.setTitle("Adicionar dinheiro de outro envelope");

        popUp.getDialogPane().getStylesheets().add("@../../Style.css");

        ButtonType btnOkType = new ButtonType("Adicionar", ButtonBar.ButtonData.OK_DONE);
        popUp.getDialogPane().getButtonTypes().addAll(btnOkType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        //escolher envelope
        ChoiceBox<String> envelopes = new ChoiceBox<>();
        for(int i=0; i<context.getCategoriasList().size(); i++)
            if(context.getCategoriasList().get(i).isAberto() && context.getCategoriasList().get(i).getNome().compareTo(categoria.getNome()) != 0 && context.getCategoriasList().get(i).isPagarBolsa() == categoria.isPagarBolsa()) {
                envelopes.getItems().add(context.getCategoriasList().get(i).getNome());
                if(envelopes.getValue() == null) envelopes.setValue(context.getCategoriasList().get(i).getNome());
            }

        envelopes.setStyle("-fx-background-color:  #9FCDFF");

        //escolher valor
        TextField valor = new TextField();
        tfValorFormat(valor);
        valor.setPromptText("");
        valor.setStyle("-fx-background-color:  #DEEFFF");

        grid.add(new Label("Envelope:"), 0, 0);
        grid.add(envelopes, 1, 0);
        Label lValor = new Label("Valor (entre 0 e " + context.getCategoriaByName(envelopes.getValue()).getValor() + "):      ");
        grid.add(lValor, 0, 1);
        grid.add(valor, 1, 1);

        Node btnOk = popUp.getDialogPane().lookupButton(btnOkType);
        btnOk.setDisable(true);
        btnOk.getStyleClass().add("btn");

        valor.textProperty().addListener((observable, oldValue, newValue) -> {
            btnOk.setDisable(!isNumber(newValue));
            if(newValue.isEmpty()){
                btnOk.setDisable(true);
            }else{
                btnOk.setDisable(Double.parseDouble(newValue) <= 0);
                btnOk.setDisable(Double.parseDouble(newValue) > context.getCategoriaByName(envelopes.getValue()).getValor());
            }

        });

        envelopes.valueProperty().addListener((observable, oldValue, newValue) -> {
            lValor.setText("Valor (entre 0 e " + context.getCategoriaByName(newValue).getValor() + "): ");
            //btnOk.setDisable(envelopes.getValue().trim().isEmpty());
            if(!valor.getText().isEmpty())
                btnOk.setDisable(Double.parseDouble(valor.getText()) > context.getCategoriaByName(newValue).getValor());
        });
        ((Button) btnOk).setOnAction(event -> {
            //fechou o popup: temos os valores:
            double valoraretirar = Double.parseDouble(valor.getText());
            String envelopearetirar = envelopes.getValue();

            //retira dinheiro do envelope
            context.getCategoriaByName(envelopearetirar).setValor(
                    context.getCategoriaByName(envelopearetirar).getValor() - valoraretirar
            );

            //coloca neste envelope
            categoria.setValor(
                    categoria.getValor() + valoraretirar
            );

            reset();
        });

        popUp.getDialogPane().setContent(grid);
        popUp.showAndWait();



    }

    private void tfValorFormat(TextField textField){
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
        textField.setTextFormatter(textFormatter);
    }
}
