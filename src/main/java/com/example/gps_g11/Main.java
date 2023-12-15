package com.example.gps_g11;

import com.example.gps_g11.Data.Context;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Pane());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Controller/SideBar.fxml"));
        stage.setScene(scene);
        scene.setRoot(loader.load());

        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/Icon_App.png"))));
        stage.setTitle("Gestor de Despesas");
        stage.setResizable(false);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Context.getInstance().saveToFile();
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}