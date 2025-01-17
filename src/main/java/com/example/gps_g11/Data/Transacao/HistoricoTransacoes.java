package com.example.gps_g11.Data.Transacao;

import java.io.Serializable;
import java.util.*;

public class HistoricoTransacoes implements Serializable {
    private List<Despesa> transacaoDespesas;
    private List<Entrada> transacaoEntradas;

    public HistoricoTransacoes() {
        this.transacaoDespesas = new ArrayList<>();
        this.transacaoEntradas = new ArrayList<>();
    }

    /*Todo: Entradas*/
    public List<Entrada> getTransacaoEntradas() {
        return transacaoEntradas;
    }
    public void adicionarTransacaoEntrada(Entrada transacaoEntrada) {
        transacaoEntradas.add(transacaoEntrada);
        transacaoEntradas.sort(Comparator.comparing(Entrada::getData));

    }
    public void removerTransacaoEntradas(int id) {
        Iterator<Entrada> iterator = transacaoEntradas.iterator();
        while (iterator.hasNext()) {
            Entrada transacaoEntrada = iterator.next();
            if (transacaoEntrada.getId() == id) {
                iterator.remove();
                return;
            }
        }
    }
    /*Todo: Despesas*/
    public List<Despesa> getTransacaoDespesas() {
        return transacaoDespesas;
    }
    public void adicionarTransacaoDespesas(Despesa transacaoDespesa) {
        transacaoDespesas.add(transacaoDespesa);
        transacaoDespesas.sort(Comparator.comparing(Despesa::getData));
    }
    public void removerTransacaoDespesas(int id) {
        Iterator<Despesa> iterator = transacaoDespesas.iterator();
        while (iterator.hasNext()) {
            Despesa transacaoDespesa = iterator.next();
            if (transacaoDespesa.getId() == id) {
                iterator.remove();
                return;
            }
        }
    }

}
