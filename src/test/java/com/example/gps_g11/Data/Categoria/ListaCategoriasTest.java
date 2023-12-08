package com.example.gps_g11.Data.Categoria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListaCategoriasTest {
    //Todo:Despesas
    @Test
    public void testGetCategoriasDespesas() {
        ListaCategorias listaCategorias = new ListaCategorias();
        assertNotNull(listaCategorias.getCategoriasDespesas());
        assertEquals(0, listaCategorias.getCategoriasDespesas().size());
    }

    @Test
    public void testAdicionarCategoriaDespesas() {
        ListaCategorias listaCategorias = new ListaCategorias();
        CategoriaDespesas categoriaDespesas = new CategoriaDespesas("Transporte", "Despesas com transporte", 50.0, true,false);
        listaCategorias.adicionarCategoriaDespesas(categoriaDespesas);
        assertEquals(1, listaCategorias.getCategoriasDespesas().size());
        assertTrue(listaCategorias.getCategoriasDespesas().contains(categoriaDespesas));
    }

    @Test
    public void testAdicionarCategoriaDespesasComNomeExistente() {
        ListaCategorias listaCategorias = new ListaCategorias();
        CategoriaDespesas categoriaDespesas1 = new CategoriaDespesas("Transporte", "Despesas com transporte", 50.0, true,false);
        CategoriaDespesas categoriaDespesas2 = new CategoriaDespesas("Transporte", "Despesas com transporte", 50.0, true,false);
        listaCategorias.adicionarCategoriaDespesas(categoriaDespesas1);
        assertEquals(1, listaCategorias.getCategoriasDespesas().size());
        listaCategorias.adicionarCategoriaDespesas(categoriaDespesas2);
        assertEquals(1, listaCategorias.getCategoriasDespesas().size());
    }

    @Test
    public void testCategoriasDespesasComNomeExistente() {
        ListaCategorias listaCategorias = new ListaCategorias();
        CategoriaDespesas categoriaEntradas1 = new CategoriaDespesas("Salário", "Recebimento mensal", 1000.0,true,false);
        CategoriaDespesas categoriaEntradas2 = new CategoriaDespesas("Teste", "Recebimento mensal", 1000.0,false,false);
        assertFalse(listaCategorias.categoriasEntradasComNomeExistente("Teste"));

        listaCategorias.getCategoriasEntradas().add(categoriaEntradas1);
        assertTrue(listaCategorias.categoriasEntradasComNomeExistente("Salário"));

        listaCategorias.getCategoriasEntradas().add(categoriaEntradas2);
        assertTrue(listaCategorias.categoriasEntradasComNomeExistente("Salário"));
    }
    //TODO: Entradas
    @Test
    public void testGetCategoriasEntradas() {
        ListaCategorias listaCategorias = new ListaCategorias();
        assertNotNull(listaCategorias.getCategoriasEntradas());
        assertEquals(0, listaCategorias.getCategoriasEntradas().size());
    }

    @Test
    public void testAdicionarCategoriaEntradas() {
        ListaCategorias listaCategorias = new ListaCategorias();
        CategoriaEntradas categoriaEntradas = new CategoriaEntradas("Salário", "Recebimento mensal", 1000.0);
        assertEquals(1, listaCategorias.adicionarCategoriaEntradas(categoriaEntradas));
        assertEquals(1, listaCategorias.getCategoriasEntradas().size());
        assertTrue(listaCategorias.getCategoriasEntradas().contains(categoriaEntradas));
    }

    @Test
    public void testAdicionarCategoriaEntradasComNomeExistente() {
        ListaCategorias listaCategorias = new ListaCategorias();
        CategoriaEntradas categoriaEntradas1 = new CategoriaEntradas("Salário", "Recebimento mensal", 1000.0);
        CategoriaEntradas categoriaEntradas2 = new CategoriaEntradas("Salário", "Recebimento mensal", 1000.0);
        assertEquals(1, listaCategorias.adicionarCategoriaEntradas(categoriaEntradas1));
        assertEquals(1, listaCategorias.getCategoriasEntradas().size());
        assertEquals(0, listaCategorias.adicionarCategoriaEntradas(categoriaEntradas2));
        assertEquals(1, listaCategorias.getCategoriasEntradas().size());
    }

    @Test
    public void testCategoriasEntradasComNomeExistente() {
        ListaCategorias listaCategorias = new ListaCategorias();
        CategoriaEntradas categoriaEntradas1 = new CategoriaEntradas("Salário", "Recebimento mensal", 1000.0);
        CategoriaEntradas categoriaEntradas2 = new CategoriaEntradas("Salário", "Recebimento mensal", 1000.0);
        assertFalse(listaCategorias.categoriasEntradasComNomeExistente("Salário"));
        listaCategorias.getCategoriasEntradas().add(categoriaEntradas1);
        assertTrue(listaCategorias.categoriasEntradasComNomeExistente("Salário"));
        listaCategorias.getCategoriasEntradas().add(categoriaEntradas2);
        assertTrue(listaCategorias.categoriasEntradasComNomeExistente("Salário"));
    }
}
