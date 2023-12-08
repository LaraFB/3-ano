package com.example.gps_g11.Controller.Estatistica;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import com.example.gps_g11.Data.Context;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;

public class EstastiticasEnvelopeController {
    public Button btnBack;
    public ChoiceBox<?> cbenvelope;
    public PieChart cp_envelope;
    public DatePicker dataf;
    public DatePicker datai;
    private SideBarController sideBarController;
    private Context context;

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void onBack() {
        sideBarController.mudaestastiticasDespesa();
        //chartUpdate();
    }

/*    public void chartUpdate() {
        if (context.getCategoriasListEntradas().isEmpty())
            return;

        int i = 0;

        for (int j = 0; j < context.getCategoriasListEntradas().size(); j++, i++) {
            PieChart envelopeChartPie = createPie(context.getCategoriasListEntradas().get(j));
        }

        for (int j = 0; j < context.getCategoriasListEntradas().size(); j++) {
        }

        PieChart.Data totalValor = new PieChart.Data();

    }*/
/*
    private PieChart createPie(CategoriaEntradas o) {
        PieChart pie = new PieChart();
        PieChart.Data obtido = new PieChart.Data();
        pie.getData().add(obtido);

        pie.setLabelsVisible(false);
        pie.setTitle(o.getNome());

        pie.getStyleClass().add("chart-pie");
        pie.setPrefSize(200, 300);

        return pie;
    }*/
}