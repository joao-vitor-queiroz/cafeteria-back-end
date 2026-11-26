package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados de entrada para criar um cartão fidelidade")
public record CartaoFidelidadeRequestDTO(

        @Schema(description = "Id do cliente dono do cartão", example = "1")
        @NotNull(message = "O id do cliente é obrigatório")
        Long clienteId) {
}
