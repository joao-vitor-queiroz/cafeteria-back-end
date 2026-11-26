package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Quantidade de pontos a acumular ou resgatar")
public record PontosRequestDTO(

        @Schema(example = "10")
        @NotNull(message = "A quantidade de pontos é obrigatória")
        @Positive(message = "A quantidade de pontos deve ser maior que zero")
        Integer pontos) {
}
