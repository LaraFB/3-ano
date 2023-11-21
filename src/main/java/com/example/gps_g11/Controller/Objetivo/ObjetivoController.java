package com.example.gps_g11.Controller.Objetivo;

import com.example.gps_g11.Controller.SideBarController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;

public class ObjetivoController {
    public Button addObj;

    public BorderPane root;

    public PieChart graph;
    public PieChart graph1;
    public PieChart graph2;
    public PieChart graph3;
    public PieChart graph4;
    public PieChart graph5;

    private SideBarController sideBarController;

    public void setSideBar(SideBarController sideBarController) {this.sideBarController = sideBarController;}

    public void onAddObjective(ActionEvent actionEvent) {sideBarController.adicionarObjetivos();}
}
