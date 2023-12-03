/*
package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HistoricoTransacoesTest {

    @Test
    void testadicionarTransacaoDespesa() {
        HistoricoTransacoes historico = new HistoricoTransacoes();
        Despesa transacao = criarTransacaoPadrao();
        historico.adicionarTransacaoDespesa(transacao);
        assertTrue(historico.buscarTodasTransacoes().contains(transacao));
    }

    @Test
    void testRemoverTransacao() {
        HistoricoTransacoes historico = new HistoricoTransacoes();
        Despesa transacao = criarTransacaoPadrao();
        historico.adicionarTransacaoDespesa(transacao);
        historico.removerTransacao(transacao.getId());
        assertFalse(historico.buscarTodasTransacoes().contains(transacao));
    }

    @Test
    void testEditarTransacao() {
        HistoricoTransacoes historico = new HistoricoTransacoes();
        Despesa transacao = criarTransacaoPadrao();
        historico.adicionarTransacaoDespesa(transacao);

        String novaDescricao = "Nova Descrição";
        LocalDate novaData = LocalDate.now().plusDays(1);
        double novoMontante = 50.0;
        CategoriaDespesas novaCategoria = new CategoriaDespesas(30.0, "Nova Categoria", true, false);
        boolean novaRecorrencia = true;

        historico.editarTransacao(
                transacao.getId(),
                novaDescricao,
                novaData,
                novoMontante,
                novaCategoria,
                novaRecorrencia
        );

        Despesa transacaoEditada = historico.buscarPorId(transacao.getId());
        assertEquals(novaDescricao, transacaoEditada.getDescricao());
        assertEquals(novaData, transacaoEditada.getData());
        assertEquals(novoMontante, transacaoEditada.getMontante());
        assertEquals(novaCategoria, transacaoEditada.getCategoria());
        assertEquals(novaRecorrencia, transacaoEditada.isRecorrente());
    }

    @Test
    void testBuscarDespesas() {
        HistoricoTransacoes historico = new HistoricoTransacoes();
        Despesa despesa = criarTransacaoPadrao();
        Despesa entrada = new Despesa("Entrada", "Entrada Teste", new CategoriaDespesas(20.0, "Categoria Teste", true, false), LocalDate.now(), 30.0);
        historico.adicionarTransacaoDespesa(despesa);
        historico.adicionarTransacaoDespesa(entrada);

        List<Despesa> despesas = historico.buscarDespesas();
        assertTrue(despesas.contains(despesa));
        assertFalse(despesas.contains(entrada));
    }

    @Test
    void testBuscarEntradas() {
        HistoricoTransacoes historico = new HistoricoTransacoes();
        Despesa despesa = criarTransacaoPadrao();
        Despesa entrada = new Despesa("Entrada", "Entrada Teste", new CategoriaDespesas(20.0, "Categoria Teste", true, false), LocalDate.now(), 30.0);
        historico.adicionarTransacaoDespesa(despesa);
        historico.adicionarTransacaoDespesa(entrada);

        List<Despesa> entradas = historico.buscarEntradas();
        assertTrue(entradas.contains(entrada));
        assertFalse(entradas.contains(despesa));
    }

    private Despesa criarTransacaoPadrao() {
        CategoriaDespesas categoria = new CategoriaDespesas(20.0, "Categoria Teste", true, false);
        LocalDate data = LocalDate.now();
        return new Despesa("Despesa", "Descrição Teste", categoria, data, 30.0);
    }
}
*/
