package com.aula.models;

public class Jogo {
    private int id;
    private String nome;
    private String categoria;

    public Jogo(int id, String nome, String categoria) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Categoria: " + categoria;
    }
}
