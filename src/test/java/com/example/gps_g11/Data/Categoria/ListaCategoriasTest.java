package com.example.gps_g11.Data.Categoria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaCategoriasTest {

    @Test
    public void adicionarCategoriaObjeto() {
        ListaCategorias listaCategorias = new ListaCategorias();
        Categoria categoria = new Categoria("Teste", "Descrição de teste", true);

        assertTrue(listaCategorias.adicionarCategoriaObjeto(categoria));
        assertFalse(listaCategorias.adicionarCategoriaObjeto(categoria));
    }

    @Test
    public void adicionarCategoriaNomeDescricao() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCategoriaNomeDescricao("Teste", "Descrição de teste", true));
        assertFalse(listaCategorias.adicionarCategoriaNomeDescricao("Teste", "Outra descrição", false));
    }

    @Test
    public void adicionarCateogiraNome() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCateogiraNome("Teste", true));
        assertFalse(listaCategorias.adicionarCateogiraNome("Teste", false));
    }

    @Test
    public void removerCategoriaObjeto() {
        ListaCategorias listaCategorias = new ListaCategorias();
        Categoria categoria = new Categoria("Teste", "Descrição de teste", true);

        assertTrue(listaCategorias.adicionarCategoriaObjeto(categoria));
        assertTrue(listaCategorias.removerCategoriaObjeto(categoria));
        assertFalse(listaCategorias.removerCategoriaObjeto(categoria));
        assertFalse(listaCategorias.removerCategoriaObjeto(null));
    }

    @Test
    public void removerCategoriaIndex() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCategoriaNomeDescricao("Teste", "Descrição de teste", true));
        assertTrue(listaCategorias.removerCategoriaIndex(0));
        assertFalse(listaCategorias.removerCategoriaIndex(-1));
        assertFalse(listaCategorias.removerCategoriaIndex(10));
    }

    @Test
    public void removeCategoriaNome() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCategoriaNomeDescricao("Teste", "Descrição de teste", true));
        assertTrue(listaCategorias.removeCategoriaNome("Teste"));
        assertFalse(listaCategorias.removeCategoriaNome("Teste"));
        assertFalse(listaCategorias.removeCategoriaNome(null));
    }

    @Test
    public void editarCategoria() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCategoriaNomeDescricao("Teste", "Descrição de teste", true));
        assertTrue(listaCategorias.editarCategoria("Teste", "Nova descrição"));
        assertFalse(listaCategorias.editarCategoria("CategoriaInexistente", "Nova descrição"));
        assertFalse(listaCategorias.editarCategoria("Teste", null));
        assertFalse(listaCategorias.editarCategoria(null, "Nova descrição"));
    }

    @Test
    public void editarCategoriaNomeIndex() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCategoriaNomeDescricao("Teste", "Descrição de teste", true));
        assertTrue(listaCategorias.editarCategoriaNomeIndex(0, "NovoNome"));
        assertFalse(listaCategorias.editarCategoriaNomeIndex(-1, "NovoNome"));
        assertFalse(listaCategorias.editarCategoriaNomeIndex(0, null));
    }

    @Test
    public void editCategoriaDescricaoIndex() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCategoriaNomeDescricao("Teste", "Descrição de teste", true));
        assertTrue(listaCategorias.editCategoriaDescricaoIndex(0, "NovaDescrição"));
        assertFalse(listaCategorias.editCategoriaDescricaoIndex(-1, "NovaDescrição"));
        assertFalse(listaCategorias.editCategoriaDescricaoIndex(0, null));
    }



    @Test
    public void getCategoriaPorIndex() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCategoriaNomeDescricao("Teste", "Descrição de teste", true));

        Categoria categoria = listaCategorias.getCategoriaPorIndex(0);
        assertNotNull(categoria);
        assertEquals("Teste", categoria.getNome());

        assertNull(listaCategorias.getCategoriaPorIndex(-1));
        assertNull(listaCategorias.getCategoriaPorIndex(1));
    }

    @Test
    public void getCategoriaNomePorIndex() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCategoriaNomeDescricao("Teste", "Descrição de teste", true));

        String nome = listaCategorias.getCategoriaNomePorIndex(0);
        assertNotNull(nome);
        assertEquals("Teste", nome);

        assertNull(listaCategorias.getCategoriaNomePorIndex(-1));
        assertNull(listaCategorias.getCategoriaNomePorIndex(1));
    }

    @Test
    public void getCategoraiDescricaoPorIndex() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCategoriaNomeDescricao("Teste", "Descrição de teste", true));

        String descricao = listaCategorias.getCategoraiDescricaoPorIndex(0);
        assertNotNull(descricao);
        assertEquals("Descrição de teste", descricao);

        assertNull(listaCategorias.getCategoraiDescricaoPorIndex(-1));
        assertNull(listaCategorias.getCategoraiDescricaoPorIndex(1));
    }

    @Test
    public void getCategoriaPorNome() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCategoriaNomeDescricao("Teste", "Descrição de teste", true));

        Categoria categoria = listaCategorias.getCategoriaPorNome("Teste");
        assertNotNull(categoria);
        assertEquals("Descrição de teste", categoria.getDescricao());

        assertNull(listaCategorias.getCategoriaPorNome("CategoriaInexistente"));
        assertNull(listaCategorias.getCategoriaPorNome(null));
    }
}