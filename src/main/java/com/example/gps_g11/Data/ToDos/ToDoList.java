package com.example.gps_g11.Data.ToDos;

import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import com.example.gps_g11.Data.Objetivo.Objetivo;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ToDoList implements Serializable {
    private List<ToDo> toDoList;

    public ToDoList() {
        this.toDoList = new ArrayList<>();
    }

    public boolean addToDo(String description, ToDo.TYPE type){
        if(description.isEmpty() || description == null || type == null)
            return false;
        try{
            for (ToDo t: toDoList) {
                if(t.getDescription().equals(description)) return false;
            }

            ToDo toDo = new ToDo(description, type);
            toDoList.add(toDo);
            sort();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean addToDo(String description, ToDo.TYPE type, String envelope){
        if(description.isEmpty() || description == null || type == null || envelope==null)
            return false;
        try{
            for (ToDo t: toDoList) {
                if(t.getDescription().equals(description)) return false;
            }

            ToDo toDo = new ToDo(description, type,envelope);
            toDoList.add(toDo);
            sort();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean removeToDo(int index){
        try{
            toDoList.remove(index);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean removeToDo(ToDo td){
        try{
            toDoList.remove(td);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean removeToDo(String desc){
        try{
            for (ToDo td:toDoList)
                if(td.getDescription().equals(desc)){
                    toDoList.remove(td);
                    return true;
                }

            return false;
        }catch (Exception e){
            return false;
        }
    }

    public void sort(){
        Comparator<ToDo> comparator = new Comparator<ToDo>() {
            @Override
            public int compare(ToDo td1, ToDo td2) {
                /* ordem de prioridade:
                 * alerta
                 * notificacao
                 * pedido ao utilizador
                 * informaçao
                 */
                if(td1.getType() == td2.getType()) return 0;
                switch (td1.getType()){
                   case ALERT -> {
                       return -1;
                   }
                   case REQUEST ->{
                       if(td2.getType().equals(ToDo.TYPE.ALERT)) return 1;
                       else return -1;
                   }
                    case NOTIFICATION -> {
                        return 1;
                    }
               }
               return 0;
            }
        };

        Collections.sort(toDoList,comparator);
    }

    public boolean isEmpty(){
        return toDoList.isEmpty();
    }
    public int size(){
        return toDoList.size();
    }

    public ToDo get(int index){
        try {
            return toDoList.get(index);
        }catch (Exception e){
            return null;
        }
    }
    public ToDo get(String descricao){
        try {
            for (ToDo t : toDoList) {
                if(t.getDescription().equals(descricao)) return t;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
    public boolean addToDo(String description, ToDo.TYPE type, String envelope,Objetivo objetivo){
        if(description.isEmpty() || description == null || type == null || envelope==null)
            return false;
        try{
            for (ToDo t: toDoList) {
                if(t.getDescription().equals(description)) return false;
            }

            ToDo toDo = new ToDo(description, type,envelope,objetivo);
            toDoList.add(toDo);
            sort();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean addToDo(String description, ToDo.TYPE type, String envelope,double valorRecorente){
        if(description.isEmpty() || description == null || type == null || envelope==null)
            return false;
        try{
            for (ToDo t: toDoList) {
                if(t.getDescription().equals(description)) return false;
            }

            ToDo toDo = new ToDo(description, type,envelope,valorRecorente);
            toDoList.add(toDo);
            sort();
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
