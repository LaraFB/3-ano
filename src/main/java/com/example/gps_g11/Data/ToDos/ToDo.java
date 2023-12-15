package com.example.gps_g11.Data.ToDos;

import java.io.Serial;
import java.io.Serializable;

public class ToDo implements Serializable {
    public enum TYPE{
        ALERT, NOTIFICATION, REQUEST, USER_GENERATED
    }
    private String description;
    private TYPE type;
    private String envelope;
    private Double valor;

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
    public ToDo(String description, TYPE type, String envelope,Double valor){
        this.description = description;
        this.type = type;
        this.envelope = envelope;
        this.valor = valor;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
