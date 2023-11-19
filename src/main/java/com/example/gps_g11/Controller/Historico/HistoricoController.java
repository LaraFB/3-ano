package com.example.gps_g11.Controller.Historico;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Transacao.Transacao;
import com.example.gps_g11.Data.Categoria.Categoria;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HistoricoController implements Initializable {
    private static final String NO_FILTER = "Sem filtro";
    private static final String FILTER_ENTRADAS = "Entradas";
    private static final String FILTER_DESPESAS = "Despesas";


    private static final String FILTER_DATA = "Data";
    private static final String FILTER_CATEGORIA = "Categoria";

    private static final String FILTER_DATA_CRESCENTE = "Data por ordem crescente";
    private static final String FILTER_DATA_DECRESCENTE = "Data por ordem decrescente";
    private static final String FILTER_CATEGORIA_ORDEM_ALFABETICA = "Categoria por ordem alfabética";
    private static final String FILTER_CATEGORIA_ORDEM_INVERSA_ALFABETICA = "Categoria por ordem inversa alfabética";
    private static final String FILTER_MONTANTE_CRESCENTE = "Montante por ordem crescente";
    private static final String FILTER_MONTANTE_DESCRESCENTE = "Montante por ordem decrescente";



    public BorderPane root;
    public ChoiceBox<String> cbTransacao;
    public ChoiceBox<String> cbFiltrosAvancados;
    public ChoiceBox<String> cbEnvelopes;
    public DatePicker dpDate;
    public ChoiceBox<String> cbOrdenar;
    public Button btnRemoverFiltros;
    public Button btnPesquisa;
    public TableView<Transacao> tableView;
    public TableColumn<Transacao, Float> tcMontante;
    public TableColumn<Transacao, LocalDate> tfData;
    public TableColumn<Transacao, String> tfEnvelope;
    public TableColumn<Transacao, String> tfDescricao;
    private SideBarController sideBarController;
    private Context context;
    private ObservableList<Transacao> transacaos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = Context.getInstance();
        /*context.adicionarTransacao("Despesa","Descricao",new Categoria("Propinas"), LocalDate.of(2023, 11, 16),100);
        context.adicionarTransacao("Entrada","Descricao1",null, LocalDate.of(2023, 11, 15),200);
        context.adicionarTransacao("Despesa","Descricao2",new Categoria("Renda"), LocalDate.of(2024, 11, 15),300);
        context.adicionarTransacao("Entrada","Descricao3",null, LocalDate.of(2023, 12, 15),400);*/
        configurarTabela();
        configurarChoiceBoxs();
        configurarListeners();
        update();
    }

    private void configurarChoiceBoxs() {
        ObservableList<String> parametrosTransacao = FXCollections.observableArrayList(
            NO_FILTER,FILTER_DESPESAS,FILTER_ENTRADAS
        );
        ObservableList<String> parametrosFiltros = FXCollections.observableArrayList(
            NO_FILTER,FILTER_CATEGORIA,FILTER_DATA
        );
        ObservableList<String> parametrosOrdenacao = FXCollections.observableArrayList(
            NO_FILTER,FILTER_DATA_CRESCENTE,FILTER_DATA_DECRESCENTE,FILTER_MONTANTE_CRESCENTE,FILTER_MONTANTE_DESCRESCENTE
        );

        ObservableList<String> parametrosCategorias = FXCollections.observableArrayList(
                NO_FILTER
        );
        for (Categoria categoria : context.getCategoriasList()) {
            parametrosCategorias.add(categoria.getNome());
        }



        cbTransacao.setItems(parametrosTransacao);
        cbTransacao.setValue(NO_FILTER);

        cbFiltrosAvancados.setItems(parametrosFiltros);
        cbFiltrosAvancados.setValue(NO_FILTER);

        cbOrdenar.setItems(parametrosOrdenacao);
        cbOrdenar.setValue(NO_FILTER);

        cbEnvelopes.setItems(parametrosCategorias);
        cbEnvelopes.setValue(NO_FILTER);
        mostrarDatePicker(false);
        mostrarChoiceBox(false);
    }

    private void configurarListeners() {
        cbFiltrosAvancados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            dpDate.setValue(null);
            if (FILTER_DATA.equals(newValue)) {
                mostrarDatePicker(true);
                mostrarChoiceBox(false);
            } else if (FILTER_CATEGORIA.equals(newValue)) {
                mostrarDatePicker(false);
                mostrarChoiceBox(true);
            } else {
                mostrarDatePicker(false);
                mostrarChoiceBox(false);
            }
        });

        cbTransacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            atualizarEscolhasOrdenar(newValue);
        });

    }
    private void atualizarEscolhasOrdenar(String tipoTransacao) {
        ObservableList<String> escolhasOrdenar;
        if ("Despesas".equals(tipoTransacao)) {
            escolhasOrdenar = FXCollections.observableArrayList(NO_FILTER,FILTER_CATEGORIA_ORDEM_ALFABETICA,FILTER_CATEGORIA_ORDEM_INVERSA_ALFABETICA
            ,FILTER_DATA_CRESCENTE,FILTER_DATA_DECRESCENTE,FILTER_MONTANTE_CRESCENTE,FILTER_DATA_DECRESCENTE);
        }else {
            escolhasOrdenar = FXCollections.observableArrayList(NO_FILTER,FILTER_DATA_CRESCENTE,FILTER_DATA_DECRESCENTE,FILTER_MONTANTE_CRESCENTE,FILTER_DATA_DECRESCENTE);
        }

        cbOrdenar.setItems(escolhasOrdenar);
        cbOrdenar.setValue(escolhasOrdenar.isEmpty() ? null : escolhasOrdenar.get(0));  // Define o primeiro valor, se disponível
    }

    private void mostrarDatePicker(boolean mostrar) {
        dpDate.setVisible(mostrar);
        dpDate.setManaged(mostrar);
    }

    private void mostrarChoiceBox(boolean mostrar) {
        cbEnvelopes.setVisible(mostrar);
        cbEnvelopes.setManaged(mostrar);
    }
    private void limparFiltros() {
        dpDate.setValue(null);
        cbTransacao.setValue(NO_FILTER);
        cbFiltrosAvancados.setValue(NO_FILTER);
        cbOrdenar.setValue(NO_FILTER);
        cbEnvelopes.setValue(NO_FILTER);
        mostrarDatePicker(false);
        mostrarChoiceBox(false);
        update();
    }
    public void onRemoverFiltros(){
        limparFiltros();
    }

    private void update(){
        tableView.getItems().clear();
        transacaos.clear();
        transacaos.addAll(context.getTransacoes());
        tableView.setItems(transacaos);
    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }
    private void configurarTabela() {
        tcMontante.setCellValueFactory(new PropertyValueFactory<>("montante"));
        tfData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tfDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        tfEnvelope.setCellValueFactory(cellData -> {
            Categoria categoria = cellData.getValue().getCategoria();
            return new SimpleStringProperty(categoria != null ? categoria.getNome() : "Não existe categoria para esta transação");
        });
    }

    public void onPesquisar(){
        String tipoTransacao = cbTransacao.getValue();
        String filtroAvancado = cbFiltrosAvancados.getValue();
        String categoria = cbEnvelopes.getValue();
        LocalDate date = dpDate.getValue();
        String ordenacao = cbOrdenar.getValue();

        transacaos.clear();
        tableView.getItems().clear();
        transacaos.addAll(context.realizarPesquisa(tipoTransacao, filtroAvancado, categoria,date, ordenacao));
        tableView.setItems(transacaos);
    }


}

/*

    @FXML ChoiceBox<String> SearchCategory;
    @FXML DatePicker SearchDate;

    int i=0;
        SearchCategory.getItems().add("No Filter");
                while(context.getCategory(i) != null){
                SearchCategory.getItems().add(context.getCategoryName(i));
                i++;
                }
                SearchCategory.setValue("No Filter");


                String selectedCategory = SearchCategory.getSelectionModel().getSelectedItem();
                LocalDate selectedDate = SearchDate.getValue();

                for (Expense expense : expenses) {
                // Verificar se os valores estão vazios ou se coincidem com o Expense
                if ((selectedCategory.equals("No Filter") || expense.getCategory().equals(selectedCategory))
                && (selectedDate == null || expense.getDate().isEqual(selectedDate))) {
                Button button = createExpenseButton(expense);
                container.getChildren().add(button);
                }
                }*/
/*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = Context.getInstance();

        //initContainer();
        //container.setVgrow(container, Priority.ALWAYS);
    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }

    public void initContainer(){
        container.setPadding(new Insets(10,10,10,10));
        container.getChildren().clear();
        expenses = context.getExpensesHistory();


        for (Expense expense : expenses) {
                Button button = createExpenseButton(expense);
                container.getChildren().add(button);
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
            secondaryStage.setOnHidden(event -> {
                initContainer();
            });
            secondaryStage.showAndWait();
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

    public void onSearch() {
        initContainer();
    }

    public void acaoAoFecharJanelaSecundaria() {
        initContainer();
    }

    public void editExpense(Expense expense, float value, LocalDate date,String descripton){
        context.editExpense(expense,value,date,descripton);
    }*/