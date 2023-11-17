package com.example.gps_g11.Data.categoryManagment;

import java.io.Serializable;

public class Category implements Serializable {
    private String name, description;

    public Category(String name){
        //validation:
        if(name != null) this.name = name;
        else this.name = "";

        this.description = "";
    }

    public Category(String name, String description){
        if(name != null) this.name = name;
        else this.name = "";

        if(description != null) this.description = description;
        else this.description = "";
    }

    public void setName(String name) {
        if(name != null) this.name = name;
    }
    public void setDescription(String description) {
        if(description != null) this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}
