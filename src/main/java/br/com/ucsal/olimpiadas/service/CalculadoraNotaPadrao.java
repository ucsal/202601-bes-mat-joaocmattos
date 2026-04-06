package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Questao;
import br.com.ucsal.olimpiadas.domain.Tentativa;
import java.util.List;


public class CalculadoraNotaPadrao implements CalculadoraNota {
    
    @Override
    public int calcular(Tentativa tentativa, List<Questao> questoes) {
        return (int) tentativa.getRespostas().stream()
                .filter(r -> r.isCorreta())
                .count();
    }
}
