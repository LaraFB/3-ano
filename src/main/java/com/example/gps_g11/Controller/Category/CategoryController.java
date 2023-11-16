package com.example.gps_g11.Controller.Category;

import com.example.gps_g11.Controller.Budget.BudgetPanePopUpController;
import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Expenses.Expense;
import com.example.gps_g11.Data.categoryManagment.Category;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    public StackPane root;
    SideBarController sideBarController;
    Context context;

    List<Expense> expenses;

    @FXML VBox container;
    @FXML ChoiceBox<String> SearchCategory;
    @FXML DatePicker SearchDate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        context = Context.getInstance();
        int i=0;
        SearchCategory.getItems().add("No Filter");
        while(context.getCategory(i) != null){
            SearchCategory.getItems().add(context.getCategoryName(i));
            i++;
        }
        SearchCategory.setValue("No Filter");
        initContainer();
        container.setVgrow(container, Priority.ALWAYS);
    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void initContainer(){
        container.setPadding(new Insets(10,10,10,10));
        container.getChildren().clear();
        expenses = context.getExpensesHistory();

        String selectedCategory = SearchCategory.getSelectionModel().getSelectedItem();
        LocalDate selectedDate = SearchDate.getValue();

        for (Expense expense : expenses) {
            // Verificar se os valores estão vazios ou se coincidem com o Expense
            if ((selectedCategory.equals("No Filter") || expense.getCategory().equals(selectedCategory))
                    && (selectedDate == null || expense.getDate().isEqual(selectedDate))) {
                Button button = createExpenseButton(expense);
                container.getChildren().add(button);
            }
        }
    }

    private Button createExpenseButton(Expense expense) {
        Button button = new Button();

        // Configurar estilos e dimensões do botão
        button.getStylesheets().add(getClass().getResource("/com/example/gps_g11/Style.css").toExternalForm());
        button.getStyleClass().add("buttonItemCategory");
        button.setPrefHeight(100);
        button.setPrefWidth(1000);

        // VBox para Montante e Data
        VBox infoVBox = new VBox();
        infoVBox.setPrefHeight(200);
        infoVBox.setPrefWidth(300);
        infoVBox.setSpacing(15);
        infoVBox.setAlignment(Pos.CENTER);

        // Configurar elementos dentro do VBox (montante e data)
        Label montanteLabel = new Label("Montante: ");
        montanteLabel.setStyle("-fx-font-size: 22;");
        Label lblMontante = new Label(String.valueOf(expense.getValue()) + "€");
        lblMontante.setStyle("-fx-font-size: 22; -fx-text-fill: #656565;");

        Label dataLabel = new Label("Data: ");
        dataLabel.setStyle("-fx-font-size: 22;");
        Label lblData = new Label(String.valueOf(expense.getDate()));
        lblData.setStyle("-fx-font-size: 22; -fx-text-fill: #656565;");

        infoVBox.getChildren().addAll(createHBoxWithMargin(montanteLabel, lblMontante, 10),
                createHBoxWithMargin(dataLabel, lblData, 10));

        // VBox para Categoria
        VBox categoriaVBox = new VBox();
        categoriaVBox.setPrefHeight(200);
        categoriaVBox.setPrefWidth(600);
        categoriaVBox.setSpacing(20);
        categoriaVBox.setAlignment(Pos.CENTER);

        // Configurar elementos dentro do VBox (categoria)
        Label categoriaLabel = new Label("Categoria");
        categoriaLabel.setStyle("-fx-font-size: 22;");
        Label lblCategoria = new Label(expense.getCategory());
        lblCategoria.setStyle("-fx-font-size: 22; -fx-text-fill: #656565;");

        categoriaVBox.getChildren().addAll(categoriaLabel, lblCategoria);

        // VBox para Botões
        VBox buttonVBox = new VBox();
        buttonVBox.setPrefHeight(200);
        buttonVBox.setPrefWidth(50);
        buttonVBox.setSpacing(10);

        // Configurar botões dentro do VBox
        Button btnEdit = createButtonWithImage("/image/edit_icon.png");
        Button btnDelete = createButtonWithImage("/image/trash_icon.png");
        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                actionEvent.consume();
                detailsExpense(expense,true);
            }
        });
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                actionEvent.consume();
                delete(expense);
            }
        });
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                detailsExpense(expense,false);
            }
        });

        buttonVBox.getChildren().addAll(btnEdit, btnDelete);

        // Configurar elementos dentro do botão usando setGraphic
        button.setGraphic(new HBox(infoVBox, categoriaVBox, buttonVBox));

        return button;
    }

    private void detailsExpense(Expense expense,boolean isEdit) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryDetailsPopUp.fxml"));
            Pane secondaryPane = loader.load();

            Stage secondaryStage = new Stage();
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initOwner(root.getScene().getWindow());
            Scene secondaryScene = new Scene(secondaryPane);

            CategoryDetailsPopUp categoryDetailsPopUp = loader.getController();
            categoryDetailsPopUp.setData(this,expense,isEdit);


            secondaryStage.setScene(secondaryScene);
            secondaryStage.setTitle("Detalhes Despesas");
            secondaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private HBox createHBoxWithMargin(Label label1, Label label2, double leftMargin) {
        HBox hbox = new HBox(label1, label2);
        VBox.setMargin(hbox, new Insets(0, 0, 0, leftMargin));
        return hbox;
    }

    private Button createButtonWithImage(String imagePath) {
        Button button = new Button();
        button.getStylesheets().add(getClass().getResource("/com/example/gps_g11/Style.css").toExternalForm());
        button.getStyleClass().add("buttonCategory");
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
        return button;
    }

    public void delete(Expense expense) {;
        context.deleteExpense(expense);
        initContainer();
    }


    public void onSearch(ActionEvent actionEvent) {
        initContainer();
    }

    public void acaoAoFecharJanelaSecundaria() {
        initContainer();
    }

    public void editExpense(Expense expense, float value, LocalDate date,String descripton){
        context.editExpense(expense,value,date,descripton);
    }
}
