package com.example.gps_g11.Data.Objetivo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ListaObjetivosTest {

    @Test
    public void testIsEmpty() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        assertTrue(listaObjetivos.isEmpty());

        listaObjetivos.addObjetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertFalse(listaObjetivos.isEmpty());
    }

    @Test
    public void testGetSize() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        assertEquals(0, listaObjetivos.getSize());

        listaObjetivos.addObjetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertEquals(1, listaObjetivos.getSize());
    }

    @Test
    public void testAddObjetivo() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        assertTrue(listaObjetivos.addObjetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1));

        assertEquals(1, listaObjetivos.getSize());
        assertNotNull(listaObjetivos.getObjetivo("Comprar um carro"));
    }

    @Test
    public void testEditObjetivo() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);

        assertTrue(listaObjetivos.editObjetivo("Comprar um carro", "Economizar para uma viagem"));
        assertEquals("Economizar para uma viagem", listaObjetivos.getObjetivo("Comprar um carro").getDescricao());
    }

    @Test
    public void testDeleteObjetivo() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);

        assertTrue(listaObjetivos.deleteObjetivo("Comprar um carro"));
        assertEquals(0, listaObjetivos.getSize());
    }

    @Test
    public void testIsFullfiled() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertFalse(listaObjetivos.isFullfiled("Comprar um carro"));

        listaObjetivos.getObjetivo("Comprar um carro").addToGoal(20000.0);
        assertTrue(listaObjetivos.isFullfiled("Comprar um carro"));
    }

    @Test
    public void testHowMuchLeft() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertEquals(20000.0, listaObjetivos.howMuchLeft("Comprar um carro"), 0.01);

        listaObjetivos.getObjetivo("Comprar um carro").addToGoal(5000.0);
        assertEquals(15000.0, listaObjetivos.howMuchLeft("Comprar um carro"), 0.01);
    }

    @Test
    public void testCurrentValue() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertEquals(0.0, listaObjetivos.currentValue("Comprar um carro"), 0.01);

        listaObjetivos.getObjetivo("Comprar um carro").addToGoal(5000.0);
        assertEquals(5000.0, listaObjetivos.currentValue("Comprar um carro"), 0.01);
    }

    /*@Test
    public void testSort() {
        ListaObjetivos listaObjetivos = new ListaObjetivos();
        listaObjetivos.addObjetivo("Viagem", "Planejar uma viagem", 1000.0, 2,LocalDate.now());
        listaObjetivos.addObjetivo("Estudos", "Estudar para o exame", 500.0, 1,LocalDate.now());
        listaObjetivos.addObjetivo("Comprar livro", "Economizar para comprar um livro", 50.0, 3,LocalDate.now());

        assertEquals("Comprar livro", listaObjetivos.getObjetivo(0).getNome());
        assertEquals("Viagem", listaObjetivos.getObjetivo(1).getNome());
        assertEquals("Estudos", listaObjetivos.getObjetivo(2).getNome());
    }*/
}
