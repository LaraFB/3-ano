package com.example.gps_g11.Data;

import com.example.gps_g11.Data.Budget.Budget;
import com.example.gps_g11.Data.Budget.Saldo;
import com.example.gps_g11.Data.Objetivo.ListaObjetivos;
import com.example.gps_g11.Data.Transacao.HistoricoTransacoes;
import com.example.gps_g11.Data.Categoria.ListaCategorias;

import java.io.*;
import java.time.LocalDate;

public class ContextData implements Serializable {
    private HistoricoTransacoes historicoTransacoes;
    private ListaCategorias listaCategorias;
    private ListaObjetivos listaObjetivos;
    private Saldo saldo;
    private LocalDate date;

    public ContextData() {
        this.listaCategorias = new ListaCategorias();
        this.saldo = new Saldo();
        this.historicoTransacoes = new HistoricoTransacoes();
        this.listaObjetivos = new ListaObjetivos();
        date = LocalDate.of(2024,1,1);
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
