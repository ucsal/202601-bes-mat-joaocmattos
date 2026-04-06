package br.com.ucsal.olimpiadas.ui;

import java.util.Scanner;

/**
 * Implementação de entrada de dados via console.
 * Implementa SRP: responsável apenas por entrada de dados.
 */
public class EntradaDadosConsole implements EntradaDados {
    private final Scanner scanner;

    public EntradaDadosConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String obterString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public char obterCaractere(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Entrada vazia");
        }
        return input.charAt(0);
    }

    @Override
    public int obterInteiro(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Entrada inválida");
        }
    }
}
