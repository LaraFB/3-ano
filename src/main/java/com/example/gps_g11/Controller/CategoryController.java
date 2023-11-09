package com.example.gps_g11.Controller;

import com.example.gps_g11.SideBarController;
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
    SideBarController sideBarController;

    @FXML
    Button BtnAdc;

    @FXML
    HBox HBox3;

    private Button temp;

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BtnAdc.setOnMouseClicked(mouseEvent -> {
            Dialog dialog = new Dialog<>();
            dialog.setTitle("Adicionar categoria");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.getDialogPane().setContent(createCategoryForm());
            dialog.show();

            if(temp != null){
                HBox3.getChildren().add(temp);
            }
        });
    }

    private Node createCategoryForm(){
        Button AddBtn = new Button("Add");
        TextField nameTf = new TextField();
        TextField descTf = new TextField();

        GridPane gp = new GridPane();
        gp.add(new Label("Name: "),0,0);
        gp.add(new Label("Description: "),0,1);
        gp.add(nameTf,1,0);
        gp.add(descTf,1,1);
        gp.add(AddBtn,0,2);

        AddBtn.setOnMouseClicked(mouseEvent -> {
            if(nameTf.getText() != null && !nameTf.getText().isEmpty())
                temp = new Button(nameTf.getText());
        });

        return gp;
    }
}
