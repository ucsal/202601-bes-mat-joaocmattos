package br.com.ucsal.olimpiadas.ui;

import br.com.ucsal.olimpiadas.domain.Questao;
import br.com.ucsal.olimpiadas.domain.ValidadorAlternativa;
import br.com.ucsal.olimpiadas.service.OlimpiadaService;
import java.util.List;

/**
 * Componente UI para operações com Questão.
 * SRP: Responsabilidade única - interface de questão.
 */
public class QuestaoUI {
    private final OlimpiadaService service;
    private final EntradaDados entrada;

    public QuestaoUI(OlimpiadaService service, EntradaDados entrada) {
        this.service = service;
        this.entrada = entrada;
    }

    public void cadastrar() {
        List<Questao> questoes = service.getQuestaoRepo().listarTodos();
        
        Long provaId = escolherProva();
        if (provaId == null) return;

        String enunciado = entrada.obterString("Enunciado: ");
        if (enunciado.isBlank()) {
            System.out.println("Enunciado inválido");
            return;
        }

        String[] alternativas = new String[5];
        for (int i = 0; i < 5; i++) {
            char letra = (char) ('A' + i);
            alternativas[i] = letra + ") " + entrada.obterString("Alternativa " + letra + ": ");
        }

        try {
            char correta = ValidadorAlternativa.normalizar(entrada.obterCaractere("Alternativa correta (A-E): "));
            
            Questao q = new Questao();
            q.setProvaId(provaId);
            q.setEnunciado(enunciado);
            q.setAlternativas(alternativas);
            q.setAlternativaCorreta(correta);
            
            service.getQuestaoRepo().salvar(q);
            System.out.println("✓ Questão cadastrada");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private Long escolherProva() {
        List<Questao> questoes = service.getQuestaoRepo().listarTodos();
        List<Questao> list = service.getProvaRepo().listarTodos().stream()
                .map(p -> new Questao())
                .toList();
        
        System.out.println("\nProvas:");
        service.getProvaRepo().listarTodos().forEach(p -> 
            System.out.printf("  %d) %s%n", p.getId(), p.getTitulo())
        );
        
        try {
            int id = entrada.obterInteiro("Escolha o ID da prova: ");
            return (long) id;
        } catch (Exception e) {
            System.out.println("Entrada inválida");
            return null;
        }
    }
}
