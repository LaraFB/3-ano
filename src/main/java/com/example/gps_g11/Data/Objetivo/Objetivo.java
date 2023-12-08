package com.example.gps_g11.Data.Objetivo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Objetivo implements Serializable {
    private String nome, descricao;
    private double valor, valorObtido;
    private int prioridade;
    private LocalDate dataLimite;

    public Objetivo(String nome, String descricao, double valor, int prioridade){
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.valorObtido = 0;
        this.prioridade = prioridade;
        dataLimite = null;
    }
    public Objetivo(String nome, String descricao, double valor, int prioridade, LocalDate dataLimite){
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.valorObtido = 0;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
    }
    public Objetivo(String nome, double valor, int prioridade){
        this.nome = nome;
        this.descricao = "";
        this.valor = valor;
        this.valorObtido = 0;
        this.prioridade = prioridade;
        dataLimite = null;
    }
    public Objetivo(String nome, double valor, int prioridade,LocalDate dataLimite){
        this.nome = nome;
        this.descricao = "";
        this.valor = valor;
        this.valorObtido = 0;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Double getValor() {
        return valor;
    }
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    public int getPrioridade() {
        return prioridade;
    }
    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }
    public LocalDate getDataLimite() {
        return dataLimite;
    }
    public Double addToGoal(Double amount){
        if(amount > valor-valorObtido) { //adicionou mais do q era preciso
            valorObtido = valor; // valor obtido = valor necessario
            return amount - valor; // sobra
        }
        valorObtido +=amount;
        return 0.0; //n restou nada!
    }
    public boolean isFullfiled(){
        if(valorObtido == valor) return true; //ja alcançou o objetivo!
        return false; //ainda falta..
    }
    public Double getMissingValue(){return valor-valorObtido;} //oq falta p completar
    public Double getCurrentValue(){return valorObtido;} //oq tem agr
}
