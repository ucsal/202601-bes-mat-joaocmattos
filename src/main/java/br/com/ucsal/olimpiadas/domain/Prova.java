package br.com.ucsal.olimpiadas.domain;

/**
 * Entidade Prova.
 * Mantém compatibilidade com o modelo original.
 */
public class Prova {
	private long id;
	private String titulo;

	// Gerador de ID estático para manter compatibilidade
	private static long proximoId = 1;

	public Prova() {
		this.id = proximoId++;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
		if (id >= proximoId) {
			proximoId = id + 1;
		}
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
