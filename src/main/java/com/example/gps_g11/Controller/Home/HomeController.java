package com.example.gps_g11.Controller.Home;
import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.ToDos.ToDo;
import com.example.gps_g11.Data.Transacao.Despesa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;


import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class HomeController implements Initializable {

    public Label lblSaldoRealCB;
    public Label lblSaldoRealD;
    public Label lblSaldoTotal;
    public Label lblSaldoDistribuir;
    public Label lblSaldoEnvelopes;
    public Label lblTotalDespesas;
    private Context context;
    public VBox VBoxToDo;

    public BorderPane root;
    private SideBarController sideBarController;

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        context = Context.getInstance();
        updateHomePage();
    }

    private String formatarNumero(double numero) {
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        return formato.format(numero);
    }

    public void onAdicionarDespesa(){
        sideBarController.adicionarDespesa();
    }

    public void onAdicionarSaldo() {
        sideBarController.adicionarSaldo();
    }
    public void onTransaction() {sideBarController.transacao();}//sideBarController.transaction();
    public void updateNotificacoes(){
        context.getListaNotificacoes().sort();
        VBoxToDo.getChildren().clear();

        if(context.getSaldo().getSaldoPorDistribuir() > 0)
            context.getListaNotificacoes().addToDo("Ainda tem saldo para distribuir pelos envelopes", ToDo.TYPE.ALERT);
        else
            context.getListaNotificacoes().removeToDo("Ainda tem saldo para distribuir pelos envelopes");

        if(context.getSaldo().getBudgetDinheiro().getSaldoReal() < 10)
            context.getListaNotificacoes().addToDo("Cuidado, tem menos de 10€ em dinheiro!", ToDo.TYPE.ALERT);
        else
            context.getListaNotificacoes().removeToDo("Cuidado, tem menos de 10€ em dinheiro!");

        if(context.getSaldo().getBudgetDinheiro().isExcedeuSaldo())
            context.getListaNotificacoes().addToDo("Atenção! Excedeu o seu budget de dinheiro!", ToDo.TYPE.ALERT);
        else
            context.getListaNotificacoes().removeToDo("Atenção! Excedeu o seu budget de dinheiro!");

        if(context.getSaldo().getBudgetContaBancaria().getSaldoReal() < 10)
            context.getListaNotificacoes().addToDo("Cuidado, tem menos de 10€ na sua conta bancária!", ToDo.TYPE.ALERT);
        else
            context.getListaNotificacoes().removeToDo("Cuidado, tem menos de 10€ na sua conta bancária!");

        if(context.getSaldo().getBudgetContaBancaria().isExcedeuSaldo())
            context.getListaNotificacoes().addToDo("Atenção! Excedeu o seu budget na sua conta bancária!", ToDo.TYPE.ALERT);
        else
            context.getListaNotificacoes().removeToDo("Atenção! Excedeu o seu budget na sua conta bancária!");

        if(context.getSaldo().getSaldoNosEnvelopes() < 10)
            context.getListaNotificacoes().addToDo("Atenção, tem menos de 10€ para gastar nos seus envelopes...", ToDo.TYPE.NOTIFICATION);
        else
            context.getListaNotificacoes().removeToDo("Atenção, tem menos de 10€ para gastar nos seus envelopes...");

        for (CategoriaDespesas d: context.getCategoriasListDespesas()) {
            if(d.isRecorrente() && d.getValor()>0){
                context.getListaNotificacoes().addToDo("Já pagaste " + d.getNome() + " este mês?", ToDo.TYPE.REQUEST, d.getNome());
            }
        }

        if(context.getListaNotificacoes().isEmpty()) return;

        for(int i=0; i<context.getListaNotificacoes().size(); i++){
            Label lNot = new Label( context.getListaNotificacoes().get(i).getDescription());
            lNot.setCursor(Cursor.HAND);
            switch (context.getListaNotificacoes().get(i).getType()){
                case ALERT -> {
                    lNot.setStyle("-fx-text-fill: #ff676a; -fx-font-size: 16px;-fx-font-family: 'Times New Roman';");

                    int notificacao  = i;
                    lNot.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            clicaAlerta(context.getListaNotificacoes().get(notificacao));
                            //clicaNotificacaoNOTIFICATION(context.getListaNotificacoes().get(notificacao));
                        }
                    });
                }
                case REQUEST -> {
                    lNot.setStyle("-fx-font-size: 16px;-fx-font-family: 'Times New Roman';");
                    int notificacao  = i;
                    lNot.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            clicaNotificacaoREQUEST(context.getListaNotificacoes().get(notificacao));
                        }
                    });
                }
                case NOTIFICATION -> {
                    lNot.setStyle("-fx-text-fill: #545454; -fx-font-size: 16px;-fx-font-family: 'Times New Roman';");

                    int notificacao  = i;
                    lNot.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            //clicaNotificacaoNOTIFICATION(context.getListaNotificacoes().get(notificacao));
                        }
                    });
                }
                case USER_GENERATED -> {
                    lNot.setStyle("-fx-text-fill: #545454; -fx-font-size: 16px;-fx-font-family: 'Times New Roman';");

                    int notificacao  = i;
                    lNot.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            clicaNotificacaoUSER(context.getListaNotificacoes().get(notificacao));
                        }
                    });
                }
            }

            VBoxToDo.getChildren().add(lNot);
            Separator separator = new Separator(Orientation.HORIZONTAL);
            VBoxToDo.getChildren().add(separator);
        }
    }

    private boolean isNumber(String text){
        try{
            Double.parseDouble(text);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private void clicaAlerta(ToDo td) {
        if(td.getDescription().equals("Ainda tem saldo para distribuir pelos envelopes")){
            //caso específico
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Distribuir saldo");

            alert.getDialogPane().setStyle("-fx-font-family: 'Times New Roman';-fx-background-color: #DEEFFF;-fx-text-fill: #545454;");
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            Label labelValor = new Label("Dinheiro a distribuir: ");
            Label lValor = new Label(String.valueOf(context.getSaldo().getSaldoPorDistribuir()));

            grid.add(labelValor,0,0);
            grid.add(lValor,1,0);

            ChoiceBox<String> cbEnvelopes = new ChoiceBox<>();
            for (CategoriaDespesas env: context.getCategoriasListDespesas())
                cbEnvelopes.getItems().add(env.getNome());

            cbEnvelopes.setStyle("-fx-background-color: #9FCDFF;-fx-font-family: 'Times New Roman';");

            Label lEnv = new Label("Envelope ");

            grid.add(lEnv,0,1);
            grid.add(cbEnvelopes,1,1);

            Label lValorEscolhido = new Label("Valor: ");
            TextField tfValor = new TextField();
            tfValor.setStyle("-fx-background-color: #9FCDFF");

            grid.add(lValorEscolhido,0,2);
            grid.add(tfValor,1,2);

            ButtonType buttonSim = new ButtonType("Adicionar");
            ButtonType buttonNao = new ButtonType("Cancelar");

            alert.getButtonTypes().setAll(buttonNao);
            alert.getDialogPane().lookupButton(buttonNao).setStyle("-fx-background-color:#ff676a;-fx-font-family: 'Times New Roman';");


            tfValor.textProperty().addListener((observable, oldValue, newValue) -> {
                if(!isNumber(newValue) || newValue.trim().isEmpty() ||
                        Double.parseDouble(newValue) <= 0 || Double.parseDouble(newValue) >context.getSaldo().getSaldoPorDistribuir()){
                    alert.getButtonTypes().clear();
                    alert.getButtonTypes().add(buttonNao);
                    alert.getDialogPane().lookupButton(buttonNao).setStyle("-fx-background-color:#ff676a;-fx-font-family: 'Times New Roman';");
                }
                else{
                    alert.getButtonTypes().clear();
                    alert.getButtonTypes().add(buttonSim);
                    alert.getButtonTypes().add(buttonNao);


                    alert.getDialogPane().lookupButton(buttonSim).setStyle("-fx-background-color:#92d0ff;-fx-font-family: 'Times New Roman';");
                    alert.getDialogPane().lookupButton(buttonNao).setStyle("-fx-background-color:#ff676a;-fx-font-family: 'Times New Roman';");
                }
            });

            alert.getDialogPane().setContent(grid);


            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonSim) {
                double val = Double.parseDouble(tfValor.getText());

                //retira do saldo por distribuir
                context.getSaldo().setSaldoPorDistribuir(
                        context.getSaldo().getSaldoPorDistribuir() - val
                );

                //Adicionar ao dinheiro nos envelopes
                context.getSaldo().setSaldoNosEnvelopes(
                        context.getSaldo().getSaldoNosEnvelopes()+val
                );

                //adiciona ao envelope
                context.getCategoriaByName(cbEnvelopes.getValue()).setValor(
                        context.getCategoriaByName(cbEnvelopes.getValue()).getValor() + val
                );

                updateHomePage();
            }
        }
    }


    private void clicaNotificacaoREQUEST(ToDo td) {

        if(context.getSaldo().getBudgetContaBancaria().getSaldoReal() < context.getCategoriaByName(td.getEnvelope()).getValor()
        && context.getSaldo().getBudgetDinheiro().getSaldoReal() < context.getCategoriaByName(td.getEnvelope()).getValor()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setGraphic(null);
            alert.setTitle("Aviso");
            alert.setHeaderText("Não tem dinheiro suficiente para pagar!!!");

            alert.getButtonTypes().setAll(new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE));
            alert.showAndWait();
            return;
        }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Confirmação");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            Label desc = new Label(td.getDescription());
            desc.setStyle("-fx-font-size: 12px;-fx-font-family: 'Times New Roman';");

            Label ldinheiro = new Label("..em dinheiro?");
            CheckBox isDinheiro = new CheckBox();

            grid.add(desc, 0, 0);

            if(context.getSaldo().getBudgetContaBancaria().getSaldoReal() < context.getCategoriaByName(td.getEnvelope()).getValor()){
                ldinheiro.setText("Saldo insuficiente na conta bancária. Pagamento pressuposto em dinheiro");
                grid.add(ldinheiro, 0, 1);
                //grid.add(isDinheiro, 1, 1);

                isDinheiro.setSelected(true);
                //isDinheiro.setDisable(true);
            }
            else {
                if (context.getSaldo().getBudgetDinheiro().getSaldoReal() >= context.getCategoriaByName(td.getEnvelope()).getValor()) {
                    ldinheiro.setText("Saldo insuficiente em numerário. Pagamento pressuposto em tranferência bancária.");
                    grid.add(ldinheiro, 0, 1);
                    //grid.add(isDinheiro, 1, 1);
                } else isDinheiro.setSelected(false);
            }

            alert.getDialogPane().setContent(grid);

            ButtonType buttonSim = new ButtonType("Sim");
            ButtonType buttonNao = new ButtonType("Não");

            alert.getButtonTypes().setAll(buttonSim, buttonNao);

            alert.getDialogPane().lookupButton(buttonSim).setStyle("-fx-background-color:#92d0ff;-fx-font-family: 'Times New Roman';");
            alert.getDialogPane().lookupButton(buttonNao).setStyle("-fx-background-color:#ff676a;-fx-font-family: 'Times New Roman';");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonSim) {
                if(td.getEnvelope() != null && td.getEnvelope().equals("Objetivos")){
                    context.adicionarDespesa(td.getEnvelope(), "Pagou "+td.getEnvelope(), context.getData(), td.getValor(), isDinheiro.isSelected());
                }else{
                    context.adicionarDespesa(td.getEnvelope(), "Pagou "+td.getEnvelope(), context.getData(), context.getCategoriaByName(td.getEnvelope()).getValor(), isDinheiro.isSelected());
                }

                context.getListaNotificacoes().removeToDo(td);
                updateHomePage();
            }

    }
    private void clicaNotificacaoUSER(ToDo td){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setTitle("Confirmação");
        alert.setContentText("Deseja eliminar esta notificação?");
        if(td.getEnvelope() != null && td.getEnvelope().equals("Objetivos")){
            alert.setContentText("Deseja usar o dinheiro do objetivo?");
        }

        ButtonType buttonSim = new ButtonType("Sim");
        ButtonType buttonNao = new ButtonType("Não");

        alert.getButtonTypes().setAll(buttonSim, buttonNao);

        alert.getDialogPane().lookupButton(buttonSim).setStyle("-fx-background-color:#92d0ff;-fx-font-family: 'Times New Roman';");
        alert.getDialogPane().lookupButton(buttonNao).setStyle("-fx-background-color:#ff676a;-fx-font-family: 'Times New Roman';");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonSim) {
            context.getListaNotificacoes().removeToDo(td);
            if(td.getEnvelope() != null && td.getEnvelope().equals("Objetivos")){
                for (CategoriaDespesas categoriasListDespesa : context.getCategoriasListDespesas()) {
                    if(categoriasListDespesa.getNome().equals(td.getEnvelope())){
                        categoriasListDespesa.setValor(categoriasListDespesa.getValor()-td.getValor());
                        String texto = td.getDescription();
                        int indiceTraco = texto.indexOf("-");
                        String resultado = null;
                        if (indiceTraco != -1) {
                            resultado = texto.substring(indiceTraco + 1).trim();
                            System.out.println(resultado);
                        }
                        context.getListaNotificacoes().addToDo("Já pagou o objetivo - " + resultado, ToDo.TYPE.REQUEST,td.getEnvelope(),td.getValor());
                        context.getListaObjetivos().getObjetivo(resultado).setDone(true);
                    }
                }
            }

            updateHomePage();
        }
    }

    public void onAdicionarToDo(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setTitle("Adicionar notificação");
        alert.getDialogPane().setStyle("-fx-background-color: #DEEFFF;-fx-font-family: 'Times New Roman';-fx-text-fill: #545454;-fx-font-size: 16px;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label lDesc = new Label("Descrição: ");
        TextField tfDesc = new TextField();
        tfDesc.setStyle("-fx-background-color: #9FCDFF;-fx-font-family: 'Times New Roman';");

        grid.add(lDesc,0,0);
        grid.add(tfDesc,1,0);

        alert.getDialogPane().setContent(grid);

        ButtonType buttonSim = new ButtonType("Adicionar");
        ButtonType buttonNao = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(buttonSim, buttonNao);

        alert.getDialogPane().lookupButton(buttonSim).setStyle("-fx-background-color:#92d0ff;-fx-font-family: 'Times New Roman';");
        alert.getDialogPane().lookupButton(buttonNao).setStyle("-fx-background-color:#ff676a;-fx-font-family: 'Times New Roman';");

        tfDesc.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!tfDesc.getText().isEmpty())
                alert.getDialogPane().lookupButton(buttonSim).setDisable(false);
            else
                alert.getDialogPane().lookupButton(buttonSim).setDisable(true);
        });

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonSim){
            if(!tfDesc.getText().isEmpty() && tfDesc.getText().trim() != ""){
                context.getListaNotificacoes().addToDo(tfDesc.getText(), ToDo.TYPE.USER_GENERATED);
                updateHomePage();
            }
        }
    }

    private void updateHomePage(){
        lblSaldoRealCB.setText(formatarNumero(context.getSaldo().getBudgetContaBancaria().getSaldoReal()) + " €");
        lblSaldoRealD.setText(formatarNumero(context.getSaldo().getBudgetDinheiro().getSaldoReal()) + " €");
        lblSaldoTotal.setText(formatarNumero(context.getSaldo().getBudgetDinheiro().getSaldoReal()+context.getSaldo().getBudgetContaBancaria().getSaldoReal()) + " €");

        lblSaldoDistribuir.setText(formatarNumero(context.getSaldo().getSaldoPorDistribuir()) + " €");
        if(context.getSaldo().getSaldoPorDistribuir() > 0)
            lblSaldoDistribuir.setStyle("-fx-text-fill: red;-fx-font-family: 'Times New Roman';");
        else
            lblSaldoDistribuir.setStyle("-fx-text-fill: #545454;-fx-font-family: 'Times New Roman';");

        lblSaldoEnvelopes.setText(formatarNumero(context.getSaldo().getSaldoNosEnvelopes()) + " €");
        lblTotalDespesas.setText(formatarNumero(context.getSaldo().getTotalDespesas()) + " €");

        updateNotificacoes();
    }
}
