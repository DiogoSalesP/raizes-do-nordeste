package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.Produto;
import com.diogo.raizesdonordeste.dto.request.ProdutoRequestDTO;
import com.diogo.raizesdonordeste.mapper.ProdutoMapper;
import com.diogo.raizesdonordeste.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto salvar(ProdutoRequestDTO dto) {
        Produto produto = ProdutoMapper.toEntity(dto);
        return produtoRepository.save(produto);
    }

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(UUID id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> buscarDisponivel() {
        return produtoRepository.findByDisponivel(true);
    }

    public Produto atualizar(UUID id, ProdutoRequestDTO dto) {
        Produto produto = buscarPorId(id);
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setDisponivel(dto.disponivel());
        return produtoRepository.save(produto);
    }

    public void deletar(UUID id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}
