package com.example.gps_g11.Data.Categoria;

import java.io.Serializable;
import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class ListaCategorias implements Serializable {
    private List<CategoriaDespesas> categoriasDespesas;
    private List<CategoriaEntradas> categoriasEntradas;

    public ListaCategorias() {
        this.categoriasDespesas = new ArrayList<>();
        this.categoriasEntradas = new ArrayList<>();
    }
    /*Todo: Despesas*/
    public List<CategoriaDespesas> getCategoriasDespesas() {
        return categoriasDespesas;
    }

    public void adicionarCategoriaDespesas(CategoriaDespesas novaCategoria) {
        if (!categoriaDespesasComNomeExistente(novaCategoria.getNome())) {
            categoriasDespesas.add(novaCategoria);
        }
    }

    public void removerCategoriaDespesas(String nomeCategoria) {
        Iterator<CategoriaDespesas> iterator = categoriasDespesas.iterator();
        while (iterator.hasNext()) {
            CategoriaDespesas categoria = iterator.next();
            if (categoria.getNome().equals(nomeCategoria)) {
                iterator.remove();
                return;
            }
        }
    }

    public CategoriaDespesas obterCategoriaDespesasPorNome(String nome) {
        for (CategoriaDespesas categoria : categoriasDespesas) {
            if (categoria.getNome().equals(nome)) {
                return categoria;
            }
        }
        return null;
    }

    private boolean categoriaDespesasComNomeExistente(String nome) {
        for (CategoriaDespesas categoria : categoriasDespesas) {
            if (categoria.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    /*Todo: Entradas*/
    public List<CategoriaEntradas> getCategoriasEntradas() {
        return categoriasEntradas;
    }
    public int adicionarCategoriaEntradas(CategoriaEntradas novaCategoria) {
        if (!categoriasEntradasComNomeExistente(novaCategoria.getNome())) {
            categoriasEntradas.add(novaCategoria);
            return 1;
        }
        return 0;
    }

    public void removerCategoriaEntradas(String nomeCategoria) {
        Iterator<CategoriaEntradas> iterator = categoriasEntradas.iterator();
        while (iterator.hasNext()) {
            CategoriaEntradas categoria = iterator.next();
            if (categoria.getNome().equals(nomeCategoria)) {
                iterator.remove();
                return;
            }
        }
    }
    public CategoriaEntradas obterCategoriaEntradasPorNome(String nome) {
        for (CategoriaEntradas categoria : categoriasEntradas) {
            if (categoria.getNome().equals(nome)) {
                return categoria;
            }
        }
        return null;
    }

    private boolean categoriasEntradasComNomeExistente(String nome) {
        for (CategoriaEntradas categoria : categoriasEntradas) {
            if (categoria.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }
    public List<String> obterNomesCategoriasDespesas() {
        List<String> nomes = new ArrayList<>();
        for (CategoriaDespesas categoriaDespesas : categoriasDespesas) {
            nomes.add(categoriaDespesas.getNome());
        }
        return nomes;
    }
    public List<String> obterNomesCategoriasEntradas() {
        List<String> nomes = new ArrayList<>();
        for (CategoriaEntradas categoria : categoriasEntradas) {
            nomes.add(categoria.getNome());
        }
        return nomes;
    }

}

