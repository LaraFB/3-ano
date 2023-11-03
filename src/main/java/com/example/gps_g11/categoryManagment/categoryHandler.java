package com.example.gps_g11.categoryManagment;

import java.util.ArrayList;
import java.util.List;

public class categoryHandler {
    private List<category> categories;

    public categoryHandler(){
        this.categories = new ArrayList<>();
    }

    public List<category> getCategories() { return categories; }

    //adicionar categoria
    public void addCategory(category c){
        categories.add(c);
    }
    public void addCategory(String name, String description){
        category c = new category(name,description);
        categories.add(c);
    }
    public void addCategory(String name){
        category c = new category(name);
        categories.add(c);
    }

    //remover categoria
    public boolean removeCategory(category c){
        return categories.remove(c);
    }
    public boolean removeCategory(int i){
        if(i>categories.size())
            return false;

        return removeCategory(i);
    }
    public boolean removeCategory(String name){ //por nome
        for(category c : categories)
            if(c.getName().equals(name)) return removeCategory(c);

        return false; //se n encontrou
    }

    //editar categoria
    public boolean editCategoryName(String oldName, String newName){ //por nome
        for(category c : categories)
            if(c.getName().equals(oldName)){
                c.setName(newName);
                return true;
            }
        return false; //se n encontrou
    }

    public boolean editCategoryDescription(String name, String newDescription){ //por nome
        for(category c : categories)
            if(c.getName().equals(name)){
                c.setDescription(newDescription);
                return true;
            }
        return false; //se n encontrou
    }

    public boolean editCategoryName(int i, String newName){ //por index
        if(i>categories.size())
            return false;

        categories.get(i).setName(newName);
        return true;
    }

    public boolean editCategoryDescription(int i, String newDescription){ //por index
        if(i>categories.size())
            return false;

        categories.get(i).setDescription(newDescription);
        return true;
    }
}
