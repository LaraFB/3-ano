package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.Categoria;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HistoricoTransacoesTest {

    @Test
    void testAdicionarTransacao() {
        HistoricoTransacoes historico = new HistoricoTransacoes();
        Transacao transacao = criarTransacaoPadrao();
        historico.adicionarTransacao(transacao);
        assertTrue(historico.buscarTodasTransacoes().contains(transacao));
    }

    @Test
    void testRemoverTransacao() {
        HistoricoTransacoes historico = new HistoricoTransacoes();
        Transacao transacao = criarTransacaoPadrao();
        historico.adicionarTransacao(transacao);
        historico.removerTransacao(transacao.getId());
        assertFalse(historico.buscarTodasTransacoes().contains(transacao));
    }

    @Test
    void testEditarTransacao() {
        HistoricoTransacoes historico = new HistoricoTransacoes();
        Transacao transacao = criarTransacaoPadrao();
        historico.adicionarTransacao(transacao);

        String novaDescricao = "Nova Descrição";
        LocalDate novaData = LocalDate.now().plusDays(1);
        double novoMontante = 50.0;
        Categoria novaCategoria = new Categoria(30.0, "Nova Categoria", true, false);
        boolean novaRecorrencia = true;

        historico.editarTransacao(
                transacao.getId(),
                novaDescricao,
                novaData,
                novoMontante,
                novaCategoria,
                novaRecorrencia
        );

        Transacao transacaoEditada = historico.buscarPorId(transacao.getId());
        assertEquals(novaDescricao, transacaoEditada.getDescricao());
        assertEquals(novaData, transacaoEditada.getData());
        assertEquals(novoMontante, transacaoEditada.getMontante());
        assertEquals(novaCategoria, transacaoEditada.getCategoria());
        assertEquals(novaRecorrencia, transacaoEditada.isRecorrente());
    }

    @Test
    void testBuscarDespesas() {
        HistoricoTransacoes historico = new HistoricoTransacoes();
        Transacao despesa = criarTransacaoPadrao();
        Transacao entrada = new Transacao("Entrada", "Entrada Teste", new Categoria(20.0, "Categoria Teste", true, false), LocalDate.now(), 30.0);
        historico.adicionarTransacao(despesa);
        historico.adicionarTransacao(entrada);

        List<Transacao> despesas = historico.buscarDespesas();
        assertTrue(despesas.contains(despesa));
        assertFalse(despesas.contains(entrada));
    }

    @Test
    void testBuscarEntradas() {
        HistoricoTransacoes historico = new HistoricoTransacoes();
        Transacao despesa = criarTransacaoPadrao();
        Transacao entrada = new Transacao("Entrada", "Entrada Teste", new Categoria(20.0, "Categoria Teste", true, false), LocalDate.now(), 30.0);
        historico.adicionarTransacao(despesa);
        historico.adicionarTransacao(entrada);

        List<Transacao> entradas = historico.buscarEntradas();
        assertTrue(entradas.contains(entrada));
        assertFalse(entradas.contains(despesa));
    }

    private Transacao criarTransacaoPadrao() {
        Categoria categoria = new Categoria(20.0, "Categoria Teste", true, false);
        LocalDate data = LocalDate.now();
        return new Transacao("Despesa", "Descrição Teste", categoria, data, 30.0);
    }
}
