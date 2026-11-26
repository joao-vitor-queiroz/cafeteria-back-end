package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CartaoFidelidade;
import com.example.demo.service.CartaoFidelidadeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/cartaoFidelidade")
@RequiredArgsConstructor
public class CartaoFidelidadeController {
    private final CartaoFidelidadeService cartaoService;

    @GetMapping("/listarCartoes")
    public List<CartaoFidelidade> listarCartoes() {
        return cartaoService.listarCartoes();
    }

    @GetMapping("/buscarCartaoPeloId/{id}")
    public CartaoFidelidade buscarCartaoPeloId(@PathVariable Long id) {
        return cartaoService.buscarCartaoPeloId(id);
    }

    @GetMapping("/buscarCartaoPeloCliente/{clienteId}")
    public CartaoFidelidade buscarCartaoPeloCliente(@PathVariable Long clienteId) {
        return cartaoService.buscarCartaoPeloCliente(clienteId);
    }

    @PostMapping("/criarCartao/{clienteId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CartaoFidelidade criarCartao(@PathVariable Long clienteId) {
        return cartaoService.criarCartao(clienteId);
    }

    @PatchMapping("/adicionarPontos/{id}")
    public CartaoFidelidade adicionarPontos(@PathVariable Long id, @RequestParam int quantidade) {
        return cartaoService.adicionarPontos(id, quantidade);
    }

    @PatchMapping("/resgatarPontos/{id}")
    public CartaoFidelidade resgatarPontos(@PathVariable Long id, @RequestParam int quantidade) {
        return cartaoService.resgatarPontos(id, quantidade);
    }

    @DeleteMapping("/deletarCartao/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCartao(@PathVariable Long id) {
        cartaoService.deletarCartao(id);
    }
}
