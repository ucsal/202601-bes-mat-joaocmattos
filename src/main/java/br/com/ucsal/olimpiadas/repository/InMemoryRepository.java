package br.com.ucsal.olimpiadas.repository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<T> implements Repository<T> {
    protected final List<T> dados = new ArrayList<>();

    @Override
    public void salvar(T entidade) { dados.add(entidade); }

    @Override
    public List<T> listarTodos() { return new ArrayList<>(dados); }

    @Override
    public T buscarPorId(Long id) {
        return null;
    }
}
