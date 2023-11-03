package com.example.gps_g11.categoryManagment;

public class category {
    private String name, description;

    public category(String name){
        this.name = name;
        this.description = "";
    }

    public category(String name, String description){
        this.name = name;
        this.description = description;
    }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }

    public String getName() { return name; }
    public String getDescription() { return description; }
}
