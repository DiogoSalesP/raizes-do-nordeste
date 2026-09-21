package com.diogo.raizesdonordeste.controller;


import com.diogo.raizesdonordeste.domain.Unidade;
import com.diogo.raizesdonordeste.dto.request.UnidadeRequestDTO;
import com.diogo.raizesdonordeste.dto.response.UnidadeResponseDTO;
import com.diogo.raizesdonordeste.mapper.UnidadeMapper;
import com.diogo.raizesdonordeste.service.UnidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("unidades")
@RequiredArgsConstructor
public class UnidadeController {

    private final UnidadeService unidadeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    public UnidadeResponseDTO salvar(@RequestBody @Valid UnidadeRequestDTO dto) {
        Unidade unidade = unidadeService.salvar(dto);
        return UnidadeMapper.toResponse(unidade);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    public Page<UnidadeResponseDTO> buscarTodos(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        Page<Unidade> unidades = unidadeService.buscarTodos(pagina, tamanhoPagina);
        return unidades.map(UnidadeMapper::toResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    public UnidadeResponseDTO buscarPorId(@PathVariable UUID id) {
        Unidade unidade = unidadeService.buscarPorId(id);
        return UnidadeMapper.toResponse(unidade);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public UnidadeResponseDTO atualizar(@PathVariable UUID id, @RequestBody @Valid UnidadeRequestDTO dto) {
        Unidade unidade = unidadeService.atualizar(id, dto);
        return UnidadeMapper.toResponse(unidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('GERENTE')")
    public void deletar(@PathVariable UUID id) {
        unidadeService.deletar(id);
    }

}

