package com.example.gps_g11.Controller.Budget;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Budget.Envelope;
import com.example.gps_g11.Data.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BudgetPaneController {
    public PieChart pieChartBudget;
    public PieChart pieChartBolsa;
    public BorderPane root;
    public HBox dynamicHBox;
    public ScrollPane scrollPane;
    public Button savedMoney;
    SideBarController sideBarController;
    private Context context;

    public void initialize(){
        context = Context.getInstance();
        savedMoney.setOnMouseEntered(event -> savedMoney.setStyle("-fx-background-color: #e0e0e0;"));
        savedMoney.setOnMouseExited(event -> savedMoney.setStyle("-fx-background-color: transparent;"));
        update();
    }

    private Button createImageButton(String imageUrl,Envelope envelope) {
        Button button = new Button(envelope.getFinalidade());
        button.setContentDisplay(ContentDisplay.TOP);
        button.setMnemonicParsing(false);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction(event -> onEnvelopeButtonClicked(envelope));

        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #e0e0e0;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent;"));

        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(62.0);
        imageView.setFitWidth(88.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        button.setGraphic(imageView);
        button.setCursor(Cursor.HAND);
        button.setFont(new Font("Times New Roman", 14.0));

        // Adicione aqui a lógica do evento de clique, se necessário

        return button;
    }

    private void onEnvelopeButtonClicked(Envelope envelope) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SeeSavedMoney.fxml"));
            Pane secondaryPane = loader.load();

            Stage secondaryStage = new Stage();
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initOwner(root.getScene().getWindow());
            Scene secondaryScene = new Scene(secondaryPane);

            SavedMovneyPopUpToSeeController savedMovneyPopUpToSeeController = loader.getController();
            savedMovneyPopUpToSeeController.setBudgetPane(this,envelope);

            secondaryStage.setScene(secondaryScene);
            secondaryStage.setTitle("Save Money");
            secondaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addToDynamicHBox(Button button) {
        dynamicHBox.getChildren().add(button);
    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }


    public void onAddBudget() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBudgetPopUp.fxml"));
                Pane secondaryPane = loader.load();

                Stage secondaryStage = new Stage();
                secondaryStage.initModality(Modality.WINDOW_MODAL);
                secondaryStage.initOwner(root.getScene().getWindow());
                Scene secondaryScene = new Scene(secondaryPane);

                BudgetPanePopUpController budgetPanePopUpController = loader.getController();
                budgetPanePopUpController.setBudgetPane(this);

                secondaryStage.setScene(secondaryScene);
                secondaryStage.setTitle("Montante");
                secondaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void update() {
        dynamicHBox.getChildren().clear();
        PieChart.Data slice1 = new PieChart.Data("Budget restante", context.getBudgetRestante());
        PieChart.Data slice2 = new PieChart.Data("Budget gasto", context.getBudgetGasto());
        PieChart.Data slice5 = new PieChart.Data("Budget guardado", context.getBudgetGuardado());

        slice1.setName("Restante: " + context.getBudgetRestante() + "€");
        slice1.setPieValue(context.getBudgetRestante());

        slice2.setName("Gasto: " +context.getBudgetGasto()+"€");
        slice2.setPieValue(context.getBudgetGasto());

        slice5.setName("Guardado: " +context.getBudgetGuardado()+"€");
        slice5.setPieValue(context.getBudgetGuardado());

        pieChartBudget.setLabelsVisible(false);
        pieChartBudget.setTitle("Budget");
        pieChartBudget.getData().setAll(slice1, slice2,slice5);

        PieChart.Data slice3 = new PieChart.Data("Bolsa restante: ", context.getValorBolsa());
        PieChart.Data slice4 = new PieChart.Data("Bolsa gasto: ", context.getValorGastoBolsa());

        slice3.setName("Restante: " + context.getValorBolsa() + "€");
        slice3.setPieValue(context.getValorBolsa());

        slice4.setName("Gasto: " + context.getValorGastoBolsa() + "€");
        slice4.setPieValue(context.getValorGastoBolsa());

        pieChartBolsa.setTitle("Bolsa - " + context.getNomeBolsa());
        pieChartBolsa.setLabelsVisible(false);
        pieChartBolsa.getData().setAll(slice3,slice4);

        if(context.getEnvelopes().isEmpty()){
            scrollPane.setVisible(false);
        }else{
            scrollPane.setVisible(true);
            for (Envelope envelope : context.getEnvelopes()) {
                Button button = createImageButton( "/image/saved_money_icon.png",envelope);
                button.setMinSize(88.0, 150);
                addToDynamicHBox(button);
            }
        }

    }

    public void acaoAoFecharJanelaSecundaria() {
        update();
    }

    public void onSavedMoney(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddSavedMoney.fxml"));
            Pane secondaryPane = loader.load();

            Stage secondaryStage = new Stage();
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initOwner(root.getScene().getWindow());
            Scene secondaryScene = new Scene(secondaryPane);

            SavedMoneyPopUpToAddController savedMoneyPopUpToAddController = loader.getController();
            savedMoneyPopUpToAddController.setBudgetPane(this);

            secondaryStage.setScene(secondaryScene);
            secondaryStage.setTitle("Save Money");
            secondaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
