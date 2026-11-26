package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartaoFidelidadeRequestDTO;
import com.example.demo.dto.CartaoFidelidadeResponseDTO;
import com.example.demo.dto.PontosRequestDTO;
import com.example.demo.service.CartaoFidelidadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cartoes-fidelidade")
@RequiredArgsConstructor
@Tag(name = "Cartão Fidelidade", description = "Cartões de fidelidade e pontos acumulados pelos clientes")
public class CartaoFidelidadeController {

    private final CartaoFidelidadeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar o cartão fidelidade de um cliente")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    @ApiResponse(responseCode = "409", description = "Cliente já possui cartão", content = @Content)
    public CartaoFidelidadeResponseDTO criar(@Valid @RequestBody CartaoFidelidadeRequestDTO dto) {
        return service.criar(dto);
    }

    @GetMapping
    @Operation(summary = "Listar todos os cartões")
    public List<CartaoFidelidadeResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cartão pelo id")
    @ApiResponse(responseCode = "404", description = "Cartão não encontrado", content = @Content)
    public CartaoFidelidadeResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Buscar o cartão de um cliente")
    @ApiResponse(responseCode = "404", description = "Cliente sem cartão", content = @Content)
    public CartaoFidelidadeResponseDTO buscarPorCliente(@PathVariable Long clienteId) {
        return service.buscarPorCliente(clienteId);
    }

    @PostMapping("/{id}/acumular")
    @Operation(summary = "Acumular pontos no cartão")
    @ApiResponse(responseCode = "404", description = "Cartão não encontrado", content = @Content)
    public CartaoFidelidadeResponseDTO acumularPontos(@PathVariable Long id, @Valid @RequestBody PontosRequestDTO dto) {
        return service.acumularPontos(id, dto);
    }

    @PostMapping("/{id}/resgatar")
    @Operation(summary = "Resgatar (gastar) pontos do cartão")
    @ApiResponse(responseCode = "404", description = "Cartão não encontrado", content = @Content)
    @ApiResponse(responseCode = "422", description = "Saldo de pontos insuficiente", content = @Content)
    public CartaoFidelidadeResponseDTO resgatarPontos(@PathVariable Long id, @Valid @RequestBody PontosRequestDTO dto) {
        return service.resgatarPontos(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir um cartão")
    @ApiResponse(responseCode = "404", description = "Cartão não encontrado", content = @Content)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
