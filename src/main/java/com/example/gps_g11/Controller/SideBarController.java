package com.example.gps_g11.Controller;

import com.example.gps_g11.Controller.Envelope.EnvelopeController;
import com.example.gps_g11.Controller.Envelope.EnvelopeCriaEnvelopeController;
import com.example.gps_g11.Controller.Envelope.EnvelopeAdicionaDinheiroController;
import com.example.gps_g11.Controller.Envelope.EnvelopeVisualizarController;
import com.example.gps_g11.Controller.Estatistica.EstatisticaController;
import com.example.gps_g11.Controller.Historico.HistoricoController;
import com.example.gps_g11.Controller.Home.HomeController;
import com.example.gps_g11.Controller.Home.HomePageAdicionarDividaController;
import com.example.gps_g11.Controller.Home.HomePageAdicionarSaldoController;
import com.example.gps_g11.Controller.Home.HomeResetController;
import com.example.gps_g11.Controller.Objetivo.ObjetivoController;

import com.example.gps_g11.Controller.Objetivo.adicionarObjetivoController;
import com.example.gps_g11.Controller.Objetivo.editarObjetivoController;
import com.example.gps_g11.Data.Categoria.Categoria;
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

    private Categoria c;

    public void onHomePage(){
        loadFXML("Home/HomePage.fxml");
    }

    public void onHistorico(){loadFXML("Historico/Historico.fxml");}

    public void onEnvelope(){
        loadFXML("Envelope/Envelope.fxml");
    }

    public void onObjetivos(){
        loadFXML("Objetivo/Objetivo.fxml");
    }

    public void onEstatisticas(){ loadFXML("Estatisticas/Estatisticas.fxml");}

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
                    case "Envelope/EnvelopeAdicionaDinheiro.fxml":
                        EnvelopeAdicionaDinheiroController envelopeGuardaDinheiroController =loader.getController();
                        envelopeGuardaDinheiroController.setSideBar(this);
                        putBtnActive(btnEnvelope);
                        break;
                    case "Envelope/EnvelopeVisualizar.fxml":
                        EnvelopeVisualizarController envelopeVisualizarController =loader.getController();
                        envelopeVisualizarController.setSideBar(this);
                        System.out.println("sidebar-loadfml"+c.getNome());
                        envelopeVisualizarController.setCategoria(c);
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
                    case "Objetivo/Objetivo.fxml":
                        ObjetivoController objetivoController = loader.getController();
                        objetivoController.setSideBar(this);
                        putBtnActive(btnObjetivos);
                        break;
                    case "Objetivo/adicionarObjetivo.fxml":
                        adicionarObjetivoController adcObjetivoController = loader.getController();
                        adcObjetivoController.setSideBar(this);
                        putBtnActive(btnObjetivos);
                        break;
                    case "Objetivo/editarObjetivo.fxml":
                        editarObjetivoController editObjetivoController = loader.getController();
                        editObjetivoController.setSideBar(this);
                        putBtnActive(btnObjetivos);
                        break;
                    case "Estatisticas/Estatisticas.fxml":
                        EstatisticaController estatitcaController = loader.getController();
                        estatitcaController.setSideBar(this);
                        putBtnActive(btnEstatistica);
                        break;
                    case "Home/HomePageReset.fxml":
                        HomeResetController homeResetController =loader.getController();
                        homeResetController.setSideBar(this);
                        putBtnActive(btnHomePage);
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
        btnObjetivos.getStyleClass().remove("slideItem1");
        btnEstatistica.getStyleClass().remove("slideItem1");

        if (btn.equals(btnHomePage)) {
            btnHomePage.getStyleClass().add("slideItem1");
        }else if (btn.equals(bntHistorico)) {
            bntHistorico.getStyleClass().add("slideItem1");
        }else if (btn.equals(btnEnvelope)) {
            btnEnvelope.getStyleClass().add("slideItem1");
        }else if(btn.equals(btnObjetivos)){
            btnObjetivos.getStyleClass().add("slideItem1");
        }else if(btn.equals(btnEstatistica)){
            btnEstatistica.getStyleClass().add("slideItem1");
        }
    }

    public void initialize(){
        context = Context.getInstance();
        onHomePage();
    }

    public void criarEnvelopesPane(){
        loadFXML("Envelope/EnvelopeCriaEnvelope.fxml");
    }
    public void adicionaDinheiroEnvelope(){loadFXML("Envelope/EnvelopeAdicionaDinheiro.fxml");}
    public void adicionarDespesa(){
        loadFXML("Home/HomePageAdicionarDivida.fxml");
    }
    public void verEnvelope(Categoria c){
        this.c = c;
        System.out.println("sidebarcontroller"+c.getNome());
        loadFXML("Envelope/EnvelopeVisualizar.fxml");
    }

    public void adicionarSaldo() {
        loadFXML("Home/HomePageAdicionarSaldo.fxml");
    }

    public void adicionarObjetivos() { loadFXML("Objetivo/adicionarObjetivo.fxml");}

    public void resetbudget(){loadFXML("Home/HomePageReset.fxml");}

    public void editarObjetivos() { loadFXML("Objetivo/editarObjetivo.fxml");}
}


