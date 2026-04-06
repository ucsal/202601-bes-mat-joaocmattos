package br.com.ucsal.olimpiadas.repository;

import java.util.ArrayList;
import java.util.List;

public interface Repository<T> {
    void salvar(T entidade);
    List<T> listarTodos();
    T buscarPorId(Long id);
}
