package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.CartaoFidelidade;
import com.example.demo.model.Cliente;
import com.example.demo.repository.CartaoFidelidadeRepository;
import com.example.demo.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartaoFidelidadeService {
    private final CartaoFidelidadeRepository cartaoRepository;
    private final ClienteRepository clienteRepository;

    public List<CartaoFidelidade> listarCartoes() {
        return cartaoRepository.findAll();
    }

    public CartaoFidelidade buscarCartaoPeloId(Long id) {
        return cartaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cartão fidelidade não encontrado"));
    }

    public CartaoFidelidade buscarCartaoPeloCliente(Long clienteId) {
        return cartaoRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Esse cliente não possui cartão fidelidade"));
    }

    public CartaoFidelidade criarCartao(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        if (cartaoRepository.existsByClienteId(clienteId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Esse cliente já possui cartão fidelidade");
        }

        CartaoFidelidade cartao = new CartaoFidelidade();
        cartao.setCliente(cliente);
        cartao.setPontos(0);
        return cartaoRepository.save(cartao);
    }

    public CartaoFidelidade adicionarPontos(Long id, int quantidade) {
        if (quantidade <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "A quantidade de pontos deve ser maior que zero");
        }

        CartaoFidelidade cartao = buscarCartaoPeloId(id);
        cartao.setPontos(cartao.getPontos() + quantidade);
        return cartaoRepository.save(cartao);
    }

    public CartaoFidelidade resgatarPontos(Long id, int quantidade) {
        if (quantidade <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "A quantidade de pontos deve ser maior que zero");
        }

        CartaoFidelidade cartao = buscarCartaoPeloId(id);
        if (cartao.getPontos() < quantidade) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Pontos insuficientes para o resgate");
        }

        cartao.setPontos(cartao.getPontos() - quantidade);
        return cartaoRepository.save(cartao);
    }

    public void deletarCartao(Long id) {
        cartaoRepository.delete(buscarCartaoPeloId(id));
    }
}
