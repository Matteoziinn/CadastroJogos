package com.aula;

import com.aula.models.Jogo;
import com.aula.estrutura.ListaEncadeadaSimples;
import com.aula.estrutura.FilaEncadeada;

import java.util.Scanner;

public class Main {
    private static ListaEncadeadaSimples<Jogo> listaJogos = new ListaEncadeadaSimples<>();
    private static FilaEncadeada<Jogo> filaJogos = new FilaEncadeada<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n SISTEMA DE CADASTRO DE JOGOS");
            System.out.println("1 - Cadastrar jogo");
            System.out.println("2 - Listar jogos");
            System.out.println("3 - Buscar jogo");
            System.out.println("4 - Remover jogo (Lista e Fila)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarJogo();
                case 2 -> listarJogos();
                case 3 -> buscarJogo();
                case 4 -> removerJogo();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void cadastrarJogo() {
        System.out.print("Digite o ID do jogo: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o nome do jogo: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a categoria do jogo: ");
        String categoria = scanner.nextLine();

        Jogo jogo = new Jogo(id, nome, categoria);
        listaJogos.inserirFim(jogo);
        filaJogos.enfileirar(jogo);

        System.out.println(" Jogo cadastrado com sucesso!");
    }

    private static void listarJogos() {
        System.out.println("\n📋 Jogos na Lista:");
        listaJogos.listar();
        System.out.println("\n📋 Jogos na Fila:");
        filaJogos.listar();
    }

    private static void buscarJogo() {
        System.out.print("Digite o ID ou Nome do jogo para buscar: ");
        String termo = scanner.nextLine();

        // Busca na Lista
        long inicioLista = System.nanoTime();
        Jogo encontradoLista = null;
        for (int i = 0; i < listaJogos.tamanho(); i++) {
            Jogo jogo = listaJogos.get(i);
            if (String.valueOf(jogo.getId()).equals(termo) || jogo.getNome().equalsIgnoreCase(termo)) {
                encontradoLista = jogo;
                break;
            }
        }
        long fimLista = System.nanoTime();

        // Busca na Fila
        long inicioFila = System.nanoTime();
        Jogo encontradoFila = null;
        FilaEncadeada<Jogo> copia = new FilaEncadeada<>();
        while (!filaJogos.vazia()) {
            Jogo jogo = filaJogos.desenfileirar();
            if (String.valueOf(jogo.getId()).equals(termo) || jogo.getNome().equalsIgnoreCase(termo)) {
                encontradoFila = jogo;
            }
            copia.enfileirar(jogo);
        }
        filaJogos = copia;
        long fimFila = System.nanoTime();

        System.out.println("\n🔎 Resultado da busca:");
        System.out.println("Lista -> " + (encontradoLista != null ? encontradoLista : "Não encontrado") +
                           " | Tempo: " + (fimLista - inicioLista) + " ns");
        System.out.println("Fila -> " + (encontradoFila != null ? encontradoFila : "Não encontrado") +
                           " | Tempo: " + (fimFila - inicioFila) + " ns");
    }

    private static void removerJogo() {
        if (listaJogos.vazia() || filaJogos.vazia()) {
            System.out.println("Não há jogos para remover.");
            return;
        }
        Jogo removidoLista = listaJogos.removerInicio();
        Jogo removidoFila = filaJogos.desenfileirar();

        System.out.println("\n Jogo removido da Lista: " + removidoLista);
        System.out.println(" Jogo removido da Fila: " + removidoFila);

        listarJogos();
    }
}
