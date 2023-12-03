package com.example.gps_g11.Data.Transacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    /*
    public List<TransacaoDespesa> realizarPesquisa(String tipoTransacao, String categoria, LocalDate data, String ordenacao) {
        List<TransacaoDespesa> resultadoPesquisa = new ArrayList<>(transacoes);
        if (!"Sem filtro".equals(tipoTransacao)) {
            resultadoPesquisa = resultadoPesquisa.stream().filter(transacao -> tipoTransacao.equals(transacao.getTipo() + "s")).collect(Collectors.toList());
        }
        if (!"Sem filtro".equals(categoria)) {
            resultadoPesquisa = resultadoPesquisa.stream().filter(transacao -> categoria.equals(transacao.getCategoria().getNome())).collect(Collectors.toList());
        }
        if (data != null) {
            resultadoPesquisa = resultadoPesquisa.stream().filter(transacao -> data.equals(transacao.getData())).collect(Collectors.toList());
        }
        if (!"Sem filtro".equals(ordenacao)) {
            resultadoPesquisa = ordenarTransacoes(resultadoPesquisa, ordenacao);
        }
        return resultadoPesquisa;
    }

    private List<TransacaoDespesa> ordenarTransacoes(List<TransacaoDespesa> transacoes, String ordenacao) {
        if(ordenacao == null){
            return transacoes;
        }
        switch (ordenacao) {
            case "Categoria por ordem alfabética":
                return transacoes.stream().sorted(Comparator.comparing(transacao -> transacao.getCategoria().getNome())).collect(Collectors.toList());
            case "Categoria por ordem inversa alfabética":
                return transacoes.stream()
                        .sorted(Comparator.comparing((TransacaoDespesa transacao) -> transacao.getCategoria().getNome()).reversed()).collect(Collectors.toList());
            case "Data por ordem crescente":
                return transacoes.stream().sorted(Comparator.comparing(TransacaoDespesa::getData)).collect(Collectors.toList());
            case "Data por ordem decrescente":
                return transacoes.stream().sorted(Comparator.comparing(TransacaoDespesa::getData).reversed()).collect(Collectors.toList());
            case "Montante por ordem crescente":
                return transacoes.stream().sorted(Comparator.comparing(TransacaoDespesa::getMontante)).collect(Collectors.toList());
            case "Montante por ordem decrescente":
                return transacoes.stream().sorted(Comparator.comparing(TransacaoDespesa::getMontante).reversed()).collect(Collectors.toList());
            default:
                return transacoes;
        }
    }*/

}
