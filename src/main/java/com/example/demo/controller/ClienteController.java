package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClienteRequestDTO;
import com.example.demo.dto.ClienteResponseDTO;
import com.example.demo.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Cadastro e consulta de clientes da cafeteria")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    public List<ClienteResponseDTO> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente pelo id")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    public ClienteResponseDTO buscarClientePeloId(@PathVariable Long id) {
        return clienteService.buscarClientePeloId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um novo cliente")
    @ApiResponse(responseCode = "409", description = "E-mail já cadastrado", content = @Content)
    public ClienteResponseDTO criarCliente(@Valid @RequestBody ClienteRequestDTO dto) {
        return clienteService.criarCliente(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar os dados de um cliente")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    @ApiResponse(responseCode = "409", description = "E-mail já cadastrado em outro cliente", content = @Content)
    public ClienteResponseDTO atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {
        return clienteService.atualizarCliente(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir um cliente (e o seu cartão fidelidade, se houver)")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    @ApiResponse(responseCode = "409", description = "Cliente possui pedidos", content = @Content)
    public void deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }
}
