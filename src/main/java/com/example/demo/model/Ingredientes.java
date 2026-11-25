package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredientes")
public class Ingredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngrediente;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String unidadeMedida;

    @Column(nullable = false)
    private Double stock;

    @Column(nullable = false)
    private String alergenio;

    @ManyToMany(mappedBy = "ingredientes")
    private Set<Produto> produtos = new HashSet<>();

    public Ingredientes() {
    }

    public Ingredientes(Long idIngrediente, String nome, String unidadeMedida, Double stock, String alergenio) {
        this.idIngrediente = idIngrediente;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.stock = stock;
        this.alergenio = alergenio;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public String getAlergenio() {
        return alergenio;
    }

    public void setAlergenio(String alergenio) {
        this.alergenio = alergenio;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }
}