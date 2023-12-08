package com.example.gps_g11.Controller.Estatistica;

import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import com.example.gps_g11.Data.Context;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import com.example.gps_g11.Controller.SideBarController;

public class EstastiticasDespesaController {
    public Button btnNext;
    public ChoiceBox<?> cbenvelope;
    public DatePicker dataf;
    public DatePicker datai;
    public LineChart<?, ?> graphicdespesas;
    private SideBarController sideBarController;
    private Context context;

    public void setSideBar(SideBarController sideBarController) {this.sideBarController = sideBarController;}

    public void onNext(){
        sideBarController.mudaestastiticasEnvelope();
        chartUpdate();
    }
    public void chartUpdate() {

    }}
/*
    public void generateChart () {
        for (int i = 0; i < despesas.size(); i++) {
            XYChart.Series series = new XYChart.Series();
            series.setName(despesas.get(i).toString());
            for (int j = 0; j < parameters.size(); j++) {
                series.getData().add(new XYChart.Data(parameters.get(j).toString(), param.get(i).get(j)));
            }
            graphicdespesas.getData().add(series);
        }
    }
}*/
/*  NumberAxis xAxis = new NumberAxis(1960, 2020, 10);
    xAxis.setLabel("Meses");
    NumberAxis yAxis = new NumberAxis(0, 350, 50);
    yAxis.setLabel("Total Despesas);
    LineChart linechart = new LineChart(xAxis, yAxis);
    XYChart.Series series = new XYChart.Series();
    series.setName("No of schools in an year");
    series.getData().add(new XYChart.Data(1970, 15));

    linechart.getData().add(series);

    XYChart.Series series2 = new XYChart.Series();
    series2.getData().add(new XYChart.Data("Jan", -0.2));

    XYChart.Series series3 = new XYChart.Series();
    series3.getData().add(new XYChart.Data("Jan", 3.9));
    */