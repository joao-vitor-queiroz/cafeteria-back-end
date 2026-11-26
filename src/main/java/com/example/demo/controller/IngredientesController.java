package com.example.demo.controller;

import com.example.demo.model.Ingredientes;
import com.example.demo.service.IngredientesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
@Tag(name = "Ingredientes", description = "Controle de ingredientes e estoque")
public class IngredientesController {

    private final IngredientesService service;

    public IngredientesController(IngredientesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Ingredientes> criar(@RequestBody Ingredientes ingrediente) {
        Ingredientes criado = service.criar(ingrediente);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Ingredientes>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredientes> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredientes> atualizar(@PathVariable Long id, @RequestBody Ingredientes ingrediente) {
        return ResponseEntity.ok(service.atualizar(id, ingrediente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}