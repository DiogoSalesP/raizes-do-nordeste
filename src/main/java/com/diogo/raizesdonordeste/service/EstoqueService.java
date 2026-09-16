package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.Estoque;
import com.diogo.raizesdonordeste.domain.Produto;
import com.diogo.raizesdonordeste.dto.request.AtualizarEstoqueRequestDTO;
import com.diogo.raizesdonordeste.dto.request.EstoqueRequestDTO;
import com.diogo.raizesdonordeste.exception.OperacaoNaoPermitidaException;
import com.diogo.raizesdonordeste.exception.RegistroNaoEncontradoException;
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
        return estoqueRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Estoque", id));
    }
     public Estoque buscarEstoquePorIdProduto(UUID idProduto) {
        return estoqueRepository.findByProduto_idProduto(idProduto);
     }

    public Estoque atualizarQuantidade(AtualizarEstoqueRequestDTO dto, UUID id) {
        Estoque estoque = buscarPorId(id);
        estoque.setQuantidade(dto.quantidade());
        estoque.setEstoqueMinimo(dto.estoqueMinimo());
        return estoqueRepository.save(estoque);
    }

    public void atualizarEstoque(Produto produto, Integer quantidade) {
        Estoque estoque = buscarEstoquePorIdProduto(produto.getIdProduto());
        if (estoque.getQuantidade() < quantidade) {
            throw new OperacaoNaoPermitidaException("Estoque indisponível para o produto: " + estoque.getProduto().getNome());
        }
        estoque.setQuantidade(estoque.getQuantidade() - quantidade);
    }
}
