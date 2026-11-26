package com.example.demo.dto;

public class Ingredientesdto {

    private Long idIngrediente;
    private String nome;
    private String unidadeMedida;
    private Double stock;
    private String alergenio;

    public Ingredientesdto() {
    }

    public Ingredientesdto(Long idIngrediente, String nome, String unidadeMedida, Double stock, String alergenio) {
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
}