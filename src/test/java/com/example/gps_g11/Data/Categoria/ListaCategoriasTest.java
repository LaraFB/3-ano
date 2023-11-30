package com.example.gps_g11.Data.Categoria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListaCategoriasTest {

    @Test
    public void testAdicionarCategoriaObjeto() {
        ListaCategorias listaCategorias = new ListaCategorias();
        Categoria categoria = new Categoria(50.0, "Compras", true, false);

        assertTrue(listaCategorias.adicionarCategoriaObjeto(categoria));
        assertEquals(1, listaCategorias.getCategorias().size());

        assertFalse(listaCategorias.adicionarCategoriaObjeto(categoria));
        assertEquals(1, listaCategorias.getCategorias().size());
    }

    @Test
    public void testAdicionarCategoriaNomeDescricao() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCategoriaNomeDescricao(30.0, "Lazer", "Atividades recreativas", true, true));
        assertEquals(1, listaCategorias.getCategorias().size());

        assertFalse(listaCategorias.adicionarCategoriaNomeDescricao(30.0, "Lazer", "Atividades recreativas", true, true));
        assertEquals(1, listaCategorias.getCategorias().size());
    }

    @Test
    public void testAdicionarCateogiraNome() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.adicionarCateogiraNome(25.0, "Saúde", false, false));
        assertEquals(1, listaCategorias.getCategorias().size());

        assertFalse(listaCategorias.adicionarCateogiraNome(25.0, "Saúde", false, false));
        assertEquals(1, listaCategorias.getCategorias().size());
    }

    @Test
    public void testRemoverCategoriaObjeto() {
        ListaCategorias listaCategorias = new ListaCategorias();
        Categoria categoria = new Categoria(40.0, "Viagem", false, true);

        listaCategorias.adicionarCategoriaObjeto(categoria);

        assertTrue(listaCategorias.removerCategoriaObjeto(categoria));
        assertEquals(0, listaCategorias.getCategorias().size());

        assertFalse(listaCategorias.removerCategoriaObjeto(categoria));
        assertEquals(0, listaCategorias.getCategorias().size());
    }

    @Test
    public void testRemoverCategoriaIndex() {
        ListaCategorias listaCategorias = new ListaCategorias();
        listaCategorias.adicionarCategoriaNomeDescricao(20.0, "Estudo", "Gastos com material escolar", true, false);

        assertTrue(listaCategorias.removerCategoriaIndex(0));
        assertEquals(0, listaCategorias.getCategorias().size());

        assertFalse(listaCategorias.removerCategoriaIndex(0));
        assertEquals(0, listaCategorias.getCategorias().size());
    }

    @Test
    public void testRemoveCategoriaNome() {
        ListaCategorias listaCategorias = new ListaCategorias();
        listaCategorias.adicionarCategoriaNomeDescricao(15.0, "Trabalho", "Gastos relacionados ao trabalho", true, false);

        assertTrue(listaCategorias.removeCategoriaNome("Trabalho"));
        assertEquals(0, listaCategorias.getCategorias().size());

        assertFalse(listaCategorias.removeCategoriaNome("Trabalho"));
        assertEquals(0, listaCategorias.getCategorias().size());
    }

    @Test
    public void testEditarCategoria() {
        ListaCategorias listaCategorias = new ListaCategorias();
        listaCategorias.adicionarCategoriaNomeDescricao(10.0, "Esportes", "Equipamentos esportivos", true, false);

        assertTrue(listaCategorias.editarCategoria("Esportes", "Equipamentos esportivos e atividades ao ar livre"));
        assertEquals("Equipamentos esportivos e atividades ao ar livre", listaCategorias.getCategoriaPorNome("Esportes").getDescricao());

        assertFalse(listaCategorias.editarCategoria("Viagem", "Novos destinos para explorar"));
    }

    @Test
    public void testEditarCategoriaNomeIndex() {
        ListaCategorias listaCategorias = new ListaCategorias();
        listaCategorias.adicionarCategoriaNomeDescricao(12.0, "Saúde", "Consultas médicas", true, false);

        assertTrue(listaCategorias.editarCategoriaNomeIndex(0, "Bem-Estar"));
        assertEquals("Bem-Estar", listaCategorias.getCategoriaPorIndex(0).getNome());

        assertFalse(listaCategorias.editarCategoriaNomeIndex(1, "Bem-Estar"));
    }

    @Test
    public void testEditCategoriaDescricaoIndex() {
        ListaCategorias listaCategorias = new ListaCategorias();
        listaCategorias.adicionarCategoriaNomeDescricao(18.0, "Cultura", "Livros e eventos culturais", true, false);

        assertTrue(listaCategorias.editCategoriaDescricaoIndex(0, "Leitura e participação em eventos culturais"));
        assertEquals("Leitura e participação em eventos culturais", listaCategorias.getCategoraiDescricaoPorIndex(0));

        assertFalse(listaCategorias.editCategoriaDescricaoIndex(1, "Arte e Cultura"));
    }

    @Test
    public void testGetCategoriaPorIndex() {
        ListaCategorias listaCategorias = new ListaCategorias();
        listaCategorias.adicionarCategoriaNomeDescricao(25.0, "Finanças", "Controle de gastos", true, false);

        assertEquals("Finanças", listaCategorias.getCategoriaPorIndex(0).getNome());

        assertNull(listaCategorias.getCategoriaPorIndex(1));
    }

    @Test
    public void testGetCategoriaNomePorIndex() {
        ListaCategorias listaCategorias = new ListaCategorias();
        listaCategorias.adicionarCategoriaNomeDescricao(22.0, "Tecnologia", "Gastos com dispositivos eletrônicos", true, false);

        assertEquals("Tecnologia", listaCategorias.getCategoriaNomePorIndex(0));

        assertNull(listaCategorias.getCategoriaNomePorIndex(1));
    }

    @Test
    public void testGetCategoraiDescricaoPorIndex() {
        ListaCategorias listaCategorias = new ListaCategorias();
        listaCategorias.adicionarCategoriaNomeDescricao(30.0, "Hobbies", "Atividades de lazer", true, false);

        assertEquals("Atividades de lazer", listaCategorias.getCategoraiDescricaoPorIndex(0));

        assertNull(listaCategorias.getCategoraiDescricaoPorIndex(1));
    }

    @Test
    public void testIsEmpty() {
        ListaCategorias listaCategorias = new ListaCategorias();

        assertTrue(listaCategorias.isEmpty());

        listaCategorias.adicionarCategoriaNomeDescricao(15.0, "Teste", "Testando", true, false);

        assertFalse(listaCategorias.isEmpty());
    }

    @Test
    public void testGetCategoriaPorNome() {
        ListaCategorias listaCategorias = new ListaCategorias();
        listaCategorias.adicionarCategoriaNomeDescricao(20.0, "Viagem", "Planejamento de viagens", true, false);

        assertEquals("Viagem", listaCategorias.getCategoriaPorNome("Viagem").getNome());

        assertNull(listaCategorias.getCategoriaPorNome("Trabalho"));
    }
}
