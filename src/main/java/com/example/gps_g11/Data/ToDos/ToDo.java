package com.example.gps_g11.Data.ToDos;

import com.example.gps_g11.Data.Objetivo.Objetivo;

import java.io.Serial;
import java.io.Serializable;

public class ToDo implements Serializable {
    public enum TYPE{
        ALERT, NOTIFICATION, REQUEST, USER_GENERATED
    }
    private String description;
    private TYPE type;
    private String envelope;
    private Objetivo objetivo;
    private double valorRecorrente;

    public ToDo(String description, TYPE type ){
        this.description = description;
        this.type = type;
        this.envelope = null;
    }
    public ToDo(String description, TYPE type, String envelope ){
        this.description = description;
        this.type = type;
        this.envelope = envelope;
    }
    public ToDo(String description, TYPE type, String envelope, Objetivo objetivo){
        this.description = description;
        this.type = type;
        this.envelope = envelope;
        this.objetivo = objetivo;
    }
    public ToDo(String description, TYPE type, String envelope, double valorRecorrente){
        this.description = description;
        this.type = type;
        this.envelope = envelope;
        this.valorRecorrente = valorRecorrente;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public TYPE getType() {
        return type;
    }
    public void setType(TYPE type) {
        this.type = type;
    }

    public String getEnvelope() {
        return envelope;
    }

    public void setEnvelope(String area) {
        this.envelope = area;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public double getValorRecorrente() {
        return valorRecorrente;
    }

    public void setValorRecorrente(double valorRecorrente) {
        this.valorRecorrente = valorRecorrente;
    }
}
