package com.example.gps_g11.Data.ToDos;

public class ToDo {
    public enum TYPE{
        ALERT, NOTIFICATION, REQUEST
    }
    private String description;
    private TYPE type;
    private String url;

    public ToDo(String description, TYPE type ){
        this.description = description;
        this.type = type;
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
}
