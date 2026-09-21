package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.Produto;
import com.diogo.raizesdonordeste.domain.Unidade;
import com.diogo.raizesdonordeste.dto.request.ProdutoRequestDTO;
import com.diogo.raizesdonordeste.exception.RegistroNaoEncontradoException;
import com.diogo.raizesdonordeste.mapper.ProdutoMapper;
import com.diogo.raizesdonordeste.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final UnidadeService unidadeService;
    private final EstoqueService estoqueService;

    public Produto salvar(ProdutoRequestDTO dto) {
        Produto produto = ProdutoMapper.toEntity(dto);
        Unidade unidade = unidadeService.buscarPorId(dto.idUnidade());
        produto.setUnidade(unidade);
        produto = produtoRepository.save(produto);
        estoqueService.salvar(produto);
        return produto;
    }

    public Page<Produto> buscarTodos(Integer pagina, Integer tamanhoPagina) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return produtoRepository.findAll(pageRequest);
    }

    public Produto buscarPorId(UUID id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Produto", id));
    }

    public List<Produto> buscarProdutoPorUnidade(UUID idUnidade) {
        return produtoRepository.findByUnidade_idUnidade(idUnidade);
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
