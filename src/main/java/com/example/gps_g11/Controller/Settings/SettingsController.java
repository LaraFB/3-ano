package com.example.gps_g11.Controller.Settings;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class SettingsController implements Initializable {
    public VBox vboxCategories;
    public ScrollPane scrollPane;
    SideBarController sideBarController;
    @FXML
    Button BtnAdc;
    @FXML
    TextField TFName;
    @FXML
    TextField TFDescription;
    @FXML
    Label LError;
    private Context context;

    public HBox HBox1, HBox2, HBox3;
    private int counter = 0;


    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = Context.getInstance();
        update();
    }

    public void update() {
        int i = 0;
        int buttonsPerHBox = 4;
        HBox currentHBox = null;

        while (context.getCategory(i) != null) {
            Button newBtn = new Button(context.getCategoryName(i));
            newBtn.getStylesheets().add("../Style.css");
            newBtn.getStyleClass().add("MenuItem");
            newBtn.setStyle("-fx-font-size: 20px");
            newBtn.setPrefWidth(180);
            newBtn.setPrefHeight(80);

            if (counter % buttonsPerHBox == 0) {
                currentHBox = new HBox();
                currentHBox.setAlignment(Pos.CENTER);
                currentHBox.setPrefHeight(150);
                currentHBox.setPrefWidth(200);
                currentHBox.setSpacing(50);
                vboxCategories.getChildren().add(currentHBox);
            }

            currentHBox.getChildren().add(newBtn);

            counter++;
            i++;
        }
        if (vboxCategories.getChildren().size() <= 4) {
            scrollPane.setFitToHeight(true);
        } else {
            scrollPane.setFitToHeight(false);
        }
    }


    public void onAdd(ActionEvent actionEvent) throws IOException {
        sideBarController.onAdd(actionEvent);
    }

    public void onRemove(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsPopUp.fxml"));
            Node node = loader.load();
            Dialog dialog = new Dialog<>();
            dialog.setTitle("Adicionar Categoria");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.getDialogPane().setContent(node);
            dialog.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
