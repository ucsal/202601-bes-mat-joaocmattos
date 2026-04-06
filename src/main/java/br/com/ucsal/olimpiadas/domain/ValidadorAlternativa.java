package br.com.ucsal.olimpiadas.domain;


public class ValidadorAlternativa {
	public static char normalizar(char c) {
		return Character.toUpperCase(c);
	}

	public static boolean ehValida(char alternativa) {
		char normalizada = normalizar(alternativa);
		return normalizada >= 'A' && normalizada <= 'E';
	}
}
