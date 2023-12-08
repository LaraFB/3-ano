package com.example.gps_g11.Data.Categoria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoriaEntradasTest {

    @Test
    public void testGetNome() {
        CategoriaEntradas categoria = new CategoriaEntradas("Alimentação", "Despesas com comida", 50.0);
        assertEquals("Alimentação", categoria.getNome());
    }

    @Test
    public void testSetNome() {
        CategoriaEntradas categoria = new CategoriaEntradas("Alimentação", "Despesas com comida", 50.0);
        categoria.setNome("Transporte");
        assertEquals("Transporte", categoria.getNome());
    }

    @Test
    public void testGetDescricao() {
        CategoriaEntradas categoria = new CategoriaEntradas("Alimentação", "Despesas com comida", 50.0);
        assertEquals("Despesas com comida", categoria.getDescricao());
    }

    @Test
    public void testSetDescricao() {
        CategoriaEntradas categoria = new CategoriaEntradas("Alimentação", "Despesas com comida", 50.0);
        categoria.setDescricao("Despesas com transporte");
        assertEquals("Despesas com transporte", categoria.getDescricao());
    }

    @Test
    public void testGetValor() {
        CategoriaEntradas categoria = new CategoriaEntradas("Alimentação", "Despesas com comida", 50.0);
        assertEquals(50.0, categoria.getValor(), 0.01);
    }

    @Test
    public void testSetValor() {
        CategoriaEntradas categoria = new CategoriaEntradas("Alimentação", "Despesas com comida", 50.0);
        categoria.setValor(75.0);
        assertEquals(75.0, categoria.getValor(), 0.01);
    }
}
