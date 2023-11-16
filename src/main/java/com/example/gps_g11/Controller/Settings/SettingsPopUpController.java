package com.example.gps_g11.Controller.Settings;

import com.example.gps_g11.Data.Context;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class SettingsPopUpController implements Initializable {
    private Context context;
    public Label message;
    public TextField TFName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = Context.getInstance();
        message.setVisible(false);
    }

    public void onDelete() {
        if (TFName.getText().isEmpty()) { //sem texto
            message.setVisible(true);
            message.setText("Insira um nome!");
            message.setStyle("-fx-text-fill: #ee0000");
        } else {
            if (context.getCategory(TFName.getText()) == null) { //n existe categoria
                message.setVisible(true);
                message.setText("Categoria inexistente!");
                message.setStyle("-fx-text-fill: #ee0000");
            } else { //exite categoria
                if (context.deleteCategory(TFName.getText())) { //consegue remover
                    message.setVisible(true);
                    message.setText("Removida com sucesso!");
                    message.setStyle("-fx-text-fill: #209d36");
                } else { //n consegue remover
                    message.setVisible(true);
                    message.setText("Erro ao remover categoria...");
                    message.setStyle("-fx-text-fill: #ee0000");
                }
            }
        }
    }
}
