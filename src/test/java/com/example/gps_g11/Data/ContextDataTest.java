package com.example.gps_g11.Data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.time.LocalDate;

public class ContextDataTest {

    @Test
    public void testGetters() {
        ContextData contextData = new ContextData();

        assertNotNull(contextData.getHistoricoTransacoes());
        assertNotNull(contextData.getListaCategorias());
        assertNotNull(contextData.getListaObjetivos());
        assertNotNull(contextData.getSaldo());
    }

}
