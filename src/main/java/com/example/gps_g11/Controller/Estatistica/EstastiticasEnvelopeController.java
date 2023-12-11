package com.example.gps_g11.Controller.Estatistica;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Transacao.Despesa;
import com.example.gps_g11.Data.Transacao.Entrada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstastiticasEnvelopeController {
    public Button btnBack;
    public ChoiceBox<?> cbenvelope;
    public PieChart cp_envelope;
    public DatePicker dataf;
    public DatePicker datai;
    public Button categoriasEntradas;
    public Button envelopes;
    private SideBarController sideBarController;
    private Context context;
    private boolean isEnvelopes = true;
    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void onBack() {
        sideBarController.mudaestastiticasDespesa();
        chartUpdate();
    }
    public void initialize(){
        context = Context.getInstance();
        chartUpdate();
        cbenvelope.setVisible(false);
        datai.valueProperty().addListener((observable, oldValue, newValue) -> {
            chartUpdate();
        });

        dataf.valueProperty().addListener((observable, oldValue, newValue) -> {
            chartUpdate();
        });
    }

    public void chartUpdate() {
        initializePieChart();
    }
    private void initializePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        LocalDate dataInicial = datai.getValue();
        LocalDate dataFinal = dataf.getValue();
        if(isEnvelopes){
            envelopes.setStyle(" -fx-border-width: 0 0 3 0;" + "-fx-border-color: transparent transparent #80BDFF transparent;" + "-fx-background-color: #F4F9FF;");
            categoriasEntradas.setStyle("-fx-background-color: transparent;" + "-fx-font: 16 px;" + "-fx-font-family: \"Times New Roman\";");
            cp_envelope.setTitle("Envelopes");

            double totalDespesas = 0;
            for (Despesa despesa : context.getTransacoesDespesa()) {
                LocalDate dataTransacao = despesa.getData();
                if ((dataInicial == null || dataTransacao.isAfter(dataInicial) || dataTransacao.isEqual(dataInicial)) &&
                        (dataFinal == null || dataTransacao.isBefore(dataFinal) || dataTransacao.isEqual(dataFinal))) {

                    totalDespesas += despesa.getMontante();
                }
            }

            for (CategoriaDespesas categoriasListDespesa : context.getCategoriasListDespesas()) {
                double montante = 0;
                for (Despesa despesa : context.getTransacoesDespesa()) {
                    LocalDate dataTransacao = despesa.getData();
                    if ((dataInicial == null || dataTransacao.isAfter(dataInicial) || dataTransacao.isEqual(dataInicial)) &&
                            (dataFinal == null || dataTransacao.isBefore(dataFinal) || dataTransacao.isEqual(dataFinal)) &&
                            despesa.getCategoria().equals(categoriasListDespesa)) {
                        montante += despesa.getMontante();
                    }
                }
                double percentual = (montante / totalDespesas) * 100;
                pieChartData.add(new PieChart.Data(categoriasListDespesa.getNome() + " (" + String.format("%.2f", percentual) + "%)", montante));
            }
        }else{
            categoriasEntradas.setStyle(" -fx-border-width: 0 0 3 0;" + "-fx-border-color: transparent transparent #80BDFF transparent;" + "-fx-background-color: #F4F9FF;");
            envelopes.setStyle("-fx-background-color: transparent;" + "-fx-font: 16 px;" + "-fx-font-family: \"Times New Roman\";");
            cp_envelope.setTitle("Categorias de Entrada");
            double totalEntradas = 0;
            for (Entrada entrada : context.getTransacoesEntrada()) {
                LocalDate dataTransacao = entrada.getData();
                if ((dataInicial == null || dataTransacao.isAfter(dataInicial) || dataTransacao.isEqual(dataInicial)) &&
                        (dataFinal == null || dataTransacao.isBefore(dataFinal) || dataTransacao.isEqual(dataFinal))) {
                    totalEntradas += entrada.getMontante();
                }
            }
            for (CategoriaEntradas categoriaEntradas : context.getCategoriasListEntradas()) {
                double montante = 0;
                for (Entrada entrada : context.getTransacoesEntrada()) {
                    LocalDate dataTransacao = entrada.getData();
                    if ((dataInicial == null || dataTransacao.isAfter(dataInicial) || dataTransacao.isEqual(dataInicial)) &&
                            (dataFinal == null || dataTransacao.isBefore(dataFinal) || dataTransacao.isEqual(dataFinal)) &&
                            entrada.getCategoria().equals(categoriaEntradas)) {
                        montante += entrada.getMontante();
                    }
                }
                double percentual = (montante / totalEntradas) * 100;
                pieChartData.add(new PieChart.Data(categoriaEntradas.getNome() + " (" + String.format("%.2f", percentual) + "%)", montante));
            }
        }
        cp_envelope.setData(pieChartData);
    }

    public void onEnvelopes(ActionEvent actionEvent) {
        isEnvelopes = true;
        chartUpdate();
    }

    public void onCategoriaEntrada(ActionEvent actionEvent) {
        isEnvelopes = false;
        chartUpdate();
    }
}