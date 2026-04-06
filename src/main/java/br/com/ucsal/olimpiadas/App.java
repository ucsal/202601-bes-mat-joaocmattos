package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.domain.*;
import br.com.ucsal.olimpiadas.repository.*;
import br.com.ucsal.olimpiadas.service.OlimpiadaService;
import br.com.ucsal.olimpiadas.service.CalculadoraNotaPadrao;
import br.com.ucsal.olimpiadas.ui.*;
import java.util.Scanner;

/**
 * Classe principal que orquestra toda a aplicação.
 * SRP: Responsabilidade única - orquestração do fluxo principal.
 * DIP: Dependency Injection - todas as dependências são injetadas.
 */
public class App {
	private final OlimpiadaService service;
	private final EntradaDados entrada;
	private final ParticipanteUI participanteUI;
	private final ProvaUI provaUI;
	private final QuestaoUI questaoUI;
	private final AplicadorProvaUI aplicadorUI;
	private final RelatorioUI relatorioUI;
	private final Scanner scanner;

	// Contadores de ID - gerenciados pela aplicação
	private long pId = 1, prId = 1, qId = 1, tId = 1;

	public App() {
		this.scanner = new Scanner(System.in);
		this.entrada = new EntradaDadosConsole(scanner);

		// Setup das dependências (DIP: inversão de controle)
		Repository<Participante> participanteRepo = new InMemoryParticipanteRepository();
		Repository<Prova> provaRepo = new InMemoryProvaRepository();
		Repository<Questao> questaoRepo = new InMemoryQuestaoRepository();
		Repository<Tentativa> tentativaRepo = new InMemoryTentativaRepository();

		this.service = new OlimpiadaService(
			participanteRepo,
			provaRepo,
			questaoRepo,
			tentativaRepo,
			new CalculadoraNotaPadrao()
		);

		// Componentes de UI
		this.participanteUI = new ParticipanteUI(service, entrada);
		this.provaUI = new ProvaUI(service, entrada);
		this.questaoUI = new QuestaoUI(service, entrada);
		this.aplicadorUI = new AplicadorProvaUI(
			service, entrada, 
			new ConsoleChessRenderer(),
			participanteUI, provaUI
		);
		this.relatorioUI = new RelatorioUI(service);
	}

	public static void main(String[] args) {
		var app = new App();
		app.seed();
		app.menu();
	}

	private void menu() {
		while (true) {
			System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V2 - REFATORADO SOLID) ===");
			System.out.println("1) Cadastrar participante");
			System.out.println("2) Cadastrar prova");
			System.out.println("3) Cadastrar questão (A–E) em uma prova");
			System.out.println("4) Aplicar prova (selecionar participante + prova)");
			System.out.println("5) Listar tentativas (resumo)");
			System.out.println("0) Sair");
			System.out.print("> ");

			try {
				switch (scanner.nextLine()) {
					case "1" -> participanteUI.cadastrar();
					case "2" -> provaUI.cadastrar();
					case "3" -> questaoUI.cadastrar();
					case "4" -> aplicadorUI.aplicar();
					case "5" -> relatorioUI.listarTentativas();
					case "0" -> {
						System.out.println("Até logo!");
						return;
					}
					default -> System.out.println("opção inválida");
				}
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
	}

	/**
	 * Popula dados iniciais para demonstração.
	 * Mantém compatibilidade com o comportamento original.
	 */
	private void seed() {
		// Cria a prova inicial exatamente como o código antigo
		Prova prova = new Prova();
		prova.setId(prId++);
		prova.setTitulo("Olimpíada 2026 • Nível 1 • Prova A");
		service.getProvaRepo().salvar(prova);

		// Cria a questão inicial
		Questao q1 = new Questao();
		q1.setId(qId++);
		q1.setProvaId(prova.getId());
		q1.setEnunciado("""
			Questão 1 — Mate em 1.
			É a vez das brancas.
			Encontre o lance que dá mate imediatamente.
			""");
		q1.setFenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1");
		q1.setAlternativas(new String[]{"A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#"});
		q1.setAlternativaCorreta('C');
		service.getQuestaoRepo().salvar(q1);
	}
}