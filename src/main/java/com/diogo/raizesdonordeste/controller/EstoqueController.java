package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Estoque;
import com.diogo.raizesdonordeste.dto.request.AtualizarEstoqueRequestDTO;
import com.diogo.raizesdonordeste.dto.request.EstoqueRequestDTO;
import com.diogo.raizesdonordeste.dto.response.EstoqueResponseDTO;
import com.diogo.raizesdonordeste.mapper.EstoqueMapper;
import com.diogo.raizesdonordeste.service.EstoqueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("estoques")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    public EstoqueResponseDTO salvar(@RequestBody @Valid EstoqueRequestDTO dto) {
        Estoque estoque = estoqueService.salvar(dto);
        return EstoqueMapper.toResponse(estoque);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public List<EstoqueResponseDTO> buscarTodos() {
        return estoqueService.buscarTodos()
                .stream()
                .map(EstoqueMapper::toResponse)
                .toList();
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
