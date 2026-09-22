package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Estoque;
import com.diogo.raizesdonordeste.dto.request.AtualizarEstoqueRequestDTO;
import com.diogo.raizesdonordeste.dto.response.EstoqueResponseDTO;
import com.diogo.raizesdonordeste.mapper.EstoqueMapper;
import com.diogo.raizesdonordeste.service.EstoqueService;
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

import java.util.UUID;

@RestController
@RequestMapping("estoques")
@RequiredArgsConstructor
@Tag(name = "Estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."))
    public Page<EstoqueResponseDTO> buscarTodos(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return estoqueService.buscarTodos(pagina, tamanhoPagina).map(EstoqueMapper::toResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Buscar por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Busca não permitida."),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado.")
    })
    public EstoqueResponseDTO buscarPorId(@PathVariable UUID id) {
        Estoque estoque = estoqueService.buscarPorId(id);
        return EstoqueMapper.toResponse(estoque);
    }

    @GetMapping("/produto/{idProduto}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Buscar por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Busca não permitida."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    public EstoqueResponseDTO buscarEstoquePorIdProduto(@PathVariable UUID idProduto) {
        Estoque estoque = estoqueService.buscarEstoquePorIdProduto(idProduto);
        return EstoqueMapper.toResponse(estoque);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Atualizar")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    public EstoqueResponseDTO atualizarQuantidade(@RequestBody @Valid AtualizarEstoqueRequestDTO dto, @PathVariable UUID id) {
        Estoque estoque = estoqueService.atualizarQuantidade(dto, id);
        return EstoqueMapper.toResponse(estoque);
    }

}
