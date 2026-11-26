package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.model.CartaoFidelidade;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de saída de um cartão fidelidade")
public record CartaoFidelidadeResponseDTO(
        Long id,
        Integer pontos,
        LocalDate dataCriacao,
        Long clienteId,
        String clienteNome) {

    public static CartaoFidelidadeResponseDTO fromEntity(CartaoFidelidade c) {
        return new CartaoFidelidadeResponseDTO(
                c.getId(),
                c.getPontos(),
                c.getDataCriacao(),
                c.getCliente().getId(),
                c.getCliente().getNome());
    }
}
