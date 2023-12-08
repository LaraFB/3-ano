package com.example.gps_g11.Controller.Settings;

import com.example.gps_g11.Controller.Category.CategoryDetailsPopUp;
import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsController implements Initializable {
    public VBox vboxCategories;
    public ScrollPane scrollPane;
    public BorderPane root;
    private SideBarController sideBarController;
    public Button BtnAdc;
    private Context context;
    public HBox HBox3;

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
        vboxCategories.getChildren().clear();
        HBox currentHBox = null;

        while (context.getCategory(i) != null) {
            Button newBtn = new Button(context.getCategoryName(i));
            newBtn.getStylesheets().add("../Style.css");
            newBtn.getStyleClass().add("MenuItem");
            newBtn.setStyle("-fx-font-size: 20px");
            newBtn.setPrefWidth(180);
            newBtn.setPrefHeight(80);

            if (i % buttonsPerHBox == 0) {
                currentHBox = new HBox();
                currentHBox.setAlignment(Pos.CENTER);
                currentHBox.setPrefHeight(150);
                currentHBox.setPrefWidth(200);
                currentHBox.setSpacing(50);
                vboxCategories.getChildren().add(currentHBox);
            }

            currentHBox.getChildren().add(newBtn);
            i++;
        }
        if (vboxCategories.getChildren().size() <= 4) {
            scrollPane.setFitToHeight(true);
        } else {
            scrollPane.setFitToHeight(false);
        }
    }

    public void onAdd(ActionEvent actionEvent) {
        sideBarController.onAdd(actionEvent);
    }

    public void onRemove() {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsPopUp.fxml"));
            Pane secondaryPane = loader.load();

            Stage secondaryStage = new Stage();
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initOwner(root.getScene().getWindow());
            Scene secondaryScene = new Scene(secondaryPane);

            SettingsPopUpController settingsPopUpController = loader.getController();
            settingsPopUpController.setData(this);

            secondaryStage.setScene(secondaryScene);
            secondaryStage.setTitle("Eliminar Despesas");
            secondaryStage.setOnHidden(event -> {
                update();
            });
            secondaryStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
