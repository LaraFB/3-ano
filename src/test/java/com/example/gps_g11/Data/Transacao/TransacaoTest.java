package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.Categoria;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoTest {

    /*@Test
    public void testConstrutorEGetters() {
        Categoria categoria = new Categoria("Comida", true);
        LocalDate data = LocalDate.now();
        Transacao transacao = new Transacao("Despesa", "Compra de alimentos", categoria, data, 50.0f);

        assertEquals(0, transacao.getId()); // O primeiro objeto criado deve ter id 0
        assertEquals("Despesa", transacao.getTipo());
        assertEquals("Compra de alimentos", transacao.getDescricao());
        assertEquals(data, transacao.getData());
        assertEquals(50.0f, transacao.getMontante(), 0.001); // Use delta para comparar floats
        assertEquals(categoria, transacao.getCategoria());
        assertFalse(transacao.isRecorrente());
    }*/

    /*@Test
    public void testSetters() {
        Categoria categoria1 = new Categoria("Comida", true);
        Categoria categoria2 = new Categoria("Transporte", false);
        LocalDate data1 = LocalDate.now();
        LocalDate data2 = LocalDate.of(2023, 1, 1);

        Transacao transacao = new Transacao("Despesa", "Compra de alimentos", categoria1, data1, 50.0f);

        transacao.setDescricao("Nova descrição");
        transacao.setData(data2);
        transacao.setMontante(75.0f);
        transacao.setCategoria(categoria2);
        transacao.setRecorrente(true);

        assertEquals("Nova descrição", transacao.getDescricao());
        assertEquals(data2, transacao.getData());
        assertEquals(75.0f, transacao.getMontante(), 0.001);
        assertEquals(categoria2, transacao.getCategoria());
        assertTrue(transacao.isRecorrente());
    }*/
}