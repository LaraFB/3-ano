package com.example.gps_g11.Controller.Objetivo;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Categoria.Categoria;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Objetivo.Objetivo;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ObjetivoController implements Initializable {
    public Button addObj;

    public BorderPane root;

    @FXML
    private PieChart pieObtido;
    @FXML
    private PieChart pieEmfalta;

    private Context context;

    @FXML
    private ScrollPane scObjetivos;
    @FXML
    private VBox vBoxObjetivos;

    private SideBarController sideBarController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.context = Context.getInstance();
        update();
    }

    public void setSideBar(SideBarController sideBarController) {this.sideBarController = sideBarController;}

    public void onAddObjective(ActionEvent actionEvent) {sideBarController.adicionarObjetivos(); update();}
    public void onEditObjective(ActionEvent actionEvent) {sideBarController.editarObjetivos(); update();}

    public void update(){
        if(context.getListaObjetivos().isEmpty())
            return;

        int i = 0;
        int buttonsPerHBox = 3;
        vBoxObjetivos.getChildren().clear();
        HBox currentHBox = null;

        for (int j=0; j<context.getListaObjetivos().getSize(); j++) {
            if (i % buttonsPerHBox == 0) {
                currentHBox = new HBox();
                currentHBox.setSpacing(7);
                vBoxObjetivos.getChildren().add(currentHBox);
            }

            PieChart objetivosPie = createPie(context.getListaObjetivos().getObjetivo(j));
            currentHBox.getChildren().add(objetivosPie);

            i++;
        }

        Double somaValorTotal = 0.0;
        Double somaObtido = 0.0;
        Double somaEmFalta = 0.0;

        for (int j=0; j<context.getListaObjetivos().getSize(); j++) {
            somaValorTotal += context.getListaObjetivos().getObjetivo(j).getValor();
            somaObtido += context.getListaObjetivos().getObjetivo(j).getCurrentValue();
            somaEmFalta += context.getListaObjetivos().getObjetivo(j).getMissingValue();
        }

        PieChart.Data totalValor = new PieChart.Data("Dinheiro necessário", somaValorTotal);
        PieChart.Data totalObtido = new PieChart.Data("Dinheiro total obtido", somaObtido);
        PieChart.Data totalEmFalta = new PieChart.Data("Dinheiro total que falta", somaEmFalta);

        pieEmfalta.getData().add(totalValor);
        pieEmfalta.getData().add(totalEmFalta);
        pieEmfalta.setLabelsVisible(false);

        pieObtido.getData().add(totalValor);
        pieObtido.getData().add(totalObtido);
        pieObtido.setLabelsVisible(false);
    }

    private PieChart createPie(Objetivo o) {
        PieChart pie = new PieChart();
        PieChart.Data obtido = new PieChart.Data("Dinheiro obtido", o.getCurrentValue());
        PieChart.Data emFalta = new PieChart.Data("Dinheiro que falta", o.getMissingValue());

        pie.getData().add(obtido);
        pie.getData().add(emFalta);

        pie.setLabelsVisible(false);
        pie.setLegendVisible(true);
        pie.setTitle(o.getNome());

        pie.getStyleClass().add("chart-pie");

        return pie;
    }


}
