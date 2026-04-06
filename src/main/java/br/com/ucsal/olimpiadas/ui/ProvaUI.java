package br.com.ucsal.olimpiadas.ui;

import br.com.ucsal.olimpiadas.domain.Prova;
import br.com.ucsal.olimpiadas.service.OlimpiadaService;
import java.util.List;

/**
 * Componente UI para operações com Prova.
 * SRP: Responsabilidade única - interface de prova.
 */
public class ProvaUI {
    private final OlimpiadaService service;
    private final EntradaDados entrada;

    public ProvaUI(OlimpiadaService service, EntradaDados entrada) {
        this.service = service;
        this.entrada = entrada;
    }

    public void cadastrar() {
        String titulo = entrada.obterString("Título da prova: ");
        if (titulo.isBlank()) {
            System.out.println("Título inválido");
            return;
        }
        
        Prova prova = new Prova();
        prova.setTitulo(titulo);
        service.getProvaRepo().salvar(prova);
        System.out.println("✓ Prova cadastrada");
    }

    public Long escolher() {
        List<Prova> lista = service.getProvaRepo().listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma prova cadastrada");
            return null;
        }

        System.out.println("\nProvas:");
        for (Prova p : lista) {
            System.out.printf("  %d) %s%n", p.getId(), p.getTitulo());
        }
        
        try {
            int id = entrada.obterInteiro("Escolha o ID: ");
            boolean existe = lista.stream().anyMatch(p -> p.getId() == id);
            if (!existe) {
                System.out.println("ID inválido");
                return null;
            }
            return (long) id;
        } catch (Exception e) {
            System.out.println("Entrada inválida");
            return null;
        }
    }
}
