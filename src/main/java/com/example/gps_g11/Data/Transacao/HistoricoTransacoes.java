package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.Categoria;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HistoricoTransacoes implements Serializable {
    private List<Transacao> transacoes;

    public HistoricoTransacoes() {
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void removerTransacao(int id) {
        transacoes.removeIf(t -> t.getId() == id);
    }

    public void editarTransacao(int id, String novaDescricao, LocalDate novaData, double novoMontante, Categoria categoria, boolean novaRecorrencia) {
        for (Transacao transacao : transacoes) {
            if (transacao.getId() == id && "Despesa".equals(transacao.getTipo())) {
                transacao.setDescricao(novaDescricao);
                transacao.setData(novaData);
                transacao.setMontante(novoMontante);
                transacao.setCategoria(categoria);
                transacao.setRecorrente(novaRecorrencia);
                break;
            }
        }
    }

    public List<Transacao> buscarDespesas() {
        return transacoes.stream().filter(transacao -> "Despesa".equals(transacao.getTipo())).collect(Collectors.toList());
    }

    public List<Transacao> buscarEntradas() {
        return transacoes.stream().filter(transacao -> "Entrada".equals(transacao.getTipo())).collect(Collectors.toList());
    }

    public List<Transacao> buscarTodasTransacoes() {
        return new ArrayList<>(transacoes);
    }

    public Transacao buscarPorId(int id) {
        return transacoes.stream().filter(transacao -> transacao.getId() == id).findFirst().orElse(null);
    }

    public List<Transacao> buscarPorData(LocalDate data) {
        return transacoes.stream().filter(transacao -> transacao.getData().equals(data)).collect(Collectors.toList());
    }

    public List<Transacao> buscarPorCategoria(Categoria categoria) {
        return transacoes.stream().filter(transacao -> transacao.getCategoria().equals(categoria)).collect(Collectors.toList());
    }

    public List<Transacao> realizarPesquisa(String tipoTransacao, String categoria, LocalDate data, String ordenacao) {
        List<Transacao> resultadoPesquisa = new ArrayList<>(transacoes);
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

    private List<Transacao> ordenarTransacoes(List<Transacao> transacoes, String ordenacao) {
        if(ordenacao == null){
            return transacoes;
        }
        switch (ordenacao) {
            case "Categoria por ordem alfabética":
                return transacoes.stream().sorted(Comparator.comparing(transacao -> transacao.getCategoria().getNome())).collect(Collectors.toList());
            case "Categoria por ordem inversa alfabética":
                return transacoes.stream()
                        .sorted(Comparator.comparing((Transacao transacao) -> transacao.getCategoria().getNome()).reversed()).collect(Collectors.toList());
            case "Data por ordem crescente":
                return transacoes.stream().sorted(Comparator.comparing(Transacao::getData)).collect(Collectors.toList());
            case "Data por ordem decrescente":
                return transacoes.stream().sorted(Comparator.comparing(Transacao::getData).reversed()).collect(Collectors.toList());
            case "Montante por ordem crescente":
                return transacoes.stream().sorted(Comparator.comparing(Transacao::getMontante)).collect(Collectors.toList());
            case "Montante por ordem decrescente":
                return transacoes.stream().sorted(Comparator.comparing(Transacao::getMontante).reversed()).collect(Collectors.toList());
            default:
                return transacoes;
        }
    }

}
