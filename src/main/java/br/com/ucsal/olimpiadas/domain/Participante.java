package br.com.ucsal.olimpiadas.domain;

/**
 * Entidade Participante.
 * Mantém compatibilidade com o modelo original.
 */
public class Participante {
	private long id;
	private String nome;
	private String email;

	// Gerador de ID estático para manter compatibilidade
	private static long proximoId = 1;

	public Participante() {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
