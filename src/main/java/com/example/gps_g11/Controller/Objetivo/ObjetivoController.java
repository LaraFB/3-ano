package com.example.gps_g11.Controller.Objetivo;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Objetivo.ListaObjetivos;
import com.example.gps_g11.Data.Objetivo.Objetivo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import com.example.gps_g11.Data.ToDos.ToDo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

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
            distribui2();

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
                //objetivosPie.setOnMouseClicked(mouseEvent -> eliminar(finalJ));
                if(context.getListaObjetivos().getObjetivo(j).isDone()){
                    objetivosPie.setTitle("Objetivo pago!");
                }else{
                    objetivosPie.setTitle("Objetivo cumprido!");
                }

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

        pieEmfalta.getData().clear();
        pieEmfalta.getData().add(totalValor);
        pieEmfalta.getData().add(totalEmFalta);
        pieEmfalta.setLabelsVisible(false);

        pieObtido.getData().clear();
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

    private void distribui2(){
        double valor_envelope = context.getCategoriaByName("Objetivos").getValor();
        double valor_retirar = 0;

        boolean flag = false;
        for (int i = 0; i < context.getListaObjetivos().getSize(); i++) {
            if(!context.getListaObjetivos().getObjetivo(i).isDone()){
                valor_envelope = valor_envelope - context.getListaObjetivos().getObjetivo(i).getCurrentValue();
            }
        }
        if(valor_envelope > 0) flag = true;
        if(context.getListaObjetivos().getSize() <= 0) flag = false;

        while(flag){
            context.getListaObjetivos().sort(context.getData());
            List<Objetivo> objetivosNaoCumpridos = new ArrayList<>();
            for (int i = 0; i < context.getListaObjetivos().getSize(); i++) {
                if(!context.getListaObjetivos().getObjetivo(i).isFullfiled() && !context.getListaObjetivos().getObjetivo(i).isDone()){
                    objetivosNaoCumpridos.add(context.getListaObjetivos().getObjetivo(i));
                }
            }

            /*for (int i=0; i< context.getListaObjetivos().getSize(); i++){
                if(i == context.getListaObjetivos().getSize()-1){
                    valor_retirar = valor_envelope;
                    if(context.getListaObjetivos().getObjetivo(context.getListaObjetivos().getSize()-1).getMissingValue() < valor_retirar)
                        valor_retirar = context.getListaObjetivos().getObjetivo(context.getListaObjetivos().getSize()-1).getMissingValue();

                    context.getListaObjetivos().getObjetivo(context.getListaObjetivos().getSize()-1).addToGoal(valor_retirar);
                    valor_envelope -=valor_retirar;
                    context.getCategoriaByName("Objetivos").setValor(valor_envelope);
                }else{
                    valor_retirar = valor_envelope/2;
                    if(context.getListaObjetivos().getObjetivo(i).getMissingValue() < valor_retirar)
                        valor_retirar = context.getListaObjetivos().getObjetivo(i).getMissingValue();

                    context.getListaObjetivos().getObjetivo(i).addToGoal(valor_retirar);
                    valor_envelope -=valor_retirar;
                    context.getCategoriaByName("Objetivos").setValor(valor_envelope);
                }
            }*/
            for (int i=0; i< objetivosNaoCumpridos.size(); i++){
                if(i == objetivosNaoCumpridos.size()-1){
                    valor_retirar = valor_envelope;
                    if(objetivosNaoCumpridos.get(objetivosNaoCumpridos.size()-1).getMissingValue() < valor_retirar)
                        valor_retirar = objetivosNaoCumpridos.get(objetivosNaoCumpridos.size()-1).getMissingValue();

                    objetivosNaoCumpridos.get(objetivosNaoCumpridos.size()-1).addToGoal(valor_retirar);
                    valor_envelope -=valor_retirar;
                }else{
                    valor_retirar = valor_envelope/2;
                    if(objetivosNaoCumpridos.get(i).getMissingValue() < valor_retirar)
                        valor_retirar = objetivosNaoCumpridos.get(i).getMissingValue();

                    objetivosNaoCumpridos.get(i).addToGoal(valor_retirar);
                    valor_envelope -=valor_retirar;
                }
            }
            //ultimo obj


            flag = false;
            if(valor_envelope > 0){
                if(objetivosNaoCumpridos.size() > 0)
                    for (int i=0; i< objetivosNaoCumpridos.size()   ; i++)
                        if(!objetivosNaoCumpridos.get(i).isFullfiled())
                            flag = true; //continua
            }

            //se chega ao fim do for com a flag a false é pq estao tds cumpridos ou n ha dinheiro
        }
        verificaoObjetivoCompleto();
    }

    private void distribuiDinheiro(){
        double dinheiro = 0;
        double oldVlaue = 0;
        double valorNosObejtivos = 0;
        double valorQueFaltaNosObjetivs = 0;
        for (int i = 0; i < context.getListaObjetivos().getSize(); i++) {
            if(!context.getListaObjetivos().getObjetivo(i).isDone()){
                //System.out.println(context.getListaObjetivos().getObjetivo(i).getCurrentValue());
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
                    dinheiro -= valorARetirar;
                    context.getCategoriaByName("Objetivos").setValor(dinheiro);
                }else{
                    if (context.getListaObjetivos().getObjetivo(i).getMissingValue() < dinheiro / 2)
                        valorARetirar = context.getListaObjetivos().getObjetivo(i).getMissingValue();
                    else valorARetirar = dinheiro / 2;
                    oldVlaue += valorARetirar;
                    context.getListaObjetivos().getObjetivo(i).addToGoal(valorARetirar);
                    dinheiro -= valorARetirar;
                    context.getCategoriaByName("Objetivos").setValor(dinheiro);
                }
            }
            if(dinheiro == 0.0){
                break;
            }
            //System.out.println(oldVlaue);
            count++;
        }

        dinheiro = context.getCategoriasListDespesas().get(envelopeObjetivos).getValor();
        context.getListaObjetivos().getObjetivo(context.getListaObjetivos().getSize()-1).addToGoal(dinheiro);
        valorNosObejtivos -= dinheiro;
        context.getCategoriaByName("Objetivos").setValor(valorNosObejtivos);
        verificaoObjetivoCompleto();
    }
    private void verificaoObjetivoCompleto() {
        for(int i = 0; i < context.getListaObjetivos().getSize();i++){
            if(context.getListaObjetivos().getObjetivo(i).isFullfiled() && !context.getListaObjetivos().getObjetivo(i).isDone()){
                context.getListaNotificacoes().addToDo("Concluio o objetivo - " + context.getListaObjetivos().getObjetivo(i).getNome(), ToDo.TYPE.USER_GENERATED,"Objetivos",context.getListaObjetivos().getObjetivo(i));
            }
        }
    }

    private void eliminar(int index){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setTitle("Objetivo cumprido");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label desc = new Label("Deseja pagar e eliminar o objetivo?");
        desc.setStyle("-fx-font-size: 12px;-fx-font-family: 'Times New Roman';");

        Label ldinheiro = new Label("..em dinheiro?");
        CheckBox isDinheiro = new CheckBox();

        grid.add(desc, 0, 0);
        grid.add(ldinheiro,0,1);
        grid.add(isDinheiro,1,1);

        alert.getDialogPane().setContent(grid);

        ButtonType buttonSim = new ButtonType("Sim");
        ButtonType buttonNao = new ButtonType("Não");

        alert.getButtonTypes().setAll(buttonSim, buttonNao);

        alert.getDialogPane().lookupButton(buttonSim).setStyle("-fx-background-color:#92d0ff;-fx-font-family: 'Times New Roman';");
        alert.getDialogPane().lookupButton(buttonNao).setStyle("-fx-background-color:#ff676a;-fx-font-family: 'Times New Roman';");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonSim){
            try{
                context.adicionarCategoriaDespesa(context.getListaObjetivos().getObjetivo(index).getValor(),context.getListaObjetivos().getObjetivo(index).getNome(),context.getListaObjetivos().getObjetivo(index).getDescricao(),true,false);
                int check = context.adicionarDespesa(context.getListaObjetivos().getObjetivo(index).getNome(),"Objetivo " + context.getListaObjetivos().getObjetivo(index).getNome() + " cumprido",LocalDate.now(),context.getListaObjetivos().getObjetivo(index).getValor(), isDinheiro.isSelected());
                context.getCategoriasListDespesas().remove(context.getCategoriaByName(context.getListaObjetivos().getObjetivo(index).getNome()));

                //System.out.println(check);
                if(check >= 0){
                    context.getListaObjetivos().deleteObjetivo(index);
                    context.getListaObjetivos().sort(context.getData());
                    update();
                }
                else{
                    Alert err = new Alert(Alert.AlertType.ERROR);
                    err.setTitle("Falha ao pagar e eliminar objetivo.");
                    err.setGraphic(null);
                    err.setHeaderText("Verifique se tem dinheiro para o pagar...");
                    err.showAndWait();
                }

            }catch (Exception e){
                Alert err = new Alert(Alert.AlertType.ERROR);
                err.setTitle("Falha ao eliminar objetivo.");
                err.setGraphic(null);
                err.setHeaderText(null);
                err.showAndWait();


            }
        }
        update();
    }
}
