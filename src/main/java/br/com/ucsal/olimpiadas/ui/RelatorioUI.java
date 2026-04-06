package br.com.ucsal.olimpiadas.ui;

import br.com.ucsal.olimpiadas.service.OlimpiadaService;

/**
 * Componente UI para exibição de relatórios.
 * SRP: Responsabilidade única - exibir tentativas.
 */
public class RelatorioUI {
    private final OlimpiadaService service;

    public RelatorioUI(OlimpiadaService service) {
        this.service = service;
    }

    public void listarTentativas() {
        var tentativas = service.getTentativaRepo().listarTodos();
        
        if (tentativas.isEmpty()) {
            System.out.println("Nenhuma tentativa registrada");
            return;
        }

        System.out.println("\n--- Tentativas ---");
        for (var t : tentativas) {
            int nota = service.calcularNota(t);
            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
                t.getId(),
                t.getParticipanteId(),
                t.getProvaId(),
                nota,
                t.getRespostas().size()
            );
        }
    }
}
