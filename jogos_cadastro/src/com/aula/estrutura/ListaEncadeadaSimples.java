package com.aula.estrutura;

public class ListaEncadeadaSimples<T> {
    private static class No<T> {
        T dado;
        No<T> prox;

        No(T dado) {
            this.dado = dado;
            this.prox = null;
        }
    }

    private No<T> inicio;
    private int tamanho;

    public ListaEncadeadaSimples() {
        this.inicio = null;
        this.tamanho = 0;
    }

    public void inserirFim(T elem) {
        No<T> novo = new No<>(elem);
        if (inicio == null) {
            inicio = novo;
        } else {
            No<T> atual = inicio;
            while (atual.prox != null) {
                atual = atual.prox;
            }
            atual.prox = novo;
        }
        tamanho++;
    }

    public T get(int pos) {
        if (pos < 0 || pos >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        No<T> atual = inicio;
        for (int i = 0; i < pos; i++) {
            atual = atual.prox;
        }
        return atual.dado;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean vazia() {
        return tamanho == 0;
    }

    public T removerInicio() {
        if (inicio == null) {
            return null;
        }
        T dado = inicio.dado;
        inicio = inicio.prox;
        tamanho--;
        return dado;
    }

    public void listar() {
        No<T> atual = inicio;
        while (atual != null) {
            System.out.println(atual.dado);
            atual = atual.prox;
        }
    }
}
