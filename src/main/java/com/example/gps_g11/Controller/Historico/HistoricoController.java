package com.example.gps_g11.Controller.Historico;

import com.example.gps_g11.Controller.SideBarController;
import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import com.example.gps_g11.Data.Context;
import com.example.gps_g11.Data.Transacao.Despesa;
import com.example.gps_g11.Data.Transacao.Entrada;
import com.example.gps_g11.Data.Transacao.Transacao;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.util.Callback;

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
    public DatePicker dpDateInicio;
    public DatePicker dpDateFim;
    public ChoiceBox<String> cbOrdenar;
    public TableView<Transacao> tableView;
    public TableColumn<Transacao, String> tcMontante;
    public TableColumn<Transacao, LocalDate> tfData;
    public TableColumn<Transacao, String> tfEnvelope;
    public TableColumn<Transacao, String> tfDescricao;
    public Label lblTotal;
    public Label lblTotalEntradas;
    public Label lblTotalDespesas;
    private SideBarController sideBarController;
    private Context context;
    private ObservableList<Transacao> transacaos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        context = Context.getInstance();
        configurarTabela();
        configurarChoiceBoxs();
        configurarListeners();
        update();
    }

    private void configurarChoiceBoxs() {
        ObservableList<String> parametrosTransacao = FXCollections.observableArrayList(
            NO_FILTER,FILTER_DESPESAS,FILTER_ENTRADAS
        );
        ObservableList<String> parametrosOrdenacao = FXCollections.observableArrayList(
            NO_FILTER,FILTER_DATA_CRESCENTE,FILTER_DATA_DECRESCENTE,FILTER_MONTANTE_CRESCENTE,FILTER_MONTANTE_DESCRESCENTE,FILTER_CATEGORIA_ORDEM_INVERSA_ALFABETICA,FILTER_CATEGORIA_ORDEM_ALFABETICA
        );

        ObservableList<String> parametrosCategorias = FXCollections.observableArrayList(
                NO_FILTER
        );


        cbTransacao.setItems(parametrosTransacao);
        cbTransacao.setValue(NO_FILTER);

        cbOrdenar.setItems(parametrosOrdenacao);
        cbOrdenar.setValue(NO_FILTER);

        cbEnvelopes.setItems(parametrosCategorias);
        cbEnvelopes.setValue(NO_FILTER);
        cbEnvelopes.setDisable(true);
        /*
        mostrarDatePicker(false);
        mostrarChoiceBox(false);*/
    }

    private void configurarListeners() {
        cbEnvelopes.valueProperty().addListener((observable,oldValue,newValue) -> {
            update();
        });

        cbTransacao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            atualizarEscolhasOrdenar(newValue);

        });

        cbOrdenar.valueProperty().addListener((observable,oldValue,newValue) -> {
            update();
        });
        dpDateInicio.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
            update();
        }));
        dpDateFim.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
            update();
        }));

    }
    private void atualizarEscolhasOrdenar(String tipoTransacao) {
        ObservableList<String> parametrosCategorias = FXCollections.observableArrayList(
                NO_FILTER
        );
        if ("Despesas".equals(tipoTransacao)) {
            cbEnvelopes.setDisable(false);
            parametrosCategorias.addAll(context.getCategoriaDespesasNomes());
        }else if("Entradas".equals(tipoTransacao)) {
            cbEnvelopes.setValue(NO_FILTER);
            cbEnvelopes.setDisable(false);
            parametrosCategorias.addAll(context.getCategoriaEntradasNomes());
        }else{
            cbEnvelopes.setValue(NO_FILTER);
            cbEnvelopes.setDisable(true);
        }
        cbEnvelopes.setItems(parametrosCategorias);
        cbEnvelopes.setValue(NO_FILTER);  // Define o primeiro valor, se disponível
    }

    /*private void mostrarDatePicker(boolean mostrar) {
        dpDate.setVisible(mostrar);
        dpDate.setManaged(mostrar);
    }

    private void mostrarChoiceBox(boolean mostrar) {
        cbEnvelopes.setVisible(mostrar);
        cbEnvelopes.setManaged(mostrar);
    }*/
    /*private void limparFiltros() {
        dpDate.setValue(null);
        cbTransacao.setValue(NO_FILTER);
        cbFiltrosAvancados.setValue(NO_FILTER);
        cbOrdenar.setValue(NO_FILTER);
        cbEnvelopes.setValue(NO_FILTER);
        mostrarDatePicker(false);
        mostrarChoiceBox(false);
        update();
    }*/
    /*public void onRemoverFiltros(){
        limparFiltros();
    }*/

    private void update(){
        String tipoTransacao = cbTransacao.getValue();
        String categoria = cbEnvelopes.getValue();
        LocalDate dateInicio = dpDateInicio.getValue();
        LocalDate dateFim = dpDateFim.getValue();
        String ordenacao = cbOrdenar.getValue();

        transacaos.clear();
        tableView.getItems().clear();
        if(tipoTransacao.equals("Despesas")){
            transacaos.addAll(context.getTransacoesDespesa(categoria,dateInicio,dateFim,ordenacao));
        }else if(tipoTransacao.equals("Entradas")){
            transacaos.addAll(context.getTransacoesEntrada(categoria,dateInicio,dateFim,ordenacao));
        }else{
            transacaos.addAll(context.getTransacoesEntrada(categoria,dateInicio,dateFim,ordenacao));
            transacaos.addAll(context.getTransacoesDespesa(categoria,dateInicio,dateFim,ordenacao));
        }
        tableView.setItems(transacaos);
        if(ordenacao == null || ordenacao.equals("Sem Filtro")){
            tableView.getSortOrder().clear();
            tfData.setSortType(TableColumn.SortType.DESCENDING);
            tableView.getSortOrder().add(tfData);
            tableView.sort();
        }
        double entradasTotal = 0,desepsasTotal = 0;
        for (int i = 0; i < transacaos.size(); i++) {
            if(transacaos.get(i) instanceof Entrada){
                entradasTotal += transacaos.get(i).getMontante();
            }else{
                desepsasTotal += transacaos.get(i).getMontante();
            }
        }
        lblTotalDespesas.setText(String.valueOf(desepsasTotal) + " €");
        lblTotalEntradas.setText(String.valueOf(entradasTotal) + " €");
        lblTotal.setText(String.valueOf(entradasTotal-desepsasTotal) + " €");
    }

    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }
    private void configurarTabela() {
        tfData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tfDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcMontante.setCellValueFactory(param -> {
            Transacao transacao = param.getValue();
            if (transacao instanceof Entrada) {
                return new SimpleStringProperty("+"+transacao.getMontante());
            } else if (transacao instanceof Despesa) {
                return new SimpleStringProperty("-"+transacao.getMontante());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });
        tcMontante.setCellFactory(column -> {
            return new TableCell<Transacao, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);

                        Transacao transacao = getTableView().getItems().get(getIndex());

                        if (transacao instanceof Entrada) {
                            setStyle("-fx-text-fill: green;");
                        } else if (transacao instanceof Despesa) {
                            setStyle("-fx-text-fill: red;");
                        } else {
                            setStyle(""); // Para outras situações, você pode querer definir um estilo padrão
                        }
                    }
                }
            };
        });
        tfEnvelope.setCellValueFactory(param -> {
            Transacao transacao = param.getValue();
            if (transacao instanceof Entrada) {
                return new SimpleStringProperty(((Entrada) transacao).getCategoria().getNome());
            } else if (transacao instanceof Despesa) {
                return new SimpleStringProperty(((Despesa) transacao).getCategoria().getNome());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });
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