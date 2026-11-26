package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("api/cliente")
@RequiredArgsConstructor 
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/listarClientes")
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/buscarClientePeloId/{id}")
    public Cliente buscarClientePeloId(@PathVariable Long id) {
        return clienteService.buscarClientePeloId(id);
    }

    @PostMapping("/criarCliente")
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteService.criarCliente(cliente);
    }

    @PutMapping("/atualizarCliente/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente);
    }

    @DeleteMapping("/deletarCliente/{id}")
    public void Cliente deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }
}
