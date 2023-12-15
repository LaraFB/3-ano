package com.example.gps_g11.Controller.Objetivo;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Objetivo.Objetivo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import com.example.gps_g11.Data.ToDos.ToDo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
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

    private int envelopeObjetivos = -1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.context = Context.getInstance();

        for(int i=0; i<context.getCategoriasListDespesas().size(); i++)
            if(context.getCategoriasListDespesas().get(i).getNome().toLowerCase().contains("objetivo")){
                envelopeObjetivos = i;
                break;
            }

        update();
    }

    public void setSideBar(SideBarController sideBarController) {this.sideBarController = sideBarController;}

    public void onAddObjective(ActionEvent actionEvent) {sideBarController.adicionarObjetivos(); update();}
    public void onEditObjective(ActionEvent actionEvent) {sideBarController.editarObjetivos(); update();}

    public void update(){
        if(context.getListaObjetivos().isEmpty())
            return;

        if(envelopeObjetivos != -1 && !context.getListaObjetivos().isEmpty())
            distribuiDinheiro();

        int i = 0;
        int buttonsPerHBox = 4;
        vBoxObjetivos.getChildren().clear();
        HBox currentHBox = null;

        for (int j=0; j<context.getListaObjetivos().getSize(); j++) {
            if (i % buttonsPerHBox == 0) {
                currentHBox = new HBox();
                currentHBox.setSpacing(7);
                vBoxObjetivos.getChildren().add(currentHBox);
            }

            PieChart objetivosPie = createPie(context.getListaObjetivos().getObjetivo(j));
            if(context.getListaObjetivos().getObjetivo(j).isFullfiled()) {
                int finalJ = j;
                objetivosPie.setOnMouseClicked(mouseEvent -> eliminar(finalJ));
                objetivosPie.setTitle("Objetivo cumprido!");
            }
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
        PieChart.Data totalValor2 = new PieChart.Data("Dinheiro necessário", somaValorTotal);
        PieChart.Data totalObtido = new PieChart.Data("Total guardado", somaObtido);
        PieChart.Data totalEmFalta = new PieChart.Data("Total em falta", somaEmFalta);


        pieEmfalta.getData().add(totalValor);
        pieEmfalta.getData().add(totalEmFalta);
        pieEmfalta.setLabelsVisible(false);

        pieObtido.getData().add(totalValor2);
        pieObtido.getData().add(totalObtido);
        pieObtido.setLabelsVisible(false);
    }

    private PieChart createPie(Objetivo o) {
        PieChart pie = new PieChart();
        PieChart.Data obtido = new PieChart.Data("Guardado", o.getCurrentValue());
        PieChart.Data emFalta = new PieChart.Data("Em falta", o.getMissingValue());

        pie.getData().add(obtido);
        pie.getData().add(emFalta);

        pie.setLabelsVisible(false);
        pie.setTitle(o.getNome());

        pie.getStyleClass().add("chart-pie");
        pie.setPrefSize(200,300);

        return pie;
    }

    private void distribuiDinheiro(){
        double dinheiro = 0;
        double oldVlaue = 0;
        double valorNosObejtivos = 0;
        double valorQueFaltaNosObjetivs = 0;
        for (int i = 0; i < context.getListaObjetivos().getSize(); i++) {
            if(!context.getListaObjetivos().getObjetivo(i).isDone()){
                System.out.println(context.getListaObjetivos().getObjetivo(i).getCurrentValue());
                valorNosObejtivos += context.getListaObjetivos().getObjetivo(i).getCurrentValue();
                valorQueFaltaNosObjetivs += context.getListaObjetivos().getObjetivo(i).getMissingValue();
            }
        }
        context.getListaObjetivos().sort(context.getData());
        int count = 0;
        /*valor nos objetivos 0
        * valor que falta nos obejtivos 3
        * valor que os objetivos têm 2*/

        /*valor nos objetivos 0
         * valor que falta nos obejtivos 11
         * valor que os obejtivos têm 10*/

        /*valor nos objetivos 10
         * valor que falta nos obejtivos 1
         * valor que os obejtivos têm 10*//*
        System.out.println(valorNosObejtivos);
        System.out.println(context.getCategoriaByName("Objetivos").getValor());*/
        while(valorNosObejtivos != context.getCategoriaByName("Objetivos").getValor() && oldVlaue != context.getCategoriaByName("Objetivos").getValor() && context.getCategoriasListDespesas().get(envelopeObjetivos).getValor()>context.getCategoriaByName("Objetivos").getOldValue() && !context.getListaObjetivos().isAllFullfiled()){
            for (int i = 0; i < context.getListaObjetivos().getSize() ; i++) {
                double valorARetirar;
                if(oldVlaue == context.getCategoriaByName("Objetivos").getValor()){
                    break;
                }
                dinheiro = context.getCategoriasListDespesas().get(envelopeObjetivos).getValor()-oldVlaue;
                if(!context.getListaObjetivos().isFullfiled(i) && count != 0 && context.getCategoriasListDespesas().get(envelopeObjetivos).getValor() != 0.0){
                    if (context.getListaObjetivos().getObjetivo(i).getMissingValue() < dinheiro)
                        valorARetirar = context.getListaObjetivos().getObjetivo(i).getMissingValue();
                    else valorARetirar = dinheiro;
                    oldVlaue += valorARetirar;
                    context.getListaObjetivos().getObjetivo(i).addToGoal(valorARetirar);
                }else{
                    if (context.getListaObjetivos().getObjetivo(i).getMissingValue() < dinheiro / 2)
                        valorARetirar = context.getListaObjetivos().getObjetivo(i).getMissingValue();
                    else valorARetirar = dinheiro / 2;
                    oldVlaue += valorARetirar;
                    context.getListaObjetivos().getObjetivo(i).addToGoal(valorARetirar);
                }
            }
            if(dinheiro == 0.0){
                break;
            }
            System.out.println(oldVlaue);
            count++;
        }

        dinheiro = context.getCategoriasListDespesas().get(envelopeObjetivos).getValor();
        context.getListaObjetivos().getObjetivo(context.getListaObjetivos().getSize()-1).addToGoal(dinheiro);
        context.getCategoriaByName("Objetivos").setOldValue(oldVlaue);
        verificaoObjetivoCompleto();
    }
    private void verificaoObjetivoCompleto() {
        for(int i = 0; i < context.getListaObjetivos().getSize();i++){
            if(context.getListaObjetivos().getObjetivo(i).isFullfiled() && !context.getListaObjetivos().getObjetivo(i).isDone()){
                context.getListaNotificacoes().addToDo("Concluio o objetivo - " + context.getListaObjetivos().getObjetivo(i).getNome(), ToDo.TYPE.USER_GENERATED,"Objetivos",context.getListaObjetivos().getObjetivo(i).getValor( ));
            }
        }
    }

    private void eliminar(int index){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Objetivo cumprido!");
        alert.setGraphic(null);
        alert.setHeaderText("Deseja eliminar este objetivo?");

        alert.getButtonTypes().setAll(ButtonType.YES,ButtonType.NO);

        Optional<ButtonType> a = alert.showAndWait();
        if(a.get() == ButtonType.YES){
            try{
                context.getListaObjetivos().deleteObjetivo(index);
                context.getListaObjetivos().sort(context.getData());
                update();
            }catch (Exception e){
                Alert err = new Alert(Alert.AlertType.ERROR);
                err.setTitle("Falha ao eliminar objetivo.");
                err.setGraphic(null);
                err.setHeaderText(null);
            }
        }
    }
}
