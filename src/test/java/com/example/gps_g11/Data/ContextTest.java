package com.example.gps_g11.Data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContextTest {


    @Test
    public void testAdicionarCategoriaDespesaSemSaldo() {
        Context context = Context.getInstance();
        String nomeCategoria = "CategoriaTeste";
        String descricao = "Descrição teste";
        double valor = 100.0;
        boolean isAberto = true;

        int result = context.adicionarCategoriaDespesa(valor, nomeCategoria, descricao, isAberto,false);

        //assertEquals(-1, result);
        //assertFalse(context.isListaCategoriasDespesasEmpty());
        }
    @Test
    public void testAdicionarCategoriaDespesaComSaldo() {
        Context context = Context.getInstance();
        String nomeCategoria = "CategoriaTeste";
        String descricao = "Descrição teste";
        double valor = 100.0;
        boolean isAberto = true;
        context.getSaldo().getBudgetContaBancaria().setSaldoReal(120);
        context.getSaldo().setSaldoPorDistribuir(120);
        int result = context.adicionarCategoriaDespesa(valor, nomeCategoria, descricao, isAberto,false);

        //assertEquals(0, result);
        assertFalse(context.isListaCategoriasDespesasEmpty());
        assertTrue(context.getCategoriaDespesasNomes().contains(nomeCategoria));
    }

    @Test
    public void testAdicionarDinheiroCategoriaDespesa() {
        Context context = Context.getInstance();
        String nomeCategoria = "CategoriaTeste";
        String descricao = "Descrição teste";
        double valor = 100.0;
        boolean isAberto = true;
        context.getSaldo().getBudgetContaBancaria().setSaldoReal(200);
        context.getSaldo().setSaldoPorDistribuir(200);
        context.adicionarCategoriaDespesa(valor, nomeCategoria, descricao, isAberto,false);

        double valorAdicionar = 50.0;
        int result = context.adicionarDinheiroCategoriaDespesa(valorAdicionar, nomeCategoria);

        assertEquals(0, result);
        assertEquals(valor + valorAdicionar, context.getCategoriasListDespesas()
                .stream()
                .filter(cat -> cat.getNome().equals(nomeCategoria))
                .findFirst()
                .orElseThrow()
                .getValor());
    }

    @Test
    public void testAdicionarCategoriaEntrada() {
        Context context = Context.getInstance();
        String nomeCategoria = "CategoriaTeste";
        String descricao = "Descrição teste";

        context.getSaldo().getBudgetContaBancaria().setSaldoReal(200);
        context.getSaldo().setSaldoPorDistribuir(200);
        int result = context.adicionarCategoriaEntrada(nomeCategoria, descricao);

        //assertEquals(1, result);
        assertFalse(context.isListaCategoriasEntradasEmpty());
         }

    @Test
    public void testAdicionarDinheiroCategoriaEntrada() {
        Context context = Context.getInstance();
        String nomeCategoria = "CategoriaTeste";
        String descricao = "Descrição teste";
        double valor = 100.0;
        boolean isDinheiro = true;

        context.getSaldo().getBudgetContaBancaria().setSaldoReal(200);
        context.getSaldo().setSaldoPorDistribuir(200);
        context.adicionarCategoriaEntrada(nomeCategoria, descricao);

        double valorAdicionar = 50.0;
        int result = context.adicionarDinheiroCategoriaEntrada(valorAdicionar, nomeCategoria, isDinheiro);

        assertEquals(0, result);
        assertEquals(valorAdicionar, context.getCategoriasListEntradas()
                .stream()
                .filter(cat -> cat.getNome().equals(nomeCategoria))
                .findFirst()
                .orElseThrow()
                .getValor());
    }

    @Test
    public void testAdicionarDespesa() {
        Context context = Context.getInstance();
        String nomeCategoria = "CategoriaTeste";
        String descricao = "Descrição teste";
        LocalDate date = LocalDate.now();
        double montante = 50.0;
        boolean isDinheiro = true;

        context.getSaldo().getBudgetContaBancaria().setSaldoReal(200);
        context.getSaldo().setSaldoPorDistribuir(200);
        context.adicionarCategoriaDespesa(montante, nomeCategoria, descricao, true,false);

        int result = context.adicionarDespesa(nomeCategoria, descricao, date, montante, isDinheiro);

        assertEquals(0, result);
        assertFalse(context.getTransacoesDespesa().isEmpty());
        }

    @Test
    public void testAdicionarEntrada() {
        Context context = Context.getInstance();
        String nomeCategoria = "CategoriaTeste";
        String descricao = "Descrição teste";
        LocalDate date = LocalDate.now();
        double montante = 50.0;
        boolean isDinheiro = true;

        context.adicionarCategoriaEntrada(nomeCategoria, descricao);

        int result = context.adicionarEntrada(nomeCategoria, descricao, date, montante, isDinheiro);

        assertEquals(0, result);
        assertFalse(context.getTransacoesEntrada().isEmpty());
       }

    @Test
    public void testVerficacoes() {
        Context context = Context.getInstance();
        LocalDate newValue = LocalDate.now();
        context.verficacoes(newValue);
        assertEquals(newValue, context.getData());
        // Adicione mais testes conforme necessário para cobrir a lógica dentro do método verficacoes.
    }


}
