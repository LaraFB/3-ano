package com.example.gps_g11.Data;

import com.example.gps_g11.Data.Budget.Saldo;
import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import com.example.gps_g11.Data.Objetivo.ListaObjetivos;
import com.example.gps_g11.Data.ToDos.ToDoList;
import com.example.gps_g11.Data.Transacao.Despesa;
import com.example.gps_g11.Data.Transacao.Entrada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Context {
    static final String fileName = "DataBase.dat";
    private static Context instance;
    private ContextData contextData;

    private Context() {
        contextData = new ContextData();
        loadFromFile();
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    /**TODO: Categorias Despesas*/
    public List<CategoriaEntradas> getCategoriasListEntradas(){
        return contextData.getListaCategorias().getCategoriasEntradas();
    }
    public List<String> getCategoriaEntradasNomes() {
        List<String> list = new ArrayList<>();
        for (CategoriaEntradas categoria : contextData.getListaCategorias().getCategoriasEntradas()) {
            list.add(categoria.getNome());
        }
        return list;
    }
    public int adicionarDinheiroCategoriaDespesa(double valor, String nomeCategoria) {
        if(contextData.getSaldo().getSaldoPorDistribuir()-valor < 0){
            return -1;
        }
        if(valor == 0)
            return -3;
        for (CategoriaDespesas categoria : contextData.getListaCategorias().getCategoriasDespesas()) {
            if(categoria.getNome().equals(nomeCategoria)){
                categoria.setValor(categoria.getValor()+valor);
                contextData.getSaldo().setSaldoPorDistribuir(contextData.getSaldo().getSaldoPorDistribuir()-valor);
                contextData.getSaldo().setSaldoNosEnvelopes(contextData.getSaldo().getSaldoNosEnvelopes()+valor);
                categoria.setOldValue(valor);
                return 0;
            }
        }
        return -2;
    }
    public int adicionarCategoriaDespesa(double valor, String name,String descricao,boolean isAberto, boolean isRecorrente) {
        if(contextData.getSaldo().getSaldoPorDistribuir()-valor < 0){
            return -1; //Não existe saldo para adicionar algo ao envelope
        }

        if(contextData.getListaCategorias().adicionarCategoriaDespesas(new CategoriaDespesas(name,descricao,valor,isAberto,isRecorrente))){
            contextData.getSaldo().setSaldoPorDistribuir(contextData.getSaldo().getSaldoPorDistribuir()-valor);
            contextData.getSaldo().setSaldoNosEnvelopes(contextData.getSaldo().getSaldoNosEnvelopes()+valor);
            return 0;
        }
        return -2;
    }
    public boolean isListaCategoriasDespesasEmpty() {
        return contextData.getListaCategorias().getCategoriasDespesas().isEmpty();
    }
    /**TODO: Categorias Entradas*/
    public List<CategoriaDespesas> getCategoriasListDespesas(){
        return contextData.getListaCategorias().getCategoriasDespesas();
    }

    public List<String> getCategoriaDespesasNomes() {
        List<String> list = new ArrayList<>();
        for (CategoriaDespesas categoria : contextData.getListaCategorias().getCategoriasDespesas()) {
            list.add(categoria.getNome());
        }
        return list;
    }
    public int adicionarDinheiroCategoriaEntrada(double valor, String nomeCategoria,boolean isDinheiro) {
        for (CategoriaEntradas categoria : contextData.getListaCategorias().getCategoriasEntradas()) {
            if(categoria.getNome().equals(nomeCategoria)){
                categoria.setValor(categoria.getValor()+valor);
                if(isDinheiro){
                    contextData.getSaldo().getBudgetDinheiro().setSaldoReal(contextData.getSaldo().getBudgetDinheiro().getSaldoReal()+valor);
                }else{
                    contextData.getSaldo().getBudgetContaBancaria().setSaldoReal(contextData.getSaldo().getBudgetContaBancaria().getSaldoReal()+valor);
                }
                contextData.getSaldo().setSaldoPorDistribuir(contextData.getSaldo().getSaldoPorDistribuir()+valor);
                return 0;
            }
        }
        return -1;
    }
    public int adicionarCategoriaEntrada(String name,String descricao) {
        return contextData.getListaCategorias().adicionarCategoriaEntradas(new CategoriaEntradas(name,descricao,0));
    }
    public boolean isListaCategoriasEntradasEmpty() {
        return contextData.getListaCategorias().getCategoriasEntradas().isEmpty();
    }

    /**Objetivos*/
    public ListaObjetivos getListaObjetivos(){return contextData.getListaObjetivos();}
    /**Notificações*/
    public ToDoList getListaNotificacoes(){return contextData.getListaNotificacoes();}

    /**Budget*/
    public Saldo getSaldo() {
        return contextData.getSaldo();
    }

    /**Historico de transacoes Despesa*/
    public int adicionarDespesa(String nomeCategoria,String descricao, LocalDate date,double montante,boolean isDinheiro){
        /*Tenho que verificar se esta despesa existe
        * Se existir tenho que verificar se essa categoria tem saldo suficiente nesse envelope
        * Se tiver saldo suficiente vai criar uma despesa
        * Desconta do budget o saldo Real e desconta o saldo nos envelopes, e desconta o valor do envelope
        * */
        if(montante == 0)
            return -3;
        CategoriaDespesas categoriaDespesas = null;
        for (CategoriaDespesas c : contextData.getListaCategorias().getCategoriasDespesas()) {
            if(c.getNome().equals(nomeCategoria)){
                categoriaDespesas = c;
            }
        }
        if(nomeCategoria.equals("Objetivos")){
            if(isDinheiro && montante > contextData.getSaldo().getBudgetDinheiro().getSaldoReal()){
                return -1;
            }
            if(!isDinheiro && montante > contextData.getSaldo().getBudgetContaBancaria().getSaldoReal()){
                return -1;
            }
        }else{

            if(isDinheiro && montante > contextData.getSaldo().getBudgetDinheiro().getSaldoReal()){
                return -1;
            }
            if(!isDinheiro && montante > contextData.getSaldo().getBudgetContaBancaria().getSaldoReal()){
                return -1;
            }
            if(categoriaDespesas  == null){

                System.out.println("1");
                return -2; //Se não existir categoria
            }
            if(categoriaDespesas.getValor() < montante ){
                return -1; //Se não houver saldo suficiente nesse envelope
            }
            categoriaDespesas.setValor(categoriaDespesas.getValor()-montante);
        }

        //Adiciioan adespesa
        if(isDinheiro){
            contextData.getSaldo().getBudgetDinheiro().setSaldoReal(contextData.getSaldo().getBudgetDinheiro().getSaldoReal()-montante);
        }else{
            contextData.getSaldo().getBudgetContaBancaria().setSaldoReal(contextData.getSaldo().getBudgetContaBancaria().getSaldoReal()-montante);
        }
        Despesa transacao = new Despesa(descricao,date,montante,categoriaDespesas,isDinheiro,contextData.getSaldo().getBudgetDinheiro().getSaldoReal()+contextData.getSaldo().getBudgetContaBancaria().getSaldoReal());
        //Adicionar ao historioTransacao a despesa
        contextData.getHistoricoTransacoes().adicionarTransacaoDespesas(transacao);
        //Subtrari ao saldo real e ao saldo nos envelopes a montante
        contextData.getSaldo().setSaldoNosEnvelopes(contextData.getSaldo().getSaldoNosEnvelopes()-montante);
        contextData.getSaldo().setTotalDespesas(contextData.getSaldo().getTotalDespesas()+montante);
        return 0;
    }
    public List<Despesa> getTransacoesDespesa() {
        List<Despesa> transacoesDespesa = contextData.getHistoricoTransacoes().getTransacaoDespesas();
        transacoesDespesa.sort(Comparator.comparing(Despesa::getData));
        return transacoesDespesa;
    }

    public List<Despesa> getTransacoesDespesa(String categoria, LocalDate dateInicio,LocalDate dataFim, String ordenacao){
        List<Despesa> transacoesDespesa = getTransacoesDespesa();
        if (categoria != null && !categoria.equals("Sem filtro") ) {
            transacoesDespesa = transacoesDespesa.stream()
                    .filter(despesa -> despesa.getCategoria().getNome().equals(categoria))
                    .collect(Collectors.toList());
        }

        if (dateInicio != null && dataFim != null) {
            transacoesDespesa = transacoesDespesa.stream()
                    .filter(despesa -> !despesa.getData().isBefore(dateInicio) && !despesa.getData().isAfter(dataFim))
                    .collect(Collectors.toList());
        } else if (dateInicio != null) {
            transacoesDespesa = transacoesDespesa.stream()
                    .filter(despesa -> !despesa.getData().isBefore(dateInicio))
                    .collect(Collectors.toList());
        } else if (dataFim != null) {
            transacoesDespesa = transacoesDespesa.stream()
                    .filter(despesa -> !despesa.getData().isAfter(dataFim))
                    .collect(Collectors.toList());
        }
        if (ordenacao != null && !ordenacao.equals("Sem Filtro")) {
            switch (ordenacao) {
                case "Data por ordem crescente":
                    transacoesDespesa.sort(Comparator.comparing(Despesa::getData));
                    break;
                case "Data por ordem decrescente":
                    transacoesDespesa.sort(Comparator.comparing(Despesa::getData).reversed());
                    break;
                case "Categoria por ordem alfabética":
                    transacoesDespesa.sort(Comparator.comparing(despesa -> despesa.getCategoria().getNome()));
                    break;
                case "Categoria por ordem inversa alfabética":
                    transacoesDespesa.sort(Comparator.comparing(despesa -> despesa.getCategoria().getNome(), Comparator.reverseOrder()));
                    break;
                case "Montante por ordem crescente":
                    transacoesDespesa.sort(Comparator.comparing(Despesa::getMontante));
                    break;
                case "Montante por ordem decrescente":
                    transacoesDespesa.sort(Comparator.comparing(Despesa::getMontante).reversed());
                    break;
                default:
                    break;
            }
        }
        return transacoesDespesa;
    }

    /*public List<TransacaoDespesa> realizarPesquisa(String tipoTransacao, String categoria, LocalDate data, String ordenacao) {
        return contextData.getHistoricoTransacoes().realizarPesquisa(tipoTransacao,categoria,data,ordenacao);
    }*/
    /**Historico de transacoes Entrada*/
    public int adicionarEntrada(String nomeCategoria,String descricao, LocalDate date,double montante,boolean isDinheiro){
        /*Tenho que verificar se esta entrada existe
         * Se existir tenho que verificar se essa categoria tem saldo suficiente nesse envelope
         * Aumenta do budget o saldo Real
         * */
        if (montante ==0)
            return -3;
        CategoriaEntradas categoriaEntradas = null;
        for (CategoriaEntradas c : contextData.getListaCategorias().getCategoriasEntradas()) {
            if(c.getNome().equals(nomeCategoria)){
                categoriaEntradas = c;
            }
        }
        if(categoriaEntradas  == null){
            return -2; //Se não existir categoria
        }
        if(isDinheiro){
            contextData.getSaldo().getBudgetDinheiro().setSaldoReal(contextData.getSaldo().getBudgetDinheiro().getSaldoReal()+montante);
        }else{
            contextData.getSaldo().getBudgetContaBancaria().setSaldoReal(contextData.getSaldo().getBudgetContaBancaria().getSaldoReal()+ montante);
        }
        categoriaEntradas.setValor(categoriaEntradas.getValor()+montante);
        Entrada entrada = new Entrada(descricao,date,montante,categoriaEntradas,isDinheiro,contextData.getSaldo().getBudgetDinheiro().getSaldoReal()+contextData.getSaldo().getBudgetContaBancaria().getSaldoReal());
        contextData.getHistoricoTransacoes().adicionarTransacaoEntrada(entrada);
        contextData.getSaldo().setSaldoPorDistribuir(contextData.getSaldo().getSaldoPorDistribuir()+montante);

        return 0;
    }
    public List<Entrada> getTransacoesEntrada() {
        List<Entrada> transacaoEntradas = contextData.getHistoricoTransacoes().getTransacaoEntradas();
        return transacaoEntradas;
    }

    public List<Entrada> getTransacoesEntrada(String categoria, LocalDate dateInicio,LocalDate dataFim, String ordenacao){
        List<Entrada> transacaoEntrada = getTransacoesEntrada();

        if (categoria != null && !categoria.equals("Sem filtro")) {
            transacaoEntrada = transacaoEntrada.stream()
                    .filter(despesa -> despesa.getCategoria().getNome().equals(categoria))
                    .collect(Collectors.toList());
        }

        if (dateInicio != null && dataFim != null) {
            transacaoEntrada = transacaoEntrada.stream()
                    .filter(entrada -> !entrada.getData().isBefore(dateInicio) && !entrada.getData().isAfter(dataFim))
                    .collect(Collectors.toList());
        } else if (dateInicio != null) {
            transacaoEntrada = transacaoEntrada.stream()
                    .filter(entrada -> !entrada.getData().isBefore(dateInicio))
                    .collect(Collectors.toList());
        } else if (dataFim != null) {
            transacaoEntrada = transacaoEntrada.stream()
                    .filter(entrada -> !entrada.getData().isAfter(dataFim))
                    .collect(Collectors.toList());
        }
        if (ordenacao != null && !ordenacao.equals("Sem filtro")) {
            switch (ordenacao) {
                case "Data por ordem crescente":
                    transacaoEntrada.sort(Comparator.comparing(Entrada::getData));
                    break;
                case "Data por ordem decrescente":
                    transacaoEntrada.sort(Comparator.comparing(Entrada::getData).reversed());
                    break;
                case "Categoria por ordem alfabética":
                    transacaoEntrada.sort(Comparator.comparing(despesa -> despesa.getCategoria().getNome()));
                    break;
                case "Categoria por ordem inversa alfabética":
                    transacaoEntrada.sort(Comparator.comparing(despesa -> despesa.getCategoria().getNome(), Comparator.reverseOrder()));
                    break;
                case "Montante por ordem crescente":
                    transacaoEntrada.sort(Comparator.comparing(Entrada::getMontante));
                    break;
                case "Montante por ordem decrescente":
                    transacaoEntrada.sort(Comparator.comparing(Entrada::getMontante).reversed());
                    break;
                default:
                    break;
            }
        }
        return transacaoEntrada;
    }

    /**Guardar Ficheiro*/
    public void saveToFile() {
        if (contextData != null) {
            contextData.saveToFile(fileName);
        }
    }
    private void loadFromFile() {
        contextData = ContextData.loadFromFile(fileName);
    }

    public CategoriaDespesas getCategoriaByName(String value) {
        for (CategoriaDespesas categoriaDespesas : contextData.getListaCategorias().getCategoriasDespesas()) {
            if(categoriaDespesas.getNome().equals(value)){
                return categoriaDespesas;
            }
        }
        return null;
    }

    public LocalDate getData() {
        return contextData.getData();
    }

    public void verficacoes(LocalDate newValue) {
        //Este metodo é aquele que verifica tudo
        //Alterei a data
        contextData.setDate(newValue);
        //Verificar se o mês anterior é diferente do mês atual
        //se for, guardamos as informações do mês passado (Cria uma classe para as estatisticas)
        //vamos ter uma lista de objetos de estatisticas com informação importante para mostrar nas estatisticas
        //resetar algumas variáveis
        //e outras verficações que possam surgir

        if(contextData.getData().getDayOfMonth() == 1){ //1º dia do mes
            for (CategoriaDespesas env: getCategoriasListDespesas())
                if(env.isRecorrente())
                    env.setValor(env.getValorRecorrente()); //reset envelopes recorrentes

            //guarda historico?
            //reset historico
        }
    }


    /*
















    /* *//**Historico de Despesas*//*
    public boolean addExpense(String category, String description, LocalDate date, float value, boolean recurring) {
        if(value < contextData.getBudget().getBudgetRestante()){
            contextData.getHistoricoTransacoes().adicionarDespesa(category, description, date, value, recurring);
            contextData.getBudget().adicionarAoBudgetGasto(value);
            return true;
        }
        return false;
    }
    public double getTotalExpenses() {
        return contextData.getHistoricoTransacoes().getTotalDespesas();
    }
    public String despesaToString() {
        return contextData.getHistoricoTransacoes().toString();
    }
    public List<Transacao> getHistoricoDespesas() {
        return contextData.getHistoricoTransacoes().getDespesas();
    }
    public void deleteExpense(Transacao despesa) {
        contextData.getHistoricoTransacoes().eliminarDespesa(despesa);
        contextData.getBudget().retirarDoBudgetGasto(despesa.getMontante());
    }
    public void editExpense(Despesa despesa, float value, LocalDate date, String descripton) {
        contextData.getBudget().adicionarAoBudgetGasto(value- despesa.getMontante());
        contextData.getHistoricoDespesas().editarDespesa(despesa,value,date,descripton);
    }

    *//**Historico de entradas*//*
    public boolean aicionarEntrada(String descricao, LocalDate data, float montante) {
        contextData.getHistoricoEntradas().adicionarEntrada(descricao, data, montante);
        contextData.getBudget().adicionarAoBudgetRestante(montante);
        return true;
    }
    public double getTotalEntradas() {
        return contextData.getHistoricoEntradas().getTotalDespesas();
    }
    public String entradasToString() {
        return contextData.getHistoricoEntradas().toString();
    }
    public List<Entrada> getHistroicoEntradas() {
        return contextData.getHistoricoEntradas().getEntradas();
    }*/

/**se n há data, reset automatico*//*

    public void ResetAutomatico(){
        LocalDate hoje = LocalDate.now();
        int diaHoje = hoje.getDayOfMonth();

        if(diaHoje==1){
            contextData.getBudget().LimpaBudget();
        }
    }

    */
/**Budget se clicar no reset*//*

    public void AdicionaDataReset(int dia, int nvezes,String cald,LocalDate hoje){
        contextData.getBudget().setNvezes(nvezes);
        contextData.getBudget().setDiaReset(dia);
        contextData.getBudget().setCald(cald);
        contextData.getBudget().setHoje(hoje);
        CalculaProxData();
    }

    */
/**Calcula próxima data e atualiza no budget*//*

    private void CalculaProxData(){
        int diah;
        int mesh = contextData.getBudget().getHoje().getMonth().getValue();
        int anoh = contextData.getBudget().getHoje().getYear();

        if(contextData.getBudget().getDiaReset() != 0)
            diah = contextData.getBudget().getDiaReset();
        else
            diah = contextData.getBudget().getHoje().getDayOfMonth();

        int nvezes = contextData.getBudget().getNvezes();
        LocalDate dataSeguinte = LocalDate.of(anoh, mesh, diah);
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(dataSeguinte);

        switch (contextData.getBudget().getCald()) {
            case "ano":
                dataSeguinte= datePicker.getValue().plusYears(nvezes);
                break;
            case "mes":
                dataSeguinte= datePicker.getValue().plusMonths(nvezes);
                break;
            case "dia":
                dataSeguinte= datePicker.getValue().plusDays(nvezes);
                break;
            default:
        }
        contextData.getBudget().setHoje(dataSeguinte);
    }
*/


    /* public void criarEnvelope(String finalidade,double valor){
        contextData.getBudget().criarEnvelope(finalidade, valor);
    }*/
    /*public List<Envelope> getEnvelopes(){
        return contextData.getBudget().getEnvelopes();
    }*/
}
