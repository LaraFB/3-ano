package com.example.gps_g11.Data;

import com.example.gps_g11.Data.Objetivo.ListaObjetivos;
import com.example.gps_g11.Data.Transacao.Transacao;
import com.example.gps_g11.Data.Categoria.Categoria;

import java.time.LocalDate;
import java.util.List;

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




    /*public String getNomeBolsa(){
        return contextData.getBudget().getBolsa().getNome();
    }
    public double getValorBolsa(){
        return contextData.getBudget().getBolsa().getValor();
    }

    public double getValorGastoBolsa(){
        return contextData.getBudget().getBolsa().getValorGasto();
    }*/

    /**Categorias*/
    public List<Categoria> getCategoriasList(){
        return contextData.getListaCategorias().getCategorias();
    }
    public Categoria getCategoriaPorIndex(int i) {
        return contextData.getListaCategorias().getCategoriaPorIndex(i);
    }
    public Categoria getCategoriaPorNome(String name) {
        return contextData.getListaCategorias().getCategoriaPorNome(name);
    }
    public String getCategoriaNomePorIndex(int i) {
        return contextData.getListaCategorias().getCategoriaNomePorIndex(i);
    }
    public boolean removerCategoriaPorNome(String name){
        return contextData.getListaCategorias().removeCategoriaNome(name);
    }
    public void adicionarCategoriaPorNome(String name,boolean isAberto) {
        contextData.getListaCategorias().adicionarCateogiraNome(name,isAberto);
    }
    public void adicionarCategoriaNomeDescricao(String name, String descripton,boolean isAberto) {
        contextData.getListaCategorias().adicionarCategoriaNomeDescricao(name,descripton,isAberto);
    }
    public boolean isEmpty() {
        return contextData.getListaCategorias().isEmpty();
    }

    public ListaObjetivos getListaObjetivos(){return contextData.getListaObjetivos();}

    /**Budget*/
    public void addSaldo(double valor){
        contextData.getBudget().addSaldo(valor);
    }
    public double getSaldoReal(){
        return contextData.getBudget().getSaldoReal();
    }
    public double getSaldoDisponivel(){
        return contextData.getBudget().getSaldoDisponivel();
    }

    /**Historico de transacoes*/
    public void adicionarTransacao(String tipo, String descricao,Categoria categoria, LocalDate date,double montante){
        contextData.getHistoricoTransacoes().adicionarTransacao(new Transacao(tipo,descricao,categoria,date,montante));
    }
    public List<Transacao> getTransacoes(){
        return contextData.getHistoricoTransacoes().buscarTodasTransacoes();
    }
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

    /**Guardar Ficheiro*/
    public void saveToFile() {
        if (contextData != null) {
            contextData.saveToFile(fileName);
        }
    }
    private void loadFromFile() {
        contextData = ContextData.loadFromFile(fileName);
    }

    public List<Transacao> realizarPesquisa(String tipoTransacao, String filtroAvancado, String categoria,LocalDate data, String ordenacao) {
       return contextData.getHistoricoTransacoes().realizarPesquisa(tipoTransacao,filtroAvancado,categoria,data,ordenacao);
    }

    /* public void criarEnvelope(String finalidade,double valor){
        contextData.getBudget().criarEnvelope(finalidade, valor);
    }*/
    /*public List<Envelope> getEnvelopes(){
        return contextData.getBudget().getEnvelopes();
    }*/
}
