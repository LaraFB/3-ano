package com.example.gps_g11.Controller;

import com.example.gps_g11.Controller.Envelope.EnvelopeController;
import com.example.gps_g11.Controller.Envelope.EnvelopeCriaEnvelopeController;
import com.example.gps_g11.Controller.Envelope.EnvelopeAdicionaDinheiroController;
import com.example.gps_g11.Controller.Envelope.EnvelopeVisualizarController;
import com.example.gps_g11.Controller.Estatistica.EstastiticasDespesaGastoDiaController;
import com.example.gps_g11.Controller.Estatistica.EstastiticasEnvelopeController;
import com.example.gps_g11.Controller.Historico.HistoricoController;
import com.example.gps_g11.Controller.Home.*;
import com.example.gps_g11.Controller.Objetivo.ObjetivoController;

import com.example.gps_g11.Controller.Objetivo.adicionarObjetivoController;
import com.example.gps_g11.Controller.Objetivo.editarObjetivoController;
import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import com.example.gps_g11.Data.Context;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;

import java.time.LocalDate;

public class SideBarController {
    public DatePicker Date;
    public Button btnHomePage;
    public Button bntHistorico;
    public Button btnEnvelope;
    public Button btnObjetivos;
    public Button btnEstatistica;
    private Context context;
    public Pane ContentPane;

    private CategoriaDespesas c;
    private CategoriaEntradas c1;

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

    public void onEstatisticas(){ loadFXML("Estatisticas/EstatisticasDespesasGastoDia.fxml");}

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
                        if (c != null) {
                            envelopeVisualizarController.setCategoria(c);
                        }else{
                            envelopeVisualizarController.setCategoria(c1);
                        }
                        putBtnActive(btnEnvelope);
                        c = null;
                        c1 = null;
                        break;
                    case "Home/HomePageAdicionarDespesa.fxml":
                        HomePageAdicionarDespesaController homePageAdicionarDividaController = loader.getController();
                        homePageAdicionarDividaController.setSideBar(this);
                        putBtnActive(btnHomePage);
                        break;
                    case "Home/HomePageAdicionarSaldo.fxml":
                        HomePageAdicionarSaldoController homePageAdicionarSaldoController = loader.getController();
                        homePageAdicionarSaldoController.setSideBar(this);
                        putBtnActive(btnHomePage);
                        break;
                    case "Home/HomePageAdicionarBolsa.fxml":
                        HomePageAdicionarBolsaController homePageAdicionarBolsaController = loader.getController();
                        homePageAdicionarBolsaController.setSideBar(this);
                        putBtnActive(btnHomePage);
                        break;
                    case "Home/HomePageTransaction.fxml":
                        HomePageTransactionController homePageTransactionController = loader.getController();
                        homePageTransactionController.setSideBar(this);
                        putBtnActive(btnHomePage);
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
                    case "Estatisticas/EstatisticasDespesasGastoDia.fxml":
                        EstastiticasDespesaGastoDiaController estastiticasDespesaController = loader.getController();
                        estastiticasDespesaController.setSideBar(this);
                        putBtnActive(btnEstatistica);
                        break;
                    case "Estatisticas/EstatisticasEnvelope.fxml":
                        EstastiticasEnvelopeController estastiticasEnvelopeController = loader.getController();
                        estastiticasEnvelopeController.setSideBar(this);
                        putBtnActive(btnEstatistica);
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
        Date.setValue(context.getData());
        Date.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(context.getData())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
        Date.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Data alterada para: " + newValue);
            context.verficacoes(newValue);
        });

        //inicializar();
        onHomePage();
    }



    private void inicializar() {
        context.adicionarCategoriaEntrada("Mesada","Mesada");
        context.adicionarCategoriaEntrada("Bolsa","Bolsa");
        context.adicionarEntrada("Mesada","Deram me dinheiro", LocalDate.of(2023,10,2),50,true);
        context.adicionarEntrada("Bolsa","Mais algum dinheiro da escola", LocalDate.of(2023,10,3),150,false);
        context.adicionarEntrada("Mesada","Deram mais um dinheirinho ", LocalDate.of(2023,10,14),30,true);

        context.adicionarCategoriaDespesa(70,"Propinas","Propinas para pagar",true,true);
        context.adicionarCategoriaDespesa(30,"Compras","Dinheiro para compras",true,false);
        context.adicionarCategoriaDespesa(20,"Anti-depressivos","Dinheiro pré-defesas",true,true);

    }

    public void criarEnvelopesPane(){
        loadFXML("Envelope/EnvelopeCriaEnvelope.fxml");
    }
    public void adicionaDinheiroEnvelope(){loadFXML("Envelope/EnvelopeAdicionaDinheiro.fxml");}
    public void adicionarDespesa(){
        loadFXML("Home/HomePageAdicionarDespesa.fxml");
    }
    public void verEnvelope(CategoriaDespesas c){
        this.c = c;
        loadFXML("Envelope/EnvelopeVisualizar.fxml");
    }
    public void verCategoria(CategoriaEntradas c1){
        this.c1 = c1;
        loadFXML("Envelope/EnvelopeVisualizar.fxml");
    }

    public void adicionarSaldo() {
        loadFXML("Home/HomePageAdicionarSaldo.fxml");
    }
    public void adicionarBolsa() {
        loadFXML("Home/HomePageAdicionarBolsa.fxml");
    }

    public void adicionarObjetivos() { loadFXML("Objetivo/adicionarObjetivo.fxml");}

    //public void resetbudget(){loadFXML("Home/HomePageReset.fxml");}

    public void editarObjetivos() { loadFXML("Objetivo/editarObjetivo.fxml");}

    public void transaction() {
        loadFXML("Home/HomePageTransaction.fxml");
    }

    public void mudaestastiticasEnvelope(){loadFXML("Estatisticas/EstatisticasEnvelope.fxml");}
    public void mudaestastiticasDespesa(){loadFXML("Estatisticas/EstatisticasDespesasGastoDia.fxml");}

}


