package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class EntradaTest {

    @Test
    public void testGetCategoria() {
        CategoriaEntradas categoria = new CategoriaEntradas("Salário", "Recebimento mensal", 3000.0);
        Entrada entrada = new Entrada("Recebimento salário", LocalDate.now(), 3000.0, categoria, true);

        assertEquals(categoria, entrada.getCategoria());
    }

    @Test
    public void testInheritedProperties() {
        CategoriaEntradas categoria = new CategoriaEntradas("Salário", "Recebimento mensal", 3000.0);
        Entrada entrada = new Entrada("Recebimento salário", LocalDate.now(), 3000.0, categoria, true);

        assertEquals("Recebimento salário", entrada.getDescricao());
        assertEquals(LocalDate.now(), entrada.getData());
        assertEquals(3000.0, entrada.getMontante(), 0.01);
        assertTrue(entrada.isDinheiro());
    }
}
