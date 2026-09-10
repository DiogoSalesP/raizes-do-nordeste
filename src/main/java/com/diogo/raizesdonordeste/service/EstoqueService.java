package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.Estoque;
import com.diogo.raizesdonordeste.domain.Produto;
import com.diogo.raizesdonordeste.dto.request.EstoqueRequestDTO;
import com.diogo.raizesdonordeste.mapper.EstoqueMapper;
import com.diogo.raizesdonordeste.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoService produtoService;

    public Estoque salvar(EstoqueRequestDTO dto) {
        Estoque estoque = EstoqueMapper.toEntity(dto);
        Produto produto = produtoService.buscarPorId(dto.idProduto());
        estoque.setProduto(produto);
        return estoqueRepository.save(estoque);
    }

    public List<Estoque> buscarTodos() {
        return estoqueRepository.findAll();
    }

    public Estoque buscarPorId(UUID id) {
        return estoqueRepository.findById(id).orElse(null);
    }

    public Estoque atualizarQuantidade(EstoqueRequestDTO dto, UUID id) {
        Estoque estoque = estoqueRepository.findById(id).orElse(null);
        estoque.setQuantidade(dto.quantidade());
        estoque.setEstoqueMinimo(dto.estoqueMinimo());
        return estoqueRepository.save(estoque);
    }
}
