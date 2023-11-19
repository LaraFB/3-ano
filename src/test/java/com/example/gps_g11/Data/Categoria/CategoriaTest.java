package com.example.gps_g11.Data.Categoria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    @Test
    public void construtorComNomeEAberto() {
        Categoria categoria = new Categoria("Teste", true);

        assertEquals("Teste", categoria.getNome());
        assertEquals("", categoria.getDescricao());
        assertTrue(categoria.isAberto());
    }

    @Test
    public void construtorComNomeDescricaoEAberto() {
        Categoria categoria = new Categoria("Teste", "Descrição de Teste", true);

        assertEquals("Teste", categoria.getNome());
        assertEquals("Descrição de Teste", categoria.getDescricao());
        assertTrue(categoria.isAberto());
    }

    @Test
    public void setNome() {
        Categoria categoria = new Categoria("Teste", true);

        categoria.setNome("NovoNome");

        assertEquals("NovoNome", categoria.getNome());
    }

    @Test
    public void setNomeNull() {
        Categoria categoria = new Categoria("Teste", true);

        categoria.setNome(null);

        assertEquals("Teste", categoria.getNome());
    }

    @Test
    public void setDescricao() {
        Categoria categoria = new Categoria("Teste", true);

        categoria.setDescricao("NovaDescrição");

        assertEquals("NovaDescrição", categoria.getDescricao());
    }

    @Test
    public void setDescricaoNull() {
        Categoria categoria = new Categoria("Teste", true);

        categoria.setDescricao(null);

        assertEquals("", categoria.getDescricao());
    }

    @Test
    public void setAberto() {
        Categoria categoria = new Categoria("Teste", true);

        categoria.setAberto(false);

        assertFalse(categoria.isAberto());
    }
}