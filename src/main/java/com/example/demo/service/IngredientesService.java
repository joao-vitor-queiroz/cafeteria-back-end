package com.example.demo.service;

import com.example.demo.model.Ingredientes;
import com.example.demo.repository.IngredientesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class IngredientesService {

    private final IngredientesRepository repository;

    public IngredientesService(IngredientesRepository repository) {
        this.repository = repository;
    }

    public Ingredientes criar(Ingredientes ingrediente) {
        return repository.save(ingrediente);
    }

    public List<Ingredientes> listarTodos() {
        return repository.findAll();
    }

    public Ingredientes buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrado"));
    }

    public Ingredientes atualizar(Long id, Ingredientes ingrediente) {
        Ingredientes existente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrado"));

        existente.setNome(ingrediente.getNome());
        existente.setUnidadeMedida(ingrediente.getUnidadeMedida());
        existente.setStock(ingrediente.getStock());
        existente.setAlergenio(ingrediente.getAlergenio());

        return repository.save(existente);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrado");
        }
        repository.deleteById(id);
    }
}