package com.example.gps_g11.Controller.Estatistica;

import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Transacao.Despesa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import com.example.gps_g11.Controller.SideBarController;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EstastiticasDespesaGastoDiaController implements Initializable {
    public Button btnNext;
    public ChoiceBox<String> cbenvelope;
    public DatePicker dataf;
    public DatePicker datai;
    public LineChart<String, Number> graphicdespesas;
    public BorderPane root;
    public Button despesasdia, despesasgasto;
    public BorderPane hboxGraph;
    private SideBarController sideBarController;
    private Context context;

    private boolean BotaoDia = true;

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = Context.getInstance();
        if (!context.getTransacoesDespesa().isEmpty()) {
            datai.setValue(context.getTransacoesDespesa().get(0).getData());
            dataf.setValue(context.getTransacoesDespesa().get(context.getTransacoesDespesa().size() - 1).getData());
        }
        datai.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                boolean flag = false;
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

        datai.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (BotaoDia)
                OnPorDia();
            else
                OnTotalDespesas();
        });

        dataf.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                boolean flag = false;
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

        dataf.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (BotaoDia)
                OnPorDia();
            else
                OnTotalDespesas();
        });
        cbenvelope.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (BotaoDia)
                OnPorDia();
            else
                OnTotalDespesas();
        });

/*

        context.adicionarDespesa("Compras","asd",LocalDate.of(2023,12,10),5,true);
        context.adicionarDespesa("Compras","asd",LocalDate.of(2023,12,11),4,true);
        context.adicionarDespesa("Compras","asd",LocalDate.of(2023,12,13),4,true);
        context.adicionarDespesa("Compras","asd",LocalDate.of(2023,12,14),4,true);
        context.adicionarDespesa("Compras","asd",LocalDate.of(2023,12,14),5,true);
        context.adicionarDespesa("Compras","asd",LocalDate.of(2023,12,16),2,true);
        context.adicionarDespesa("Compras","asd",LocalDate.of(2023,12,18),1,true);
        context.adicionarDespesa("Compras","asd",LocalDate.of(2023,12,21),3,true);
        context.adicionarDespesa("Compras","asd",LocalDate.of(2023,12,25),3,true);
        context.adicionarDespesa("Propinas","asd",LocalDate.of(2023,12,13),3,true);
        context.adicionarDespesa("Propinas","asd",LocalDate.of(2023,12,27),4,true);
        context.adicionarDespesa("Anti-depressivos","asd",LocalDate.of(2023,12,13),4,true);
        context.adicionarDespesa("Anti-depressivos","asd",LocalDate.of(2023,12,26),4,true);


*/


        ObservableList<String> parametrosCategorias = FXCollections.observableArrayList(
                "Sem filtro"
        );
        parametrosCategorias.addAll(context.getCategoriaDespesasNomes());
        cbenvelope.setItems(parametrosCategorias);
        cbenvelope.setValue("Sem filtro");


        OnPorDia();

    }

    public void onNext() {
        sideBarController.mudaestastiticasEnvelope();
    }

    private LineChart<String, Number> createLineChart() {
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Values");

        ObservableList<XYChart.Series<String, Number>> lineChartData = FXCollections.observableArrayList();
        graphicdespesas = new LineChart<>(new CategoryAxis(), yAxis, lineChartData);
        graphicdespesas.setTitle("Date vs Values");

        graphicdespesas.setMaxHeight(500.0);
        graphicdespesas.setMaxWidth(850.0);
        graphicdespesas.setMinHeight(500.0);
        graphicdespesas.setMinWidth(850.0);
        graphicdespesas.setPrefHeight(500.0);
        graphicdespesas.setPrefWidth(850.0);

        return graphicdespesas;
    }

    public void OnPorDia() {
        BotaoDia = true;

        despesasdia.setStyle(" -fx-border-width: 0 0 3 0;" + "-fx-border-color: transparent transparent #80BDFF transparent;" + "-fx-background-color: #F4F9FF;");
        despesasgasto.setStyle("-fx-background-color: transparent;" + "-fx-font: 16 px;" + "-fx-font-family: \"Times New Roman\";");

        DateTimeFormatter FormatoData = DateTimeFormatter.ofPattern("dd/MM/yy");

        List<CategoriaDespesas> envelopes = context.getCategoriasListDespesas();
        List<Despesa> despesas = context.getTransacoesDespesa();

        LocalDate dateIni = null, dateFim = null;
        if (!despesas.isEmpty()) {
            dateIni = despesas.get(0).getData();
            if (datai.getValue() != null && datai.getValue().isAfter(dateIni)) {
                dateIni = datai.getValue();
            }

            dateFim = despesas.get(despesas.size() - 1).getData();
            if (dataf.getValue() != null && dataf.getValue().isBefore(dateFim)) {
                dateFim = dataf.getValue();
            }
        }

        // Limpa todas as séries do gráfico
        hboxGraph.setCenter(null);
        graphicdespesas = createLineChart();
        graphicdespesas.getXAxis().setLabel("Data");
        graphicdespesas.getYAxis().setLabel("Montante\n");
        hboxGraph.setCenter(graphicdespesas);

        graphicdespesas.setTitle("Despesas por dia");

        for (CategoriaDespesas envelope : envelopes) {
            if (!cbenvelope.getValue().equals("Sem filtro")) {
                if (!cbenvelope.getValue().equals(envelope.getNome())) {
                    continue;
                }
            }
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(envelope.getNome());

            LocalDate dataAtual = dateIni;
            while (dataAtual != null && !dataAtual.isAfter(dateFim)) {
                double montante = 0;
                for (Despesa despesa : despesas) {
                    if (despesa.getCategoria().equals(envelope) && despesa.getData().isEqual(dataAtual)) {
                        montante += despesa.getMontante();
                    }
                }
                String dataFormatada = dataAtual.format(FormatoData);
                series.getData().add(new XYChart.Data<>(dataFormatada, montante));
                dataAtual = dataAtual.plusDays(1);
            }
            graphicdespesas.getData().add(series);

            List<XYChart.Data<String, Number>> dataList = series.getData();
            for (int i = 1; i < dataList.size(); i++) {
                Number previousValue = dataList.get(i - 1).getYValue();
                Number currentValue = dataList.get(i).getYValue();

                if (previousValue.doubleValue() == currentValue.doubleValue()) {
                    XYChart.Data<String, Number> data = dataList.get(i);
                    data.setNode(null);
                }
            }

            for (XYChart.Data<String, Number> data : series.getData()) {
                Tooltip tooltip = new Tooltip("Data: " + data.getXValue() + "\nValor: " + data.getYValue());
                Tooltip.install(data.getNode(), tooltip);

                if (data.getNode() != null) {
                    data.getNode().setOnMouseReleased(event -> tooltip.hide());
                    data.getNode().setOnMousePressed(event -> tooltip.show(data.getNode(), event.getScreenX(), event.getScreenY()));
                }
            }
        }

    }

    public void OnTotalDespesas() {
        BotaoDia = false;
        despesasgasto.setStyle(" -fx-border-width: 0 0 3 0;\n" + "-fx-border-color: transparent transparent #80BDFF transparent;" + "-fx-background-color: #F4F9FF;");
        despesasdia.setStyle("-fx-background-color: transparent;" + "-fx-font: 16 px;\n" + "-fx-font-family: \"Times New Roman\";");
        DateTimeFormatter FormatoData = DateTimeFormatter.ofPattern("dd/MM/yy");

        List<CategoriaDespesas> envelopes = context.getCategoriasListDespesas();
        List<Despesa> despesas = context.getTransacoesDespesa();

        LocalDate dateIni = null, dateFim = null;
        if (!despesas.isEmpty()) {
            dateIni = despesas.get(0).getData();
            if (datai.getValue() != null && datai.getValue().isAfter(dateIni)) {
                dateIni = datai.getValue();
            }

            dateFim = despesas.get(despesas.size() - 1).getData();
            if (dataf.getValue() != null && dataf.getValue().isBefore(dateFim)) {
                dateFim = dataf.getValue();
            }
        }

        // Limpa todas as séries do gráfico
        hboxGraph.setCenter(null);
        graphicdespesas = createLineChart();
        graphicdespesas.getXAxis().setLabel("Data");
        graphicdespesas.getYAxis().setLabel("Montante\n");
        hboxGraph.setCenter(graphicdespesas);

        graphicdespesas.setTitle("Evolução das despesas");
        for (CategoriaDespesas envelope : envelopes) {
            if (!cbenvelope.getValue().equals("Sem filtro")) {
                if (!cbenvelope.getValue().equals(envelope.getNome())) {
                    continue;
                }
            }
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(envelope.getNome());
            double montante = 0;

            LocalDate dataAtual = dateIni;
            while (dataAtual != null && !dataAtual.isAfter(dateFim)) {
                for (Despesa despesa : despesas) {
                    if (despesa.getCategoria().equals(envelope) && despesa.getData().isEqual(dataAtual)) {
                        montante += despesa.getMontante();
                    }
                }
                String dataFormatada = dataAtual.format(FormatoData);
                series.getData().add(new XYChart.Data<>(dataFormatada, montante));
                dataAtual = dataAtual.plusDays(1);
            }
            graphicdespesas.getData().add(series);

            List<XYChart.Data<String, Number>> dataList = series.getData();
            for (int i = 1; i < dataList.size(); i++) {
                Number previousValue = dataList.get(i - 1).getYValue();
                Number currentValue = dataList.get(i).getYValue();

                if (previousValue.doubleValue() == currentValue.doubleValue()) {
                    XYChart.Data<String, Number> data = dataList.get(i);
                    data.setNode(null);
                }
            }

            for (XYChart.Data<String, Number> data : series.getData()) {
                Tooltip tooltip = new Tooltip("Data: " + data.getXValue() + "\nValor: " + data.getYValue());
                Tooltip.install(data.getNode(), tooltip);

                if (data.getNode() != null) {
                    data.getNode().setOnMouseReleased(event -> tooltip.hide());
                    data.getNode().setOnMousePressed(event -> tooltip.show(data.getNode(), event.getScreenX(), event.getScreenY()));
                }
            }
        }

    }
}


    /*public void chartUpdate() {

        List<CategoriaDespesas> envelopes = context.getCategoriasListDespesas();
        System.out.println(envelopes);
        List<Despesa> despesas = context.getTransacoesDespesa();
        System.out.println(despesas);
        LocalDate dateIni = null, dateFim = null;
        if (!despesas.isEmpty()) {
            dateIni = despesas.get(0).getData();
            if (datai.getValue() != null && datai.getValue().isAfter(dateIni)) {
                dateIni = datai.getValue();
            }
            dateFim = despesas.get(despesas.size()-1).getData();
            if (dataf.getValue() != null && dataf.getValue().isBefore(dateFim)) {
                dateFim = dataf.getValue();
            }
        }
        graphicdespesas.getData().clear();

        for (CategoriaDespesas envelope : envelopes) {

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(envelope.getNome());
            int temp = 0;
            double montante = 0;
            LocalDate dateAtual = null;
            LocalDate dateRepeat = null;
            for (Despesa despesa : despesas) {
                if (despesa.getCategoria().equals(envelope)) {
                    if (datai.getValue() != null && datai.getValue().isAfter(despesa.getData())) {
                        continue;
                    }
                    if (dataf.getValue() != null && dataf.getValue().isBefore(despesa.getData())) {
                        continue;
                    }

                    if (temp == 0) {
                        dateAtual = dateIni;
                        while (!dateAtual.equals(despesa.getData())) {
                            series.getData().add(new XYChart.Data<>(dateAtual.toString(), montante));
                            dateAtual = dateAtual.plusDays(1);
                        }
                        dateAtual = despesa.getData();
                    }
                    while (!dateAtual.equals(despesa.getData())) {
                        series.getData().add(new XYChart.Data<>(dateAtual.toString(), montante));
                        dateAtual = dateAtual.plusDays(1);
                    }
                    if (dateRepeat != null && dateRepeat.equals(dateAtual)) {
                        series.getData().remove(series.getData().size() - 1);
                    }
                    montante += despesa.getMontante();
                    series.getData().add(new XYChart.Data<>(dateAtual.toString(), montante));

                    temp++;
                    dateRepeat = dateAtual;
                }
            }

            while (dateAtual != null && !dateAtual.equals(dateFim)) {
                series.getData().add(new XYChart.Data<>(dateAtual.toString(), montante));
                dateAtual = dateAtual.plusDays(1);
            }

            graphicdespesas.getData().add(series);
            List<XYChart.Data<String, Number>> dataList = series.getData();
            for (int i = 1; i < dataList.size(); i++) {
                Number previousValue = dataList.get(i - 1).getYValue();
                Number currentValue = dataList.get(i).getYValue();

                if (previousValue.doubleValue() == currentValue.doubleValue() && i + 1 != dataList.size() && currentValue.doubleValue() == dataList.get(i + 1).getYValue().doubleValue()) {
                    XYChart.Data<String, Number> data = dataList.get(i);
                    data.setNode(null);
                }
            }
            for (XYChart.Data<String, Number> data : series.getData()) {
                Tooltip tooltip = new Tooltip("Data: " + data.getXValue() + "\nValor: " + data.getYValue());
                Tooltip.install(data.getNode(), tooltip);
                if (data.getNode() != null) {
                    data.getNode().setOnMouseReleased(event -> tooltip.hide());
                    data.getNode().setOnMousePressed(event -> tooltip.show(data.getNode(), event.getScreenX(), event.getScreenY()));
                }
            }
            *//*for (XYChart.Data<String, Number> data : series.getData()) {
                data.setNode(null);
            }*//*

        }
    }*/
//}


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