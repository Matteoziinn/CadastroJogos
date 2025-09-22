package com.aula.estrutura;

public class FilaEncadeada<T> {
    private static class No<T> {
        T dado;
        No<T> prox;

        No(T dado) {
            this.dado = dado;
            this.prox = null;
        }
    }

    private No<T> frente;
    private No<T> tras;
    private int tamanho;

    public FilaEncadeada() {
        this.frente = null;
        this.tras = null;
        this.tamanho = 0;
    }

    public void enfileirar(T elem) {
        No<T> novo = new No<>(elem);
        if (tras != null) {
            tras.prox = novo;
        }
        tras = novo;
        if (frente == null) {
            frente = novo;
        }
        tamanho++;
    }

    public T desenfileirar() {
        if (frente == null) {
            return null;
        }
        T dado = frente.dado;
        frente = frente.prox;
        if (frente == null) {
            tras = null;
        }
        tamanho--;
        return dado;
    }

    public boolean vazia() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }

    public void listar() {
        No<T> atual = frente;
        while (atual != null) {
            System.out.println(atual.dado);
            atual = atual.prox;
        }
    }
}
