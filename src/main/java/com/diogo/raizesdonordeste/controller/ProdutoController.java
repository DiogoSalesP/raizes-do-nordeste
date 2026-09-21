package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Produto;
import com.diogo.raizesdonordeste.dto.request.ProdutoRequestDTO;
import com.diogo.raizesdonordeste.dto.response.ProdutoResponseDTO;
import com.diogo.raizesdonordeste.mapper.ProdutoMapper;
import com.diogo.raizesdonordeste.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('GERENTE')")
    public ProdutoResponseDTO salvarProduto(@RequestBody @Valid ProdutoRequestDTO dto) {
        Produto produto = produtoService.salvar(dto);
        return ProdutoMapper.toResponse(produto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    public Page<ProdutoResponseDTO> buscarTodosProdutos(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina    ) {
        Page<Produto> produtos = produtoService.buscarTodos(pagina, tamanhoPagina);
        return produtos.map(ProdutoMapper::toResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    public ProdutoResponseDTO buscarProdutoPorId(@PathVariable UUID id) {
        Produto produtos = produtoService.buscarPorId(id);
        return ProdutoMapper.toResponse(produtos);
    }

    @GetMapping("/unidade/{idUnidade}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    public List<ProdutoResponseDTO> buscarProdutoPorUnidade(@PathVariable UUID idUnidade){
        List<Produto> produtos = produtoService.buscarProdutoPorUnidade(idUnidade);
        return produtos
                .stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    @GetMapping("/disponiveis")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    public List<ProdutoResponseDTO> buscarPorDisponivel() {
        return produtoService.buscarDisponivel()
                .stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public ProdutoResponseDTO atualizar(@PathVariable UUID id, @RequestBody @Valid ProdutoRequestDTO dto) {
        Produto produto = produtoService.atualizar(id, dto);
        return ProdutoMapper.toResponse(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('GERENTE')")
    public void delete(@PathVariable UUID id) {
        produtoService.deletar(id);
    }

}
