package com.example.gps_g11.Controller.Envelope;

import com.example.gps_g11.Controller.NaoVaiSerPreciso.Budget.BudgetPanePopUpController;
import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Categoria.Categoria;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class EnvelopeController implements Initializable {
    public ScrollPane scrollPane;
    public VBox vBoxEnvelopes;
    public HBox hbox;
    public BorderPane root;
    public Button btnCriarEnvelope;
    public Button btnGuardaDinheiro;
    private SideBarController sideBarController;
    private Context context;
    public void update() {

        int i = 0;
        int buttonsPerHBox = 5;
        vBoxEnvelopes.getChildren().clear();
        HBox currentHBox = null;

        for (Categoria categoria : context.getCategoriasList()) {
            if (i % buttonsPerHBox == 0) {
                currentHBox = new HBox();
                currentHBox.setSpacing(7);
                vBoxEnvelopes.getChildren().add(currentHBox);
            }

            Button categoriaButton = createCategoriaButton(categoria,categoria.isAberto());
            currentHBox.getChildren().add(categoriaButton);

            i++;
        }
        /*for (Categoria categoria : context.getCategoriasList()) {
            if (i % buttonsPerHBox == 0) {
                currentHBox = new HBox();
                currentHBox.setSpacing(7);
                vBoxEnvelopes.getChildren().add(currentHBox);
            }

            Button categoriaButton = createCategoriaButton(categoria,categoria.isAberto());
            currentHBox.getChildren().add(categoriaButton);

            i++;
        }
        for (Categoria categoria : context.getCategoriasList()) {
            if (i % buttonsPerHBox == 0) {
                currentHBox = new HBox();
                currentHBox.setSpacing(7);
                vBoxEnvelopes.getChildren().add(currentHBox);
            }

            Button categoriaButton = createCategoriaButton(categoria,categoria.isAberto());
            currentHBox.getChildren().add(categoriaButton);

            i++;
        }*/


        if (vBoxEnvelopes.getChildren().size() <= 4) {
            scrollPane.setFitToHeight(true);
        } else {
            scrollPane.setFitToHeight(true);
        }
    }

    private Button createCategoriaButton(Categoria categoria,boolean isAberto) {
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent;");
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #e0e0e0;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent;"));
        button.setPrefSize(150,150);
        Image image;
        ImageView imageView;

        if(isAberto){
            image = new Image(getClass().getResource("/image/open_env_icon.png").toExternalForm());
            imageView  = new ImageView(image);
            imageView.setFitWidth(130);
            imageView.setFitHeight(100);
            button.setGraphic(imageView);
        }else{
            image = new Image(getClass().getResource("/image/saved_money_icon.png").toExternalForm());
            imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            button.setGraphic(imageView);
        }


        Label legendaLabel = new Label(categoria.getNome());
        legendaLabel.setAlignment(Pos.CENTER);
        legendaLabel.setFont(new Font(14));
        legendaLabel.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(legendaLabel, Priority.ALWAYS);

        Label saldoLabel = new Label(formatarNumero(categoria.getValor()) + " €");
        saldoLabel.setAlignment(Pos.CENTER);
        saldoLabel.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(saldoLabel, Priority.ALWAYS);

        VBox vbox = new VBox(imageView, legendaLabel,saldoLabel);
        vbox.setAlignment(Pos.TOP_CENTER);

        button.setGraphic(vbox);

        button.setOnAction(event -> handleCategoriaButtonClick(categoria));

        return button;
    }

    private String formatarNumero(double numero) {
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        return formato.format(numero);
    }
    private void handleCategoriaButtonClick(Categoria categoria) {
        sideBarController.verEnvelope(categoria);
        System.out.println("envelopes"+categoria.getNome());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = Context.getInstance();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        update();
    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void onCriarEnvelope(){
        sideBarController.criarEnvelopesPane();
    }

    public void onGuardaDinheiro() {sideBarController.adicionaDinheiroEnvelope(); }

}
