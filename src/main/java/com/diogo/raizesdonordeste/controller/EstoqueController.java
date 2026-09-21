package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Estoque;
import com.diogo.raizesdonordeste.dto.request.AtualizarEstoqueRequestDTO;
import com.diogo.raizesdonordeste.dto.response.EstoqueResponseDTO;
import com.diogo.raizesdonordeste.mapper.EstoqueMapper;
import com.diogo.raizesdonordeste.service.EstoqueService;
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
public class EstoqueController {

    private final EstoqueService estoqueService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public Page<EstoqueResponseDTO> buscarTodos(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return estoqueService.buscarTodos(pagina, tamanhoPagina).map(EstoqueMapper::toResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public EstoqueResponseDTO buscarPorId(@PathVariable UUID id) {
        Estoque estoque = estoqueService.buscarPorId(id);
        return EstoqueMapper.toResponse(estoque);
    }

    @GetMapping("/produto/{idProduto}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public EstoqueResponseDTO buscarEstoquePorIdProduto(@PathVariable UUID idProduto) {
        Estoque estoque = estoqueService.buscarEstoquePorIdProduto(idProduto);
        return EstoqueMapper.toResponse(estoque);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public EstoqueResponseDTO atualizarQuantidade(@RequestBody @Valid AtualizarEstoqueRequestDTO dto, @PathVariable UUID id) {
        Estoque estoque = estoqueService.atualizarQuantidade(dto, id);
        return EstoqueMapper.toResponse(estoque);
    }

}
