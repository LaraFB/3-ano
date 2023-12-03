/*
package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class TransacaoTest {

    @Test
    void testSetDescricao() {
        Despesa transacao = criarTransacaoPadrao();
        transacao.setDescricao("Nova descrição");
        assertEquals("Nova descrição", transacao.getDescricao());
    }

    @Test
    void testSetData() {
        Despesa transacao = criarTransacaoPadrao();
        LocalDate novaData = LocalDate.now().plusDays(1);
        transacao.setData(novaData);
        assertEquals(novaData, transacao.getData());
    }

    @Test
    void testSetMontante() {
        Despesa transacao = criarTransacaoPadrao();
        transacao.setMontante(50.0);
        assertEquals(50.0, transacao.getMontante());
    }

    @Test
    void testSetCategoria() {
        Despesa transacao = criarTransacaoPadrao();
        CategoriaDespesas novaCategoria = new CategoriaDespesas(30.0, "Nova Categoria", true, false);
        transacao.setCategoria(novaCategoria);
        assertEquals(novaCategoria, transacao.getCategoria());
    }

    @Test
    void testSetRecorrente() {
        Despesa transacao = criarTransacaoPadrao();
        transacao.setRecorrente(true);
        assertTrue(transacao.isRecorrente());
    }

    private Despesa criarTransacaoPadrao() {
        CategoriaDespesas categoria = new CategoriaDespesas(20.0, "Categoria Teste", true, false);
        LocalDate data = LocalDate.now();
        return new Despesa("Despesa", "Descrição Teste", categoria, data, 30.0);
    }
}
*/
