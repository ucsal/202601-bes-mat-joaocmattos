package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.*;
import br.com.ucsal.olimpiadas.repository.Repository;
import java.util.List;

/**
 * Serviço central da aplicação que orquestra a lógica de negócio.
 * Implementa SRP (responsabilidade única): coordenar repositórios e aplicar lógica.
 * Implementa DIP: depende de abstrações (Repository), não de implementações concretas.
 */
public class OlimpiadaService {
    private final Repository<Participante> participanteRepo;
    private final Repository<Prova> provaRepo;
    private final Repository<Questao> questaoRepo;
    private final Repository<Tentativa> tentativaRepo;
    private final CalculadoraNota calculadora;

    /**
     * Construtor que injeta todas as dependências.
     * DIP: Dependency Inversion Principle
     */
    public OlimpiadaService(Repository<Participante> p, Repository<Prova> pr,
                            Repository<Questao> q, Repository<Tentativa> t) {
        this(p, pr, q, t, new CalculadoraNotaPadrao());
    }

    /**
     * Construtor que permite injetar calculadora customizada.
     * OCP: Open/Closed Principle - aberto para extensão
     */
    public OlimpiadaService(Repository<Participante> p, Repository<Prova> pr,
                            Repository<Questao> q, Repository<Tentativa> t,
                            CalculadoraNota calculadora) {
        this.participanteRepo = p;
        this.provaRepo = pr;
        this.questaoRepo = q;
        this.tentativaRepo = t;
        this.calculadora = calculadora;
    }

    /**
     * Calcula a nota de uma tentativa usando a estratégia configurada.
     */
    public int calcularNota(Tentativa tentativa) {
        List<Questao> questoes = questaoRepo.listarTodos();
        return calculadora.calcular(tentativa, questoes);
    }

    /**
     * Busca todas as questões de uma prova específica.
     */
    public List<Questao> buscarQuestoesPorProva(Long provaId) {
        return questaoRepo.listarTodos().stream()
                .filter(q -> q.getProvaId() == provaId)
                .toList();
    }

    // Getters para os repositórios
    public Repository<Participante> getParticipanteRepo() { return participanteRepo; }
    public Repository<Prova> getProvaRepo() { return provaRepo; }
    public Repository<Questao> getQuestaoRepo() { return questaoRepo; }
    public Repository<Tentativa> getTentativaRepo() { return tentativaRepo; }
}