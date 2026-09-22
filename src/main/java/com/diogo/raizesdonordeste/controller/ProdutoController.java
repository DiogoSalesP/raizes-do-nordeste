package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Produto;
import com.diogo.raizesdonordeste.dto.request.ProdutoRequestDTO;
import com.diogo.raizesdonordeste.dto.response.ProdutoResponseDTO;
import com.diogo.raizesdonordeste.mapper.ProdutoMapper;
import com.diogo.raizesdonordeste.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Salvar")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação."),
    })
    public ProdutoResponseDTO salvarProduto(@RequestBody @Valid ProdutoRequestDTO dto) {
        Produto produto = produtoService.salvar(dto);
        return ProdutoMapper.toResponse(produto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."))
    public Page<ProdutoResponseDTO> buscarTodosProdutos(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina    ) {
        Page<Produto> produtos = produtoService.buscarTodos(pagina, tamanhoPagina);
        return produtos.map(ProdutoMapper::toResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    @Operation(summary = "Buscar por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Busca não permitida."),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado.")
    })
    public ProdutoResponseDTO buscarProdutoPorId(@PathVariable UUID id) {
        Produto produtos = produtoService.buscarPorId(id);
        return ProdutoMapper.toResponse(produtos);
    }

    @GetMapping("/unidade/{idUnidade}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    @Operation(summary = "Buscar por ID_UNIDADE")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Busca não permitida."),
    })
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
    @Operation(summary = "Buscar por disponibilidade")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."))
    public List<ProdutoResponseDTO> buscarPorDisponivel() {
        return produtoService.buscarDisponivel()
                .stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Atualizar")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação."),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado.")
    })
    public ProdutoResponseDTO atualizar(@PathVariable UUID id, @RequestBody @Valid ProdutoRequestDTO dto) {
        Produto produto = produtoService.atualizar(id, dto);
        return ProdutoMapper.toResponse(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Deletar")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    public void delete(@PathVariable UUID id) {
        produtoService.deletar(id);
    }

}
