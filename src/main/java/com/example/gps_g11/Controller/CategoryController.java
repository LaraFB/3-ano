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
                        HBox hBox = new HBox();
                        hBox.getStylesheets().add(getClass().getResource("/com/example/gps_g11/Style.css").toExternalForm());
                        hBox.getStyleClass().add("MenuItem");
                        hBox.setPadding(new Insets(10, 10, 10, 10));
                        VBox vBox = new VBox();
                        VBox vBox1 = new VBox();

                        vBox.getChildren().add(new Label("Montante: " + expense.getValue()));
                        vBox.getChildren().add(new Label("Data: " + expense.getDate()));

    public void update(){
        int i=0;
            while(context.getCategory(i) != null){
                Button newBtn = new Button(context.getCategoryName(i));
                HBox3.getChildren().add(newBtn);
                i++;
            }
    }


                        vBox1.getChildren().add(new Label("Categoria"));
                        vBox1.getChildren().add(new Label(expense.getCategory()));
                        vBox1.setAlignment(Pos.CENTER);

                        Button deletebtn = new Button();
                        deletebtn.setOnAction(event -> delete(expense));
                        Image deleteImage = new Image(getClass().getResourceAsStream("/image/Trash_icon.png"));
                        ImageView deleteImageView = new ImageView(deleteImage);
                        deleteImageView.setFitWidth(10);
                        deleteImageView.setFitHeight(10);
                        deleteImageView.setPreserveRatio(true);
                        deletebtn.setGraphic(deleteImageView);


                        VBox vBox2 = new VBox(deletebtn);


                        hBox.getChildren().addAll(vBox, vBox1, vBox2);
                        container.getChildren().add(hBox);
                    }
        }
    }*/

    public void delete(Expense expense) {;
        context.deleteExpense(expense);
        initContainer();
    }


    public void Search(ActionEvent actionEvent) {
        initContainer();
    }
}
