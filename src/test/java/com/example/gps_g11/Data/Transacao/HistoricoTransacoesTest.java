package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class HistoricoTransacoesTest {

    @Test
    public void testAdicionarRemoverTransacaoEntrada() {
        HistoricoTransacoes historico = new HistoricoTransacoes();
        CategoriaEntradas categoria = new CategoriaEntradas("Salário", "Recebimento mensal", 3000.0);
        Entrada entrada = new Entrada("Recebimento salário", LocalDate.now(), 3000.0, categoria, true);

        historico.adicionarTransacaoEntrada(entrada);

        List<Entrada> transacoesEntradas = historico.getTransacaoEntradas();
        assertEquals(1, transacoesEntradas.size());

        historico.removerTransacaoEntradas(entrada.getId());

        assertEquals(0, transacoesEntradas.size());
    }

    @Test
    public void testAdicionarRemoverTransacaoDespesa() {
        HistoricoTransacoes historico = new HistoricoTransacoes();
        CategoriaDespesas categoria = new CategoriaDespesas("Alimentação", "Despesas com alimentação", 200.0, true);
        Despesa despesa = new Despesa("Compras no mercado", LocalDate.now(), 150.0, categoria, true);

        historico.adicionarTransacaoDespesas(despesa);

        List<Despesa> transacoesDespesas = historico.getTransacaoDespesas();
        assertEquals(1, transacoesDespesas.size());

        historico.removerTransacaoDespesas(despesa.getId());

        assertEquals(0, transacoesDespesas.size());
    }
}
