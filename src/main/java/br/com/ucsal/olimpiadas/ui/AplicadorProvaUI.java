package br.com.ucsal.olimpiadas.ui;

import br.com.ucsal.olimpiadas.domain.Questao;
import br.com.ucsal.olimpiadas.domain.Resposta;
import br.com.ucsal.olimpiadas.domain.Tentativa;
import br.com.ucsal.olimpiadas.domain.ValidadorAlternativa;
import br.com.ucsal.olimpiadas.service.OlimpiadaService;
import java.util.List;

public class AplicadorProvaUI {
    private final OlimpiadaService service;
    private final EntradaDados entrada;
    private final TabuleiroRenderer renderer;
    private final ParticipanteUI participanteUI;
    private final ProvaUI provaUI;

    public AplicadorProvaUI(OlimpiadaService service, EntradaDados entrada, 
                           TabuleiroRenderer renderer, ParticipanteUI pUI, ProvaUI prUI) {
        this.service = service;
        this.entrada = entrada;
        this.renderer = renderer;
        this.participanteUI = pUI;
        this.provaUI = prUI;
    }

    public void aplicar() {
        Long participanteId = participanteUI.escolher();
        if (participanteId == null) return;

        Long provaId = provaUI.escolher();
        if (provaId == null) return;

        List<Questao> questoes = service.buscarQuestoesPorProva(provaId);
        if (questoes.isEmpty()) {
            System.out.println("Esta prova não possui questões cadastradas");
            return;
        }

        Tentativa tentativa = new Tentativa();
        tentativa.setParticipanteId(participanteId);
        tentativa.setProvaId(provaId);

        System.out.println("\n--- Início da Prova ---");

        for (Questao q : questoes) {
            System.out.println("\nQuestão #" + q.getId());
            System.out.println(q.getEnunciado());

            if (q.getFenInicial() != null) {
                System.out.println("Posição inicial:");
                renderer.renderizar(q.getFenInicial());
            }

            for (String alt : q.getAlternativas()) {
                System.out.println(alt);
            }

            char marcada;
            try {
                marcada = ValidadorAlternativa.normalizar(entrada.obterCaractere("Sua resposta (A–E): "));
            } catch (Exception e) {
                System.out.println("Resposta inválida (marcando como errada)");
                marcada = 'X';
            }

            Resposta r = new Resposta();
            r.setQuestaoId(q.getId());
            r.setAlternativaMarcada(marcada);
            r.setCorreta(q.isRespostaCorreta(marcada));

            tentativa.getRespostas().add(r);
        }

        service.getTentativaRepo().salvar(tentativa);

        int nota = service.calcularNota(tentativa);
        System.out.println("\n--- Fim da Prova ---");
        System.out.printf("Nota (acertos): %d / %d%n", nota, tentativa.getRespostas().size());
    }
}
