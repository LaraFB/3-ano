package com.example.gps_g11.Data;

import com.example.gps_g11.Data.Budget.Budget;
import com.example.gps_g11.Data.Budget.Saldo;
import com.example.gps_g11.Data.Objetivo.ListaObjetivos;
import com.example.gps_g11.Data.ToDos.ToDo;
import com.example.gps_g11.Data.ToDos.ToDoList;
import com.example.gps_g11.Data.Transacao.HistoricoTransacoes;
import com.example.gps_g11.Data.Categoria.ListaCategorias;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContextData implements Serializable {
    private HistoricoTransacoes historicoTransacoes;
    private ListaCategorias listaCategorias;
    private ListaObjetivos listaObjetivos;
    private ToDoList listaNotificacoes;
    private Saldo saldo;
    private LocalDate date;

    public ContextData() {
        this.listaCategorias = new ListaCategorias();
        this.saldo = new Saldo();
        this.historicoTransacoes = new HistoricoTransacoes();
        this.listaObjetivos = new ListaObjetivos();
        this.listaNotificacoes = new ToDoList();
        date= LocalDate.from(LocalDateTime.now());
        //date = LocalDate.of(2023,12,15);
    }

    public HistoricoTransacoes getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    public ListaCategorias getListaCategorias() {
        return listaCategorias;
    }
    public ListaObjetivos getListaObjetivos() {
        return listaObjetivos;
    }
    public ToDoList getListaNotificacoes() {
        return listaNotificacoes;
    }

    public Saldo getSaldo() {
        return saldo;
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("ContextData saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ContextData loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ContextData) ois.readObject();
        } catch (FileNotFoundException ignored) {
            return new ContextData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ContextData();
        }
    }


    public LocalDate getData() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
