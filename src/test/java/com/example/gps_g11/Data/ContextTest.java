package com.example.gps_g11.Data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextTest {

   /* @Test
    public void testAdicionarDinheiroEnvelope_Sucesso() {
        ContextData contextData = new ContextData();
        Context context = Context.getInstance();
        double saldoInicial = context.getSaldoDisponivel();

        int resultado = context.adicionarDinheiroEnvelope(50.0, "CategoriaExistente");

        assertEquals(-2, resultado);
        assertEquals(100.0, context.getSaldoDisponivel());
    }
*/
    @Test
    public void testAdicionarDinheiroEnvelope_SemSaldo() {
        ContextData contextData = new ContextData();
        Context context = Context.getInstance();
        double saldoInicial = context.getSaldoDisponivel();

        int resultado = context.adicionarDinheiroEnvelope(150.0, "CategoriaExistente");

        assertEquals(-1, resultado);
        assertEquals(saldoInicial, context.getSaldoDisponivel());
    }

    @Test
    public void testAdicionarDinheiroEnvelope_CategoriaInexistente() {
        ContextData contextData = new ContextData();
        Context context = Context.getInstance();
        double saldoInicial = context.getSaldoDisponivel();

        int resultado = context.adicionarDinheiroEnvelope(50.0, "CategoriaInexistente");

       /* assertEquals(-2, resultado);*/
        assertEquals(saldoInicial, context.getSaldoDisponivel());
    }
}
