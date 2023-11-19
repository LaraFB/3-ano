package com.example.gps_g11.Controller;

import com.example.gps_g11.Controller.Envelope.EnvelopeController;
import com.example.gps_g11.Controller.Envelope.EnvelopeCriaEnvelopeController;
import com.example.gps_g11.Controller.Historico.HistoricoController;
import com.example.gps_g11.Controller.Home.HomeController;
import com.example.gps_g11.Controller.Home.HomePageAdicionarDividaController;
import com.example.gps_g11.Controller.Home.HomePageAdicionarSaldoController;
import com.example.gps_g11.Data.Context;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class SideBarController {
    public Button btnHomePage;
    public Button bntHistorico;
    public Button btnEnvelope;
    public Button btnObjetivos;
    public Button btnEstatistica;
    private Context context;
    public Pane ContentPane;

    public void onHomePage(){
        loadFXML("Home/HomePage.fxml");
    }

    public void onHistorico(){
        loadFXML("Historico/Historico.fxml");

    }

    public void onEnvelope(){
        loadFXML("Envelope/Envelope.fxml");
    }

    public void onObjetivos(){

    }

    public void onEstatisticas(){

    }

    private void loadFXML(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Node node = loader.load();
            if (loader != null) {
                switch (fxmlFileName){
                    case "Home/HomePage.fxml":
                        HomeController homeController = loader.getController();
                        homeController.setSideBar(this);
                        putBtnActive(btnHomePage);
                        break;
                    case "Historico/Historico.fxml":
                        HistoricoController historicoController = loader.getController();
                        historicoController.setSideBar(this);
                        putBtnActive(bntHistorico);
                        break;
                    case "Envelope/Envelope.fxml":
                        EnvelopeController envelopeController = loader.getController();
                        envelopeController.setSideBar(this);
                        putBtnActive(btnEnvelope);
                        break;
                    case "Envelope/EnvelopeCriaEnvelope.fxml":
                        EnvelopeCriaEnvelopeController envelopeCriaEnvelopeController = loader.getController();
                        envelopeCriaEnvelopeController.setSideBar(this);
                        putBtnActive(btnEnvelope);
                        break;
                    case "Home/HomePageAdicionarDivida.fxml":
                        HomePageAdicionarDividaController homePageAdicionarDividaController = loader.getController();
                        homePageAdicionarDividaController.setSideBar(this);
                        putBtnActive(btnEnvelope);
                        break;
                    case "Home/HomePageAdicionarSaldo.fxml":
                        HomePageAdicionarSaldoController homePageAdicionarSaldoController = loader.getController();
                        homePageAdicionarSaldoController.setSideBar(this);
                        putBtnActive(btnEnvelope);
                        break;
                    default:
                }
            }
            ContentPane.getChildren().setAll(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putBtnActive(Button btn) {
        btnHomePage.getStyleClass().remove("slideItem1");
        bntHistorico.getStyleClass().remove("slideItem1");
        btnEnvelope.getStyleClass().remove("slideItem1");
        if (btn.equals(btnHomePage)) {
            btnHomePage.getStyleClass().add("slideItem1");
        }else if (btn.equals(bntHistorico)) {
            bntHistorico.getStyleClass().add("slideItem1");
        }else if (btn.equals(btnEnvelope)) {
            btnEnvelope.getStyleClass().add("slideItem1");
        }
    }

    public void initialize(){
        context = Context.getInstance();
        onHomePage();
    }

    public void criarEnvelopesPane(){
        loadFXML("Envelope/EnvelopeCriaEnvelope.fxml");
    }
    public void adicionarDespesa(){
        loadFXML("Home/HomePageAdicionarDivida.fxml");
    }

    public void adicionarSaldo() {
        loadFXML("Home/HomePageAdicionarSaldo.fxml");
    }
}


