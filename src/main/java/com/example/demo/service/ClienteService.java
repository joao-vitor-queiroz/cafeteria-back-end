package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePeloId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(Long id, Cliente cliente) {
        Cliente antigo = buscarClientePeloId(id);

        antigo.setId(cliente.getId());
        antigo.setNome(cliente.getNome());
        antigo.setEmail(cliente.getEmail());
        antigo.setTelefone(cliente.getTelefone());
        antigo.setDataNascimento(cliente.getDataNascimento());
        antigo.setEndereco(cliente.getEndereco());

        return clienteRepository.save(antigo);
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
