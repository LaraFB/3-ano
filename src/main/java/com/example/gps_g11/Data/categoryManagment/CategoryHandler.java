package com.example.gps_g11.Data.categoryManagment;

import java.util.ArrayList;
import java.util.List;

public class CategoryHandler {
    private List<Category> categories;

    public CategoryHandler(){
        this.categories = new ArrayList<>();
    }

    public List<Category> getCategories() { return categories; }

    //adicionar categoria
    public boolean addCategory(Category c){
        //validation:
        for(Category check : categories)
            if(check.equals(c)) return false; //ja existe, n adiciona

        categories.add(c);
        return true;
    }
    public boolean addCategory(String name, String description){
        //validation:
        for(Category check : categories)
            if(check.getName().equals(name)) return false; //ja existe, n adiciona

        Category c = new Category(name,description);
        categories.add(c);
        return true;
    }
    public boolean addCategory(String name){
        //validation:
        for(Category check : categories)
            if(check.getName().equals(name)) return false; //ja existe, n adiciona

        Category c = new Category(name);
        categories.add(c);
        return true;
    }

    //remover categoria
    public boolean removeCategory(Category c){
        //validation:
        if(c == null) return false;

        return categories.remove(c);
    }
    public boolean removeCategory(int i){
        //validation:
        if(i>categories.size() || i<0)
            return false; //fora da lista

        return removeCategory(categories.get(i));
    }
    public boolean removeCategory(String name){ //por nome
        //validation:
        if(name == null)
            return false;

        for(Category c : categories)
            if(c.getName().equals(name)) return removeCategory(c);

        return false; //se n encontrou
    }

    //editar categoria
    public boolean editCategoryName(String oldName, String newName){ //por nome
        //validation:
        if(oldName == null || newName == null) return false;

        for(Category c : categories)
            if(c.getName().equals(oldName)){
                c.setName(newName);
                return true;
            }
        return false; //se n encontrou
    }

    public boolean editCategoryDescription(String name, String newDescription){ //por nome
        //validation:
        if(name == null || newDescription == null) return false;

        for(Category c : categories)
            if(c.getName().equals(name)){
                c.setDescription(newDescription);
                return true;
            }
        return false; //se n encontrou
    }

    public boolean editCategoryName(int i, String newName){ //por index
        //validation:
        if(i>categories.size() || i<0 || newName == null)
            return false;

        categories.get(i).setName(newName);
        return true;
    }

    public boolean editCategoryDescription(int i, String newDescription){ //por index
        //validation:
        if(i>categories.size() || i<0 || newDescription == null)
            return false;

        categories.get(i).setDescription(newDescription);
        return true;
    }

    public Category getCategory(int i){
        if(i>categories.size() || i<0)
            return null;
        else
            return categories.get(i);
    }
    public String getCategoryName(int i){
        if(i>categories.size() || i<0)
            return null;
        else
            return categories.get(i).getName();
    }
    public String getCategoryDescription(int i){
        if(i>categories.size() || i<0)
            return null;
        else
            return categories.get(i).getDescription();
    }

    public boolean isEmpty(){
        return categories.isEmpty();
    }


}
