package com.example.gps_g11.Data.Transacao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TransacaoTest {
    //Não faz setnido fazer este
    /*@Test
    public void testGetId() {
        Transacao transacao = new Transacao("Compra de alimentos", LocalDate.now(), 50.0, true);
        assertEquals(0, transacao.getId());
    }*/

    @Test
    public void testGetSetDescricao() {
        Transacao transacao = new Transacao("Compra de alimentos", LocalDate.now(), 50.0, true);
        assertEquals("Compra de alimentos", transacao.getDescricao());

        transacao.setDescricao("Pagamento de conta de luz");
        assertEquals("Pagamento de conta de luz", transacao.getDescricao());
    }

    @Test
    public void testGetSetData() {
        Transacao transacao = new Transacao("Compra de alimentos", LocalDate.now(), 50.0, true);
        assertEquals(LocalDate.now(), transacao.getData());

        LocalDate novaData = LocalDate.of(2023, 12, 31);
        transacao.setData(novaData);
        assertEquals(novaData, transacao.getData());
    }

    @Test
    public void testGetSetMontante() {
        Transacao transacao = new Transacao("Compra de alimentos", LocalDate.now(), 50.0, true);
        assertEquals(50.0, transacao.getMontante(), 0.01);

        transacao.setMontante(75.0);
        assertEquals(75.0, transacao.getMontante(), 0.01);
    }

    @Test
    public void testIsDinheiro() {
        Transacao transacao = new Transacao("Compra de alimentos", LocalDate.now(), 50.0, true);
        assertTrue(transacao.isDinheiro());

        transacao.setDinheiro(false);
        assertFalse(transacao.isDinheiro());
    }
}
