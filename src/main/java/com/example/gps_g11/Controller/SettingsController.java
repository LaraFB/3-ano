package com.example.gps_g11.Controller;

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
        while (context.getCategory(i) != null) {
            Button newBtn = new Button(context.getCategoryName(i));
            newBtn.getStylesheets().add("../Style.css");
            newBtn.getStyleClass().add("MenuItem");

            newBtn.setStyle("-fx-font-size: 20px");
            newBtn.setPrefWidth(180);
            newBtn.setPrefHeight(80);

            if(counter<4)
                HBox1.getChildren().add(newBtn);
            else{
                if(counter < 8)
                    HBox2.getChildren().add(newBtn);
                else
                    HBox3.getChildren().add(newBtn);
            }
            counter++;
            i++;
        }

    }

    public void onAdd(ActionEvent actionEvent) throws IOException {
        sideBarController.onAdd(actionEvent);
    }

    public void onRemove(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryPopUp.fxml"));
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
