package com.example.gps_g11.Controller;

import com.example.gps_g11.Data.Context;
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
    HBox HBox3;
    // Pop up:
    @FXML
    Button BtnAdcPopUp;
    @FXML
    TextField TFName;
    @FXML
    TextField TFDescription;
    @FXML
    Label LError;
    private Context context;


    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = Context.getInstance();
        update();
    }



    //
    public void popUp(){

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
        update();
    }


    //popup
    public void addItem(){
        if(TFName.getText() == null || TFName.getText().isEmpty()){
            LError.setVisible(true);
        }
        else {
            if (TFName.getText() == null || TFDescription.getText().isEmpty())
                context.addCategory(TFName.getText());
            else
                context.addCategory(TFName.getText(), TFDescription.getText());
        }
    }

    public void update() {
        int i = 0;
        while (context.getCategory(i) != null) {
            Button newBtn = new Button(context.getCategoryName(i));
            HBox3.getChildren().add(newBtn);
            i++;
        }

    }
    public void onAddBudget() {

    }
}
