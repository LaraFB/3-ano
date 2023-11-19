package com.example.gps_g11.Data.Categoria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaCategorias implements Serializable {
    private List<Categoria> categorias;

    public ListaCategorias(){
        this.categorias = new ArrayList<>();


    }

    public List<Categoria> getCategorias() { return categorias; }

    //adicionar categoria
    public boolean adicionarCategoriaObjeto(Categoria c){
        //validation:
        for(Categoria check : categorias)
            if(check.equals(c)) return false; //ja existe, n adiciona

        categorias.add(c);
        return true;
    }
    public boolean adicionarCategoriaNomeDescricao(String nome,String descricao,boolean isAberto){
        //validation:
        for(Categoria check : categorias)
            if(check.getNome().equals(nome)) return false; //ja existe, n adiciona

        Categoria c = new Categoria(nome,descricao,isAberto);
        categorias.add(c);
        return true;
    }
    public boolean adicionarCateogiraNome(String nome,boolean isAberto){
        //validation:
        for(Categoria check : categorias)
            if(check.getNome().equals(nome)) return false; //ja existe, n adiciona

        Categoria c = new Categoria(nome,isAberto);
        categorias.add(c);
        return true;
    }

    //remover categoria
    public boolean removerCategoriaObjeto(Categoria c){
        //validation:
        if(c == null) return false;

        return categorias.remove(c);
    }
    public boolean removerCategoriaIndex(int i){
        //validation:
        if(i>=categorias.size() || i<0)
            return false;

        return removerCategoriaObjeto(categorias.get(i));
    }
    public boolean removeCategoriaNome(String nome){ //por nome
        //validation:
        if(nome == null)
            return false;

        for(Categoria c : categorias)
            if(c.getNome().equals(nome)) return removerCategoriaObjeto(c);

        return false; //se n encontrou
    }



    public boolean editarCategoria(String nome, String descricao){ //por nome
        //validation:
        if(nome == null || descricao == null) return false;

        for(Categoria c : categorias)
            if(c.getNome().equals(nome)){
                c.setDescricao(descricao);
                return true;
            }
        return false; //se n encontrou
    }

    public boolean editarCategoriaNomeIndex(int i, String nome){ //por index
        //validation:
        if(i>categorias.size() || i<0 || nome == null)
            return false;

        categorias.get(i).setNome(nome);
        return true;
    }

    public boolean editCategoriaDescricaoIndex(int i, String descricao){ //por index
        //validation:
        if(i>=categorias.size() || i<0 || descricao == null)
            return false;

        categorias.get(i).setDescricao(descricao);
        return true;
    }

    public Categoria getCategoriaPorIndex(int i){
        if(i>=categorias.size() || i<0)
            return null;
        else
            return categorias.get(i);
    }
    public String getCategoriaNomePorIndex(int i){
        if(i>=categorias.size() || i<0)
            return null;
        else
            return categorias.get(i).getNome();
    }
    public String getCategoraiDescricaoPorIndex(int i){
        if(i>=categorias.size() || i<0)
            return null;
        else
            return categorias.get(i).getDescricao();
    }

    public boolean isEmpty(){
        return categorias.isEmpty();
    }

    public Categoria getCategoriaPorNome(String nome){ //por nome
        //validation:
        if(nome == null)
            return null;

        for(Categoria c : categorias)
            if(c.getNome().equals(nome)) return c;

        return null; //se n encontrou
    }
}
