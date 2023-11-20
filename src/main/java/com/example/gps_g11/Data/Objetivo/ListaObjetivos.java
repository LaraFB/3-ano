package com.example.gps_g11.Data.Objetivo;
import java.util.ArrayList;
import java.util.List;

public class ListaObjetivos {
    private List<Objetivo> objetivos;

    public ListaObjetivos(){
        objetivos = new ArrayList<>();
    }

    public boolean addObjetivo(Objetivo o){
        return objetivos.add(o);
    }
    public boolean addObjetivo(String nome, String descricao, double valor) {
        Objetivo o = new Objetivo(nome, descricao, valor);
        return objetivos.add(o);
    }
    public boolean addObjetivo(String nome, double valor){
        Objetivo o = new Objetivo(nome,valor);
        return objetivos.add(o);
    }

    public Objetivo getObjetivo(int index){
        return objetivos.get(index);
    }
    public Objetivo getObjetivo(String nome){
        for(Objetivo o : objetivos)
            if(o.getNome().compareTo(nome) == 0)
                return o;

        return null;
    }

    public boolean editObjetivo(int index, String nome){
        if(getObjetivo(index) == null || nome.isEmpty() || nome == null)
            return false;

        getObjetivo(index).setNome(nome);
        return true;
    }

    public boolean editObjetivo(String nome, String descricao){
        if(getObjetivo(nome) == null || descricao.isEmpty() || descricao == null)
            return false;

        getObjetivo(nome).setDescricao(descricao);
        return true;
    }

    public boolean editObjetivo(int index, double valor){
        if(getObjetivo(index) == null || valor <= 0)
            return false;

        getObjetivo(index).setValor(valor);
        return true;
    }

    public boolean deleteObjetivo(int index){
        if(getObjetivo(index) == null)
            return false;

        objetivos.remove(index);
        return true;
    }

    public boolean deleteObjetivo(String nome){
        if(getObjetivo(nome) == null || nome.isEmpty() || nome == null)
            return false;

        objetivos.remove(getObjetivo(nome));
        return true;
    }

}
