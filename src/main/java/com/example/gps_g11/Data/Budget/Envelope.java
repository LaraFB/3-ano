package com.example.gps_g11.Data.Budget;

public class Envelope {
    private String finalidade;
    private static int nextId = 1;
    private int id;
    private double valor;

    public Envelope(String finalidade, double valor) {
        this.id = nextId++;
        this.finalidade = finalidade;
        this.valor = valor;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public double getValor() {
        return valor;
    }

    public int getId() {
        return id;
    }
}