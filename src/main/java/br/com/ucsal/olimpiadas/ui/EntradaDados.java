package br.com.ucsal.olimpiadas.ui;

/**
 * Interface para entrada de dados.
 * Implementa ISP (Interface Segregation Principle) - interfaces pequenas e específicas.
 */
public interface EntradaDados {
    String obterString(String prompt);
    char obterCaractere(String prompt);
    int obterInteiro(String prompt);
}
