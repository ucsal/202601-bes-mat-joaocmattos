package br.com.ucsal.olimpiadas.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade Tentativa.
 * Mantém compatibilidade com o modelo original.
 */
public class Tentativa {
	private long id;
	private long participanteId;
	private long provaId;
	private final List<Resposta> respostas = new ArrayList<>();

	// Gerador de ID estático para manter compatibilidade
	private static long proximoId = 1;

	public Tentativa() {
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

	public long getParticipanteId() {
		return participanteId;
	}

	public void setParticipanteId(long participanteId) {
		this.participanteId = participanteId;
	}

	public long getProvaId() {
		return provaId;
	}

	public void setProvaId(long provaId) {
		this.provaId = provaId;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}
}
