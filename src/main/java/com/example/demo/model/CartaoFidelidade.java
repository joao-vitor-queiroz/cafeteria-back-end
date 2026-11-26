package com.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cartao_fidelidade")
@Getter
@Setter
@NoArgsConstructor
public class CartaoFidelidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer pontos = 0;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCriacao;

    // Lado dono da relação: a Cliente usa mappedBy = "cliente"
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false, unique = true)
    private Cliente cliente;

    @PrePersist
    void definirDataCriacao() {
        if (dataCriacao == null) {
            dataCriacao = LocalDate.now();
        }
    }

    // Evita loop infinito no JSON (Cliente <-> CartaoFidelidade)
    public Long getClienteId() {
        return cliente != null ? cliente.getId() : null;
    }
}
