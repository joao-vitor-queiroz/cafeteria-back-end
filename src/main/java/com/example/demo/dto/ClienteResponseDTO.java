package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.model.Cliente;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de saída de um cliente")
public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        LocalDate dataNascimento,
        String endereco,
        @Schema(description = "Id do cartão fidelidade do cliente (null se não possuir)")
        Long cartaoFidelidadeId) {

    public static ClienteResponseDTO fromEntity(Cliente c) {
        return new ClienteResponseDTO(
                c.getId(),
                c.getNome(),
                c.getEmail(),
                c.getTelefone(),
                c.getDataNascimento(),
                c.getEndereco(),
                c.getCartaoFidelidade() != null ? c.getCartaoFidelidade().getId() : null);
    }
}
