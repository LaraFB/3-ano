package com.example.gps_g11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login_FXMLController implements Initializable {

    @FXML
    private Button BtnLogin, BtnSignIn, BtnRegister, BtnCancel;
    @FXML
    private Label InvalidText;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private TextField UsernameField;
    @FXML
    private AnchorPane RegisterForm, LoginForm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InvalidText.setText(" ");
        LoginForm.setVisible(true);
        RegisterForm.setVisible(false);

        BtnLogin.setOnMouseClicked(mouseEvent -> {
            if(UsernameField.getText().isBlank() && PasswordField.getText().isBlank()){
                InvalidText.setText("Invalid Login. Please try again.");
            }
            else{
                //verificação c a base de dados/ficheiro == true
                try {
                    Load_Homepage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        BtnSignIn.setOnMouseClicked(mouseEvent -> {
            //validar formulário preenchido
            LoginForm.setVisible(false);
            RegisterForm.setVisible(true);
        });

        BtnRegister.setOnMouseClicked(mouseEvent -> {
            Register();
        });

        BtnCancel.setOnMouseClicked(mouseEvent -> {
            LoginForm.setVisible(true);
            RegisterForm.setVisible(false);
        });
    }

    public void Register(){
        BtnRegister.setOnMouseClicked(mouseEvent -> {
            try {
                Load_Homepage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //Criar conta
    }

    public void Load_Homepage() throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Stage stage= (Stage) BtnLogin.getScene().getWindow();
        stage.close();
        Stage stage_home= new Stage();
        Scene homepage = new Scene(root, 1200, 700);
        stage_home.setTitle("Gestor de Despesas");
        stage_home.setScene(homepage);
        stage_home.show();
    }

}
