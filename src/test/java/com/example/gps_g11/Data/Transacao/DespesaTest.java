package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.CategoriaDespesas;
import com.example.gps_g11.Data.Categoria.CategoriaEntradas;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DespesaTest {

    @Test
    public void testGetCategoria() {
        CategoriaDespesas categoria = new CategoriaDespesas("Salário", "Recebimento mensal", 3000.0,true,false);
        Despesa despesa = new Despesa("Recebimento salário", LocalDate.now(), 3000.0, categoria, true,0);

        assertEquals(categoria, despesa.getCategoria());
    }

    @Test
    public void testInheritedProperties() {
        CategoriaDespesas categoria = new CategoriaDespesas("Salário", "Recebimento mensal", 3000.0,true,false);
        Entrada entrada = new Entrada("Recebimento salário", LocalDate.now(), 3000.0, categoria, true,0);

        assertEquals("Recebimento salário", entrada.getDescricao());
        assertEquals(LocalDate.now(), entrada.getData());
        assertEquals(3000.0, entrada.getMontante(), 0.01);
        assertTrue(entrada.isDinheiro());
    }
}