package com.example.gps_g11;

import com.example.gps_g11.SideBarController;
import com.example.gps_g11.categoryManagment.categoryHandler;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.time.LocalDate;

public class CategoryController implements Initializable {
    categoryHandler handler = new categoryHandler();

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


    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update();
    }


    //
    public void popUp(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryPopUp.fxml"));
            Node node = loader.load();
            Dialog dialog = new Dialog<>();
            dialog.setTitle("Adicionar categoria");
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
                handler.addCategory(TFName.getText());
            else
                handler.addCategory(TFName.getText(), TFDescription.getText());
        }
    }

    public void update(){
        int i=0;
        if(!handler.isEmpty())
            while(handler.getCategory(i) != null){
                Button newBtn = new Button(handler.getCategoryName(i));
                HBox3.getChildren().add(newBtn);
                i++;
            }
    }
}
