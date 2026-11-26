package com.example.demo.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados de entrada para criar ou atualizar um cliente")
public record ClienteRequestDTO(

        @Schema(example = "Maria Silva")
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres")
        String nome,

        @Schema(example = "maria@email.com")
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @Schema(example = "(81) 99999-0000")
        @Pattern(regexp = "^[0-9()\\-\\s+]{8,20}$", message = "Telefone inválido")
        String telefone,

        @Schema(example = "1998-05-20")
        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate dataNascimento,

        @Schema(example = "Rua das Flores, 123 - Olinda/PE")
        @Size(max = 255, message = "O endereço deve ter no máximo 255 caracteres")
        String endereco) {
}
