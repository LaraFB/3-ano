package com.example.gps_g11.Controller.Home;

import com.example.gps_g11.Controller.NaoVaiSerPreciso.Budget.BudgetPanePopUpController;
import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.ToDos.ToDo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
        lblSaldoRealCB.setText(formatarNumero(context.getSaldo().getBudgetContaBancaria().getSaldoReal()) + " €");
        lblSaldoRealD.setText(formatarNumero(context.getSaldo().getBudgetDinheiro().getSaldoReal()) + " €");
        lblSaldoTotal.setText(formatarNumero(context.getSaldo().getBudgetDinheiro().getSaldoReal()+context.getSaldo().getBudgetContaBancaria().getSaldoReal()) + " €");
        lblSaldoDistribuir.setText(formatarNumero(context.getSaldo().getSaldoPorDistribuir()) + " €");
        lblSaldoEnvelopes.setText(formatarNumero(context.getSaldo().getSaldoNosEnvelopes()) + " €");
        lblTotalDespesas.setText(formatarNumero(context.getSaldo().getTotalDespesas()) + " €");

        context.getListaNotificacoes().addToDo("Notificação exemplo...", ToDo.TYPE.NOTIFICATION);
        context.getListaNotificacoes().addToDo("Alerta exemplo!", ToDo.TYPE.ALERT);

        context.getListaNotificacoes().sort();
        updateNotificacoes();
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

    public void updateNotificacoes(){
        VBoxToDo.getChildren().clear();
        if(context.getSaldo().getSaldoPorDistribuir() > 0)
            context.getListaNotificacoes().addToDo("Ainda tem saldo para distribuir pelos envelopes", ToDo.TYPE.NOTIFICATION);

        if(context.getSaldo().getBudgetDinheiro().getSaldoReal() < 10)
            context.getListaNotificacoes().addToDo("Cuidado, tem menos de 10€ em dinheiro!", ToDo.TYPE.ALERT);

        if(context.getSaldo().getBudgetDinheiro().isExcedeuSaldo())
            context.getListaNotificacoes().addToDo("Atenção! Excedeu o seu budget de dinheiro!", ToDo.TYPE.ALERT);

        if(context.getSaldo().getBudgetContaBancaria().getSaldoReal() < 10)
            context.getListaNotificacoes().addToDo("Cuidado, tem menos de 10€ na sua conta bancária!", ToDo.TYPE.ALERT);

        if(context.getSaldo().getBudgetContaBancaria().isExcedeuSaldo())
            context.getListaNotificacoes().addToDo("Atenção! Excedeu o seu budget na sua conta bancária!", ToDo.TYPE.ALERT);

        if(context.getSaldo().getSaldoNosEnvelopes() < 10)
            context.getListaNotificacoes().addToDo("Atenção, tem menos de 10€ para gastar nos seus envelopes...", ToDo.TYPE.NOTIFICATION);

        if(context.getCategoriaByName("Propinas") != null &&
                context.getCategoriaByName("Propinas").getValor()>0)
            context.getListaNotificacoes().addToDo("Já pagaste as propinas este mês?", ToDo.TYPE.REQUEST);

        if(context.getListaNotificacoes().isEmpty()) return;

        for(int i=0; i<context.getListaNotificacoes().size(); i++){
            Label lNot = new Label( context.getListaNotificacoes().get(i).getDescription());
            switch (context.getListaNotificacoes().get(i).getType()){
                case ALERT -> lNot.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                case REQUEST -> {
                    lNot.setStyle("-fx-font-size: 16px;");
                    int notificacao  = i;
                    lNot.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            clicaNotificacao(context.getListaNotificacoes().get(notificacao));
                        }
                    });
                }
                case NOTIFICATION -> lNot.setStyle("-fx-text-fill: gray; -fx-font-size: 16px;");
            }

            VBoxToDo.getChildren().add(lNot);
        }
    }

    private void clicaNotificacao(ToDo td) {
        if (td.getDescription().contains("pagaste as propinas")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Confirmação");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            Label desc = new Label(td.getDescription());
            desc.setStyle("-fx-font-size: 12px;");

            Label ldinheiro = new Label("..em dinheiro?");
            CheckBox isDinheiro = new CheckBox();

            grid.add(desc, 0, 0);
            grid.add(ldinheiro, 0, 1);
            grid.add(isDinheiro, 1, 1);

            alert.getDialogPane().setContent(grid);

            ButtonType buttonSim = new ButtonType("Sim");
            ButtonType buttonNao = new ButtonType("Não");

            alert.getButtonTypes().setAll(buttonSim, buttonNao);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonSim) {

                context.adicionarDespesa("Propinas", "Pagou propinas", LocalDate.now(), context.getCategoriaByName("Propinas").getValor(), isDinheiro.isSelected());

                context.getListaNotificacoes().removeToDo(td);
                updateNotificacoes();
            }
        }
    }

    public void onAdicionarToDo(){

    }

}
