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
import javafx.scene.control.DateCell;
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

        dataf.valueProperty().addListener((observable, oldValue, newValue) -> {
            chartUpdate();
        });

        dataf.valueProperty().addListener((observable, oldValue, newValue) -> {
            chartUpdate();
        });
        cbenvelope.setVisible(false);
        handlers();
    }

    private void handlers() {
        if(isEnvelopes){
            if (!context.getTransacoesDespesa().isEmpty()) {
                datai.setValue(context.getTransacoesDespesa().get(0).getData());
                dataf.setValue(context.getTransacoesDespesa().get(context.getTransacoesDespesa().size() - 1).getData());
            }
        }else{
            if (!context.getTransacoesEntrada().isEmpty()) {
                datai.setValue(context.getTransacoesEntrada().get(0).getData());
                dataf.setValue(context.getTransacoesEntrada().get(context.getTransacoesEntrada().size() - 1).getData());
            }
        }
        datai.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                boolean flag = false;
                if(isEnvelopes){
                    if (!context.getTransacoesDespesa().isEmpty()) {
                        dataPickerDespesa(localDate);
                        flag = true;
                    } else {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                    if (flag && localDate.isAfter(dataf.getValue())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }else{
                    if (!context.getTransacoesEntrada().isEmpty()) {
                        dataPickerEntrada(localDate);
                        flag = true;
                    } else {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                    if (flag && localDate.isAfter(dataf.getValue())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }

            }
            private void dataPickerEntrada(LocalDate localDate) {
                if(localDate.isBefore(context.getTransacoesEntrada().get(0).getData())){
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
                if(localDate.isAfter(context.getTransacoesEntrada().get(context.getTransacoesEntrada().size()-1).getData())){
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }

            }
            private void dataPickerDespesa(LocalDate localDate) {
                if (localDate.isBefore(context.getTransacoesDespesa().get(0).getData())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
                if (localDate.isAfter(context.getTransacoesDespesa().get(context.getTransacoesDespesa().size() - 1).getData())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }

        });

        dataf.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                boolean flag = false;
                if(isEnvelopes){
                    if (!context.getTransacoesDespesa().isEmpty()) {
                        dataPickerDespesa(localDate);
                        flag = true;
                    } else {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }

                    if (flag && localDate.isBefore(datai.getValue())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }else{
                    if (!context.getTransacoesEntrada().isEmpty()) {
                        dataPickerEntrada(localDate);
                        flag = true;
                    } else {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }

                    if (flag && localDate.isBefore(datai.getValue())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }

            }
            private void dataPickerEntrada(LocalDate localDate) {
                if(localDate.isBefore(context.getTransacoesEntrada().get(0).getData())){
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
                if(localDate.isAfter(context.getTransacoesEntrada().get(context.getTransacoesEntrada().size()-1).getData())){
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }

            }
            private void dataPickerDespesa(LocalDate localDate) {
                if (localDate.isBefore(context.getTransacoesDespesa().get(0).getData())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
                if (localDate.isAfter(context.getTransacoesDespesa().get(context.getTransacoesDespesa().size() - 1).getData())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
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