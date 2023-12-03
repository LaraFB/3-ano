/*
package com.example.gps_g11.Data.Categoria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaTest {

    @Test
    public void testConstrutorSemDescricao() {
        CategoriaDespesas categoria = new CategoriaDespesas(100.0, "Alimentação", true, false);

        assertEquals("Alimentação", categoria.getNome());
        assertEquals("", categoria.getDescricao());
        assertEquals(100.0, categoria.getValor(), 0.001);
        assertTrue(categoria.isAberto());
        assertFalse(categoria.isPagarBolsa());
    }

    @Test
    public void testConstrutorComDescricao() {
        CategoriaDespesas categoria = new CategoriaDespesas(50.0, "Transporte", "Gastos com transporte", false, true);

        assertEquals("Transporte", categoria.getNome());
        assertEquals("Gastos com transporte", categoria.getDescricao());
        assertEquals(50.0, categoria.getValor(), 0.001);
        assertFalse(categoria.isAberto());
        assertTrue(categoria.isPagarBolsa());
    }

    @Test
    public void testSetters() {
        CategoriaDespesas categoria = new CategoriaDespesas(200.0, "Entretenimento", true, true);

        categoria.setNome("Cinema");
        assertEquals("Cinema", categoria.getNome());

        categoria.setDescricao("Ingressos e lanches");
        assertEquals("Ingressos e lanches", categoria.getDescricao());

        categoria.setValor(30.0);
        assertEquals(30.0, categoria.getValor(), 0.001);

        categoria.setAberto(false);
        assertFalse(categoria.isAberto());

        categoria.setPagarBolsa(false);
        assertFalse(categoria.isPagarBolsa());
    }

    @Test
    public void testSetNomeComNull() {
        CategoriaDespesas categoria = new CategoriaDespesas(50.0, "Saúde", true, false);
        categoria.setNome(null);

        assertEquals("Saúde", categoria.getNome());
    }

    @Test
    public void testSetDescricaoComNull() {
        CategoriaDespesas categoria = new CategoriaDespesas(50.0, "Educação", true, false);
        categoria.setDescricao(null);

        assertEquals("", categoria.getDescricao());
    }
}
*/
