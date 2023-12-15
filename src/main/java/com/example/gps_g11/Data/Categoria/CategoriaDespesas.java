package com.example.gps_g11.Data.Categoria;

import java.io.Serializable;

public class CategoriaDespesas extends CategoriaEntradas implements Serializable{
    private boolean isAberto;
    private boolean isRecorrente;
    double valorRecorrente;
    private double oldValue = 0.0;
    boolean pago=false;

    public CategoriaDespesas(String nome, String descricao, double valor,boolean isAberto, boolean isRecorrente) {
        super(nome, descricao, valor);
        this.isAberto = isAberto;
        this.isRecorrente = isRecorrente;
    }
    public CategoriaDespesas(String nome, String descricao, double valor,boolean isAberto, boolean isRecorrente, double valorRecorrente) {
        super(nome, descricao, valor);
        this.isAberto = isAberto;
        this.isRecorrente = isRecorrente;
        if(isRecorrente)
            this.valorRecorrente = valorRecorrente;
    }

    public boolean isAberto() {
        return isAberto;
    }
    public void setAberto(boolean aberto) {
        isAberto = aberto;
    }
    public boolean isRecorrente(){return isRecorrente;}
    public boolean isPago(){return pago;}
    public void switchRecorrente(){ isRecorrente = !isRecorrente; }
    public double getValorRecorrente(){return valorRecorrente;}

    @Override
    public void setValor(double valor) {
        this.valor = valor;
        if(isRecorrente && valorRecorrente != valor)
            valorRecorrente = valor;
    }

    public void setPago(boolean x){pago=x;}

    public double getOldValue() {
        return oldValue;
    }

    public void setOldValue(double oldValue) {
        this.oldValue = oldValue;
    }

    @Override
    public String toString() {
        return "CategoriaDespesas{" +
                "isAberto=" + isAberto +
                ", isRecorrente=" + isRecorrente +
                ", valorRecorrente=" + valorRecorrente +
                ", valor=" + valor +
                '}';
    }
}
