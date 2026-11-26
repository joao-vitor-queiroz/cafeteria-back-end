package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity 
@AllArgsConstructor 
@Getter
@Setter
public class Cliente {
    @Id
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String dataNascimento;
    private String endereco;

    @JsonIgnore 
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedido = new ArrayList<>;
    
    @OneToOne(mappedBy = "cliente")
    private CartaoFidelidade CartaoFidelidade;
}
