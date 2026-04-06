package br.com.ucsal.olimpiadas.ui;

import br.com.ucsal.olimpiadas.domain.Participante;
import br.com.ucsal.olimpiadas.service.OlimpiadaService;
import java.util.List;


public class ParticipanteUI {
    private final OlimpiadaService service;
    private final EntradaDados entrada;

    public ParticipanteUI(OlimpiadaService service, EntradaDados entrada) {
        this.service = service;
        this.entrada = entrada;
    }

    public void cadastrar() {
        String nome = entrada.obterString("Nome: ");
        if (nome.isBlank()) {
            System.out.println("Nome inválido");
            return;
        }
        
        String email = entrada.obterString("Email (opcional): ");
        
        Participante p = new Participante();
        p.setNome(nome);
        p.setEmail(email);
        service.getParticipanteRepo().salvar(p);
        System.out.println("✓ Participante cadastrado");
    }

    public Long escolher() {
        List<Participante> lista = service.getParticipanteRepo().listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum participante cadastrado");
            return null;
        }

        System.out.println("\nParticipantes:");
        for (Participante p : lista) {
            System.out.printf("  %d) %s%n", p.getId(), p.getNome());
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
