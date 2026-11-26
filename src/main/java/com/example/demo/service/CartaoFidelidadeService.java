package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.CartaoFidelidadeRequestDTO;
import com.example.demo.dto.CartaoFidelidadeResponseDTO;
import com.example.demo.dto.PontosRequestDTO;
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

    @Transactional
    public CartaoFidelidadeResponseDTO criar(CartaoFidelidadeRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        
        if (cartaoRepository.existsByClienteId(cliente.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O cliente já possui um cartão fidelidade");
        }

        CartaoFidelidade cartao = new CartaoFidelidade();
        cartao.setCliente(cliente);
        cartao.setPontos(0);
        cartao.setDataCriacao(LocalDate.now());

        CartaoFidelidade salvo = cartaoRepository.save(cartao);
        cliente.setCartaoFidelidade(salvo);
        return CartaoFidelidadeResponseDTO.fromEntity(salvo);
    }

    @Transactional(readOnly = true)
    public List<CartaoFidelidadeResponseDTO> listar() {
        return cartaoRepository.findAll().stream()
                .map(CartaoFidelidadeResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public CartaoFidelidadeResponseDTO buscarPorId(Long id) {
        return CartaoFidelidadeResponseDTO.fromEntity(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public CartaoFidelidadeResponseDTO buscarPorCliente(Long clienteId) {
        CartaoFidelidade cartao = cartaoRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cartão fidelidade não encontrado para este cliente"));
        return CartaoFidelidadeResponseDTO.fromEntity(cartao);
    }

    @Transactional
    public CartaoFidelidadeResponseDTO acumularPontos(Long id, PontosRequestDTO dto) {
        CartaoFidelidade cartao = buscarEntidade(id);
        cartao.setPontos(cartao.getPontos() + dto.pontos());
        return CartaoFidelidadeResponseDTO.fromEntity(cartaoRepository.save(cartao));
    }

    @Transactional
    public CartaoFidelidadeResponseDTO resgatarPontos(Long id, PontosRequestDTO dto) {
        CartaoFidelidade cartao = buscarEntidade(id);

       
        if (cartao.getPontos() < dto.pontos()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Saldo insuficiente: o cartão possui " + cartao.getPontos() + " ponto(s)");
        }

        cartao.setPontos(cartao.getPontos() - dto.pontos());
        return CartaoFidelidadeResponseDTO.fromEntity(cartaoRepository.save(cartao));
    }

    @Transactional
    public void excluir(Long id) {
        CartaoFidelidade cartao = buscarEntidade(id);
        cartao.getCliente().setCartaoFidelidade(null);
        cartaoRepository.delete(cartao);
    }

    private CartaoFidelidade buscarEntidade(Long id) {
        return cartaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cartão fidelidade não encontrado"));
    }
}
