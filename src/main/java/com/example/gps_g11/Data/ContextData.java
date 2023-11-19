package com.example.gps_g11.Data;

import com.example.gps_g11.Data.Budget.Budget;
import com.example.gps_g11.Data.Transacao.HistoricoTransacoes;
import com.example.gps_g11.Data.Categoria.ListaCategorias;

import java.io.*;

public class ContextData implements Serializable {
    private HistoricoTransacoes historicoTransacoes;

    private ListaCategorias listaCategorias;

    private Budget budget;

    public ContextData() {
        this.listaCategorias = new ListaCategorias();
        this.budget = new Budget(0);
        this.historicoTransacoes = new HistoricoTransacoes();
    }

    public HistoricoTransacoes getHistoricoTransacoes() {
        return historicoTransacoes;
    }


    public ListaCategorias getListaCategorias() {
        return listaCategorias;
    }

    public Budget getBudget() {
        return budget;
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

}
