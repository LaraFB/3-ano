package com.example.gps_g11.Controller;

import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Expenses.Expense;
import com.example.gps_g11.Data.categoryManagment.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    SideBarController sideBarController;
    Context context;

    List<Expense> expenses;

    @FXML VBox container;
    @FXML TextField SearchName;
    @FXML ChoiceBox SearchCategory;
    @FXML DatePicker SearchDate;

    private String[] categorias ={"","Cafe","Compras","Propinas","Renda","Refeiçoes na Cantina","Outra"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = Context.getInstance();
        SearchCategory.getItems().setAll(categorias);
        SearchCategory.setValue("");
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
        for (Expense expense : expenses) {
            if(expense.getName().contains(SearchName.getText()) || expense.getName().isEmpty())
                if (expense.getCategory().contains(SearchCategory.getValue().toString()) || SearchCategory.getValue().toString().isEmpty())
                    if (expense.getDate().equals(SearchDate.getValue()) || SearchDate.getValue() == null) {
                        HBox hBox = createExpenseHBox(expense);
                        container.getChildren().add(hBox);
                    }
        }
    }

    private HBox createExpenseHBox(Expense expense) {
        HBox hbox = new HBox();
        hbox.getStylesheets().add(getClass().getResource("/com/example/gps_g11/Style.css").toExternalForm());
        hbox.getStyleClass().add("vBoxItemCategory");
        hbox.setPrefHeight(100);
        hbox.setPrefWidth(600);
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);

        // VBox para Montante e Data
        VBox infoVBox = new VBox();
        infoVBox.setPrefHeight(200);
        infoVBox.setPrefWidth(300);
        infoVBox.setSpacing(15);
        infoVBox.setAlignment(Pos.CENTER);

        Label montanteLabel = new Label("Montante: ");
        montanteLabel.setStyle("-fx-font-size: 22;");
        Label lblMontante = new Label( String.valueOf(expense.getValue()) + "€");
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
        Button btnEdit = createButtonWithImage("/image/edit_icon.png");
        Button btnSave = createButtonWithImage("/image/download_icon.png");
        Button btnDelete = createButtonWithImage("/image/trash_icon.png");

        buttonVBox.getChildren().addAll(btnEdit, btnSave, btnDelete);

        // Adicionar todas as VBox ao HBox
        hbox.getChildren().addAll(infoVBox, categoriaVBox, buttonVBox);

        return hbox;
    }

    private HBox createHBoxWithMargin(Label label1, Label label2, double leftMargin) {
        HBox hbox = new HBox(label1, label2);
        VBox.setMargin(hbox, new Insets(0, 0, 0, leftMargin));
        return hbox;
    }

    private Button createButtonWithImage(String imagePath) {
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent;");
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #e0e0e0;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent;"));
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


    public void Search(ActionEvent actionEvent) {
        initContainer();
    }
}
