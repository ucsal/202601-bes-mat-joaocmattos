package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Questao;
import br.com.ucsal.olimpiadas.domain.Tentativa;
import java.util.List;


public interface CalculadoraNota {

    int calcular(Tentativa tentativa, List<Questao> questoes);
}
