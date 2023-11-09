package com.example.gps_g11;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Pane());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBar.fxml"));
        stage.setScene(scene);
        scene.setRoot(loader.load());

        stage.setWidth(1200);
        stage.setHeight(700);
        stage.setTitle("Gestor de Despesas");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}