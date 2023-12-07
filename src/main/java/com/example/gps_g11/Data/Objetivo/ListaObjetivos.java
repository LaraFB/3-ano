package com.example.gps_g11.Data.Objetivo;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ListaObjetivos implements Serializable {
    private List<Objetivo> objetivos;

    public ListaObjetivos(){
        objetivos = new ArrayList<>();
    }

    public boolean isEmpty(){
        return objetivos.isEmpty();
    }

    public int getSize(){
        return objetivos.size();
    }

    public boolean addObjetivo(Objetivo o){
        return objetivos.add(o);
    }
    public boolean addObjetivo(String nome, String descricao, double valor, int prioridade) {
        Objetivo o = new Objetivo(nome, descricao, valor, prioridade);
        boolean bool = objetivos.add(o);
        sort();
        return bool;
    }
    public boolean addObjetivo(String nome, String descricao, double valor, int prioridade, LocalDate dataLimite) {
        Objetivo o = new Objetivo(nome, descricao, valor, prioridade,dataLimite);
        boolean bool = objetivos.add(o);
        sort();
        return bool;
    }
    public boolean addObjetivo(String nome, double valor, int prioridade){
        Objetivo o = new Objetivo(nome,valor, prioridade);
        boolean bool = objetivos.add(o);
        sort();
        return bool;
    }
    public boolean addObjetivo(String nome, double valor, int prioridade, LocalDate dataLimite){
        Objetivo o = new Objetivo(nome,valor, prioridade,dataLimite);
        boolean bool = objetivos.add(o);
        sort();
        return bool;
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
        sort();
        return true;
    }

    public boolean editObjetivo(String nome, String descricao){
        if(getObjetivo(nome) == null || descricao.isEmpty() || descricao == null)
            return false;

        getObjetivo(nome).setDescricao(descricao);
        sort();
        return true;
    }

    public boolean editObjetivo(int index, double valor){
        if(getObjetivo(index) == null || valor <= 0)
            return false;

        getObjetivo(index).setValor(valor);
        sort();
        return true;
    }

    public boolean deleteObjetivo(int index){
        if(getObjetivo(index) == null)
            return false;

        objetivos.remove(index);
        sort();
        return true;
    }

    public boolean deleteObjetivo(String nome){
        if(getObjetivo(nome) == null || nome.isEmpty() || nome == null)
            return false;

        objetivos.remove(getObjetivo(nome));
        sort();
        return true;
    }

    //ja atingiu o objetivo?
    public boolean isFullfiled(int index){return getObjetivo(index).isFullfiled();}
    public boolean isFullfiled(String nome){return getObjetivo(nome).isFullfiled();}

    //qt falta guardar
    public Double howMuchLeft(int index){return getObjetivo(index).getMissingValue();}
    public Double howMuchLeft(String nome){return getObjetivo(nome).getMissingValue();}

    //qt ja guardou
    public Double currentValue(int index){return getObjetivo(index).getCurrentValue();}
    public Double currentValue(String nome){return getObjetivo(nome).getCurrentValue();}

    private void sort(){
        Comparator<Objetivo> comparator = new Comparator<Objetivo>() {
            @Override
            public int compare(Objetivo o1, Objetivo o2) {
                long diasO1 = Duration.between(o1.getDataLimite(),LocalDate.now()).toDays();
                long diasO2 = Duration.between(o2.getDataLimite(),LocalDate.now()).toDays();

                double prioridadeO1 = diasO1*o1.getPrioridade() + o1.getMissingValue()*o1.getPrioridade();
                double prioridadeO2 = diasO2*o2.getPrioridade() + o2.getMissingValue()*o2.getPrioridade();

                if(prioridadeO1 == prioridadeO2) return 0;
                if(prioridadeO1 > prioridadeO2) return -1;
                else return 1;
            }
        };

        Collections.sort(objetivos,comparator);
    }
    public void sort(LocalDate now){
        Comparator<Objetivo> comparator = new Comparator<Objetivo>() {
            @Override
            public int compare(Objetivo o1, Objetivo o2) {
                double prioridadeO1, prioridadeO2;
                if(o1.getDataLimite() != null && o2.getDataLimite() != null) {
                    long diasO1 =  ChronoUnit.DAYS.between(o1.getDataLimite(), now);
                    long diasO2 = ChronoUnit.DAYS.between(o2.getDataLimite(), now);

                    prioridadeO1 = diasO1 * (11 - o1.getPrioridade()) + o1.getMissingValue() * (11 - o1.getPrioridade());
                    prioridadeO2 = diasO2 * (11 - o2.getPrioridade()) + o2.getMissingValue() * (11 - o2.getPrioridade());
                }
                else{
                    prioridadeO1 = o1.getMissingValue() * (11- o1.getPrioridade());
                    prioridadeO2 = o2.getMissingValue() * (11- o2.getPrioridade());
                }

                if(prioridadeO1 == prioridadeO2) return 0;
                if(prioridadeO1 > prioridadeO2) return -1;
                else return 1;
            }
        };

        Collections.sort(objetivos,comparator);
    }
}
