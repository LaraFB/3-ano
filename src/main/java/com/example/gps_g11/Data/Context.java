package com.example.gps_g11.Data;

import com.example.gps_g11.Data.Objetivo.ListaObjetivos;
import com.example.gps_g11.Data.Transacao.Transacao;
import com.example.gps_g11.Data.Categoria.Categoria;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Categoria getCategoriaByName(String nome){
        return contextData.getListaCategorias().getCategoriaPorNome(nome);
    }

    public List<String> getCategoriaNomes() {
        List<String> list = new ArrayList<>();
        for (Categoria categoria : contextData.getListaCategorias().getCategorias()) {
            list.add(categoria.getNome());
        }
        return list;
    }
    public int adicionarDinheiroEnvelope(double valor, String nomeCategoria) {
        if(contextData.getBudget().getSaldoDisponivel()-valor < 0){
            return -1; //Não existe saldo para adicionar algo ao envelope
        }
        for (Categoria categoria : contextData.getListaCategorias().getCategorias()) {
            if(categoria.getNome().equals(nomeCategoria)){
                categoria.setValor(categoria.getValor()+valor);
                contextData.getBudget().setSaldoDisponivel(getSaldoDisponivel()-valor);
                return 0;
            }
        }
        return -2; //Não existe essa categoria
    }
    public int adicionarCategoriaPorNome(double valor, String name,boolean isAberto) {
        if(contextData.getBudget().getSaldoDisponivel()-valor < 0){
            return -1; //Não existe saldo para adicionar algo ao envelope
        }
        contextData.getListaCategorias().adicionarCateogiraNome(valor,name,isAberto);
        contextData.getBudget().setSaldoDisponivel(getSaldoDisponivel()-valor);
        return 0;
    }
    public int adicionarCategoriaNomeDescricao(double valor, String name, String descripton,boolean isAberto) {
        if(contextData.getBudget().getSaldoDisponivel()-valor < 0){
            return -1; //Não existe saldo para adicionar algo ao envelope
        }
        contextData.getListaCategorias().adicionarCategoriaNomeDescricao(valor,name,descripton,isAberto);
        contextData.getBudget().setSaldoDisponivel(getSaldoDisponivel()-valor);
        return 0;
    }
    public boolean isEmpty() {
        return contextData.getListaCategorias().isEmpty();
    }

    /**Objetivos*/
    public ListaObjetivos getListaObjetivos(){return contextData.getListaObjetivos();}

    /**Budget*/
    public double getSaldoReal(){
        return contextData.getBudget().getSaldoReal();
    }
    public double getSaldoDisponivel(){
        return contextData.getBudget().getSaldoDisponivel();
    }
    public double getTotalDespesas() {
        return contextData.getBudget().getTotalDespesas();
    }

    /**Historico de transacoes*/
    public int adicionarTransacao(String tipo, String descricao,String nomeCategoria, LocalDate date,double montante){
        Categoria categoria = null;

        for (Categoria c : contextData.getListaCategorias().getCategorias()) {
            if(c.getNome().equals(nomeCategoria)){
                categoria = c;
            }
        }
        if(categoria  == null){
            return -2; //Se não existir categoria
        }
        if(categoria.getValor() < montante){
            return -1; //Se não houver saldo suficiente nesse envelope
        }
        contextData.getHistoricoTransacoes().adicionarTransacao(new Transacao(tipo,descricao,categoria,date,montante));
        contextData.getBudget().setSaldoReal(contextData.getBudget().getSaldoReal()-montante);
        contextData.getBudget().setTotalDespesas(contextData.getBudget().getTotalDespesas()+montante);
        return 0;
    }
    public int adicionarTransacao(String tipo, String descricao, LocalDate date,double montante){
        contextData.getHistoricoTransacoes().adicionarTransacao(new Transacao(tipo,descricao,null,date,montante));
        contextData.getBudget().setSaldoReal(contextData.getBudget().getSaldoReal()+montante);
        contextData.getBudget().setSaldoDisponivel(contextData.getBudget().getSaldoDisponivel()+montante);
        return 0;
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

    public List<Transacao> realizarPesquisa(String tipoTransacao, String categoria,LocalDate data, String ordenacao) {
       return contextData.getHistoricoTransacoes().realizarPesquisa(tipoTransacao,categoria,data,ordenacao);
    }





    /* public void criarEnvelope(String finalidade,double valor){
        contextData.getBudget().criarEnvelope(finalidade, valor);
    }*/
    /*public List<Envelope> getEnvelopes(){
        return contextData.getBudget().getEnvelopes();
    }*/
}
