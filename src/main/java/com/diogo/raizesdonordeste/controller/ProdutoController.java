package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Produto;
import com.diogo.raizesdonordeste.dto.request.ProdutoRequestDTO;
import com.diogo.raizesdonordeste.dto.response.ProdutoResponseDTO;
import com.diogo.raizesdonordeste.mapper.ProdutoMapper;
import com.diogo.raizesdonordeste.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO salvarProduto(@RequestBody ProdutoRequestDTO dto) {
        Produto produto = produtoService.salvar(dto);
        return ProdutoMapper.toResponse(produto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponseDTO> buscarTodosProdutos() {
        List<Produto> produtos = produtoService.buscarTodos();
        return produtos
                .stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponseDTO buscarProdutoPorId(@PathVariable UUID id) {
        Produto produtos = produtoService.buscarPorId(id);
        return ProdutoMapper.toResponse(produtos);
    }

    @GetMapping("/disponiveis")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoResponseDTO> buscarPorDisponivel() {
        return produtoService.buscarDisponivel()
                .stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoResponseDTO atualizar(@PathVariable UUID id, @RequestBody ProdutoRequestDTO dto) {
        Produto produto = produtoService.atualizar(id, dto);
        return ProdutoMapper.toResponse(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        produtoService.deletar(id);
    }

}
