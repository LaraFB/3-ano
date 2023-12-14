package com.example.gps_g11.Controller.Envelope;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import com.example.gps_g11.Data.Context;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.*;

public class EnvelopeVisualizarController{
    public Button btnEditar;
    public Button btnEliminar;
    private SideBarController sideBarController;
    private CategoriaDespesas categoriaDespesas;
    private CategoriaEntradas categoriaEntradas;
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
    private boolean isEnvelope;


    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void setCategoria(CategoriaDespesas categoria){
        this.categoriaDespesas = categoria;
        if(categoria == null){
            lblError.setText("Erro ao carregar categoria");
            lblError.setVisible(true);
            return;
        }
        isEnvelope = true;
        reset();
        context = Context.getInstance();
    }
    public void setCategoria(CategoriaEntradas categoria){
        this.categoriaEntradas = categoria;
        if(categoria == null){
            lblError.setText("Erro ao carregar categoria");
            lblError.setVisible(true);
            return;
        }
        isEnvelope = false;
        reset();
        context = Context.getInstance();
    }



    public void onEditar(){
        tfNome.setDisable(false);
        taDescricao.setDisable(false);
        btnGuardar.setVisible(true);
        btnGuardar.setDisable(false);
        btnEditar.setDisable(true);

        if(isEnvelope){
            tfValor.setDisable(true);
            tbtnEnvelopeAberto.setDisable(false);
            tbtnEnvelopeFechado.setDisable(false);
            btnEliminar.setVisible(false);
            //checks antes de guardar:
            tfValor.textProperty().addListener((observable, oldValue, newValue) -> { //listener: p ver alterações ao texto
                lblError.setText("O valor não pode estar vazio e tem de ser um número!");
                btnGuardar.setDisable(newValue.trim().isEmpty()); //nao consegue guardar se texto estiver vazio
                lblError.setVisible(newValue.trim().isEmpty()); //aparece erro tb
                btnGuardar.setDisable(!isNumber(newValue)); //ou se n for numero
                lblError.setVisible(!isNumber(newValue));
            });
            tfNome.textProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("Entrei aqui");
                lblError.setText("O nome não pode estar vazio!");
                btnGuardar.setDisable(categoriaDespesas.getNome().equals(newValue) || newValue.trim().isEmpty());
                lblError.setVisible(newValue.trim().isEmpty());
            });
        }else{
            tfNome.textProperty().addListener((observable, oldValue, newValue) -> {
                lblError.setText("O nome não pode estar vazio!");
                btnGuardar.setDisable(categoriaEntradas.getNome().equals(newValue) || newValue.trim().isEmpty());
                lblError.setVisible(newValue.trim().isEmpty());
            });
        }


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
        boolean correct = true;
        if(isEnvelope){
            if(tbtnEnvelopeAberto.isSelected())categoriaDespesas.setAberto(true);
            else categoriaDespesas.setAberto(false);
            for (CategoriaDespesas categoriasListDespesa : context.getCategoriasListDespesas()) {
                if(!(categoriasListDespesa.getNome().equals(tfNome.getText()) && categoriasListDespesa.getDescricao().equals(taDescricao.getText()))){
                        System.out.println("Entrei aquiu");
                        correct = false;
                        lblError.setText("Não pode ter o mesmo nome que outro envelope");
                }
            }
        }else{
            for (CategoriaEntradas categoriaEntrada : context.getCategoriasListEntradas()) {
                if(categoriaEntrada.getNome().equals(categoriaEntradas.getNome())){
                    correct = false;
                    lblError.setText("Não pode ter o mesmo nome que outra categoria");
                }
            }
        }
        lblError.setVisible(true);
        if(correct){
            if(isEnvelope){
                categoriaDespesas.setNome(tfNome.getText());
                categoriaDespesas.setDescricao(taDescricao.getText());
                categoriaDespesas.setValor(Double.parseDouble(tfValor.getText()));
            }else{
                categoriaEntradas.setNome(tfNome.getText());
                categoriaEntradas.setDescricao(taDescricao.getText());
                categoriaEntradas.setValor(Double.parseDouble(tfValor.getText()));
            }
            btnGuardar.setDisable(true);
            btnEditar.setDisable(false);
            btnEliminar.setVisible(false);
            System.out.println("Here");
            reset();
        }
    }

    private void reset(){
        if(isEnvelope){
            lTitulo.setText("Envelope" + categoriaDespesas.getNome());
            tfValor.setText(String.valueOf(categoriaDespesas.getValor()));
            tfNome.setText(categoriaDespesas.getNome());
            taDescricao.setText(categoriaDespesas.getDescricao());


            tfValor.setDisable(true);
            tfNome.setDisable(true);
            taDescricao.setDisable(true);

            if(categoriaDespesas.isAberto()) tbtnEnvelopeAberto.setSelected(true);
            else tbtnEnvelopeFechado.setSelected(true);

            tbtnEnvelopeAberto.setDisable(true);
            tbtnEnvelopeFechado.setDisable(true);
            btnGuardar.setVisible(false);

            lblError.setVisible(false);
        }else{
            lTitulo.setText("Categoria" + categoriaEntradas.getNome());
            tfValor.setText(String.valueOf(categoriaEntradas.getValor()));
            tfNome.setText(categoriaEntradas.getNome());
            taDescricao.setText(categoriaEntradas.getDescricao());


            tfValor.setDisable(true);
            tfNome.setDisable(true);
            taDescricao.setDisable(true);

            tbtnEnvelopeAberto.setVisible(false);
            tbtnEnvelopeFechado.setVisible(false);
            btnGuardar.setVisible(false);
            btnAdcDinheiro.setVisible(false);
            btnEliminar.setVisible(false);
            lblError.setVisible(false);
        }


        //if(!categoria.isAberto())
        //    btnAdcDinheiro.setVisible(false);
        //else btnAdcDinheiro.setVisible(true);
    }

    public void onEliminar(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setGraphic(null);
        alert.setHeaderText(null);
        alert.setTitle("Confirmar eliminação");
        alert.setContentText("Tem a certeza que deseja eliminar o envelope " + categoriaDespesas.getNome() +"?");

        Optional<ButtonType> result = alert.showAndWait();
        if (!(result.get() == ButtonType.OK)){
            return;
        }

        try {
            context.getSaldo().setSaldoNosEnvelopes(context.getSaldo().getSaldoNosEnvelopes()-(Double.parseDouble(tfValor.getText())));
            context.getSaldo().setSaldoPorDistribuir(context.getSaldo().getSaldoPorDistribuir()+(Double.parseDouble(tfValor.getText())));
            context.getCategoriasListDespesas().remove(categoriaDespesas);
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
        if(context.getCategoriasListDespesas().size() == 1){ //se so ha esta categoria
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Aviso");
            alert.setContentText("Não existem envelopes de onde retirar dinheiro.");
            alert.showAndWait();
            return;
        }
        boolean haAbertos = false;
        for (int x = 0; x < context.getCategoriasListDespesas().size(); x++)
            if (context.getCategoriasListDespesas().get(x).isAberto() && context.getCategoriasListDespesas().get(x).getNome().compareTo(categoriaDespesas.getNome()) != 0) {
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
        for(int i=0; i<context.getCategoriasListDespesas().size(); i++)
            if(context.getCategoriasListDespesas().get(i).isAberto() && context.getCategoriasListDespesas().get(i).getNome().compareTo(categoriaDespesas.getNome()) != 0) {
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

        Node btnOk = popUp.getDialogPane().lookupButton(btnOkType);
        btnOk.setDisable(true);
        btnOk.getStyleClass().add("btn");

        valor.textProperty().addListener((observable, oldValue, newValue) -> {
            btnOk.setDisable(!isNumber(newValue));
            btnOk.setDisable(newValue.trim().isEmpty());
            btnOk.setDisable(Double.parseDouble(newValue) <= 0);
            btnOk.setDisable(Double.parseDouble(newValue) > context.getCategoriaByName(envelopes.getValue()).getValor());

        });

        envelopes.valueProperty().addListener((observable, oldValue, newValue) -> {
            lValor.setText("Valor (entre 0 e " + context.getCategoriaByName(newValue).getValor() + "): ");
            btnOk.setDisable(envelopes.getValue().trim().isEmpty());
            if(!valor.getText().isEmpty())
                btnOk.setDisable(Double.parseDouble(valor.getText()) > context.getCategoriaByName(newValue).getValor());
        });

        popUp.getDialogPane().setContent(grid);
        popUp.showAndWait();

        //fechou o popup: temos os valores:
        double valoraretirar = Double.parseDouble(valor.getText());
        String envelopearetirar = envelopes.getValue();

        //retira dinheiro do envelope
        context.getCategoriaByName(envelopearetirar).setValor(
                context.getCategoriaByName(envelopearetirar).getValor() - valoraretirar
        );

        //coloca neste envelope
        categoriaDespesas.setValor(
                categoriaDespesas.getValor() + valoraretirar
        );

        reset();
    }
}
