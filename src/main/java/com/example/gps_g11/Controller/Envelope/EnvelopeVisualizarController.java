package com.example.gps_g11.Controller.Envelope;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Categoria.Categoria;
import com.example.gps_g11.Data.Context;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class EnvelopeVisualizarController implements Initializable {
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


    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
        System.out.println("fun"+categoria.getNome());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //System.out.println("init"+categoria.getNome());

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

        if(categoria.isAberto()) tbtnEnvelopeAberto.getStyleClass().add("btn_history");
        else tbtnEnvelopeFechado.getStyleClass().add("btn_history");

        tbtnEnvelopeAberto.setDisable(true);
        tbtnEnvelopeFechado.setDisable(true);
        btnGuardar.setVisible(false);

        lblError.setVisible(false);
    }

    public void onEliminar(){
        try {
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
    }

    public void onBackToEnvelope(){
        sideBarController.onEnvelope();
    }
}
