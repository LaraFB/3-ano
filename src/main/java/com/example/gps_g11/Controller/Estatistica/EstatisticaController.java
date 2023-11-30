package com.example.gps_g11.Controller.Estatistica;

import com.example.gps_g11.Controller.SideBarController;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;

public class EstatisticaController {
    public ChoiceBox<?> cbenvelope;
    public DatePicker dataf;
    public DatePicker datai;
    public LineChart<?, ?> graphicdespesas;
    public PieChart graphicenvelope;
    public BorderPane root;

    private SideBarController sideBarController;

    public void setSideBar(SideBarController sideBarController) {this.sideBarController = sideBarController;}

}


