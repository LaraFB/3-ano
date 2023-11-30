package com.example.gps_g11.Data.Objetivo;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListaObjetivosTest {

    /*@Test
    public void testAddObjetivo() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        Objetivo objetivo = new Objetivo("Viagem", "Viagem dos sonhos", 5000.0);

        assertTrue(listaObjetivos.addObjetivo(objetivo));
        assertEquals(1, listaObjetivos.getSize());
    }

    @Test
    public void testAddObjetivoComParametros() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();

        assertTrue(listaObjetivos.addObjetivo("Casa", "Compra da casa própria", 100000.0));
        assertEquals(1, listaObjetivos.getSize());
    }

    @Test
    public void testEditObjetivo() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Carro", "Compra de um carro", 30000.0);

        assertTrue(listaObjetivos.editObjetivo(0, "Novo Carro"));
        assertEquals("Novo Carro", listaObjetivos.getObjetivo(0).getNome());
    }

    @Test
    public void testEditObjetivoDescricao() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Férias", "Viagem dos sonhos", 8000.0);

        assertTrue(listaObjetivos.editObjetivo("Férias", "Viagem dos sonhos em família"));
        assertEquals("Viagem dos sonhos em família", listaObjetivos.getObjetivo("Férias").getDescricao());

        assertFalse(listaObjetivos.editObjetivo("Trabalho", "Nova jornada profissional"));
        assertNull(listaObjetivos.getObjetivo("Trabalho"));
    }

    @Test
    public void testEditObjetivoValor() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Estudos", "Investimento em cursos", 5000.0);

        assertTrue(listaObjetivos.editObjetivo(0, 6000.0));
        assertEquals(6000.0, listaObjetivos.getObjetivo(0).getValor(), 0.001);
    }

    @Test
    public void testDeleteObjetivoIndex() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Aposentadoria", "Planejamento para aposentadoria", 1000000.0);

        assertTrue(listaObjetivos.deleteObjetivo(0));
        assertEquals(0, listaObjetivos.getSize());

    }

    @Test
    public void testDeleteObjetivoNome() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Negócios", "Investimento em novos negócios", 50000.0);

        assertTrue(listaObjetivos.deleteObjetivo("Negócios"));
        assertEquals(0, listaObjetivos.getSize());

        assertFalse(listaObjetivos.deleteObjetivo("Empreendedorismo"));
        assertEquals(0, listaObjetivos.getSize());
    }

    @Test
    public void testIsFullfiledIndex() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Casa Própria", "Compra da casa própria", 200000.0);

        assertFalse(listaObjetivos.isFullfiled(0));

        listaObjetivos.getObjetivo(0).addToGoal(200000.0);
        assertTrue(listaObjetivos.isFullfiled(0));
    }

    @Test
    public void testIsFullfiledNome() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Férias", "Viagem internacional", 8000.0);

        assertFalse(listaObjetivos.isFullfiled("Férias"));

        listaObjetivos.getObjetivo("Férias").addToGoal(8000.0);
        assertTrue(listaObjetivos.isFullfiled("Férias"));
    }

    @Test
    public void testHowMuchLeftIndex() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Carro Novo", "Compra de carro zero km", 30000.0);

        assertEquals(30000.0, listaObjetivos.howMuchLeft(0), 0.01);

        listaObjetivos.getObjetivo(0).addToGoal(15000.0);
        assertEquals(15000.0, listaObjetivos.howMuchLeft(0), 0.01);
    }

    @Test
    public void testHowMuchLeftNome() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Curso Online", "Investimento em educação", 1500.0);

        assertEquals(1500.0, listaObjetivos.howMuchLeft("Curso Online"), 0.01);

        listaObjetivos.getObjetivo("Curso Online").addToGoal(1000.0);
        assertEquals(500.0, listaObjetivos.howMuchLeft("Curso Online"), 0.01);
    }

    @Test
    public void testCurrentValueIndex() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Curso Online", "Investimento em educação", 1500.0);

        assertEquals(0.0, listaObjetivos.currentValue(0), 0.01);

        listaObjetivos.getObjetivo(0).addToGoal(1000.0);
        assertEquals(1000.0, listaObjetivos.currentValue(0), 0.01);
    }

    @Test
    public void testCurrentValueNome() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Projeto Pessoal", "Desenvolvimento de um projeto pessoal", 5000.0);

        assertEquals(0.0, listaObjetivos.currentValue("Projeto Pessoal"), 0.01);

        listaObjetivos.getObjetivo("Projeto Pessoal").addToGoal(2500.0);
        assertEquals(2500.0, listaObjetivos.currentValue("Projeto Pessoal"), 0.01);
    }*/
}
