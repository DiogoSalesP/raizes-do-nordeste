package com.diogo.raizesdonordeste.controller;


import com.diogo.raizesdonordeste.domain.Unidade;
import com.diogo.raizesdonordeste.dto.request.UnidadeRequestDTO;
import com.diogo.raizesdonordeste.dto.response.UnidadeResponseDTO;
import com.diogo.raizesdonordeste.mapper.UnidadeMapper;
import com.diogo.raizesdonordeste.service.UnidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public UnidadeResponseDTO salvar(@RequestBody UnidadeRequestDTO dto) {
        Unidade unidade = unidadeService.salvar(dto);
        return UnidadeMapper.toResponse(unidade);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UnidadeResponseDTO> buscarTodos() {
        List<Unidade> unidades = unidadeService.buscarTodos();
        return unidades
                .stream()
                .map(UnidadeMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UnidadeResponseDTO buscarPorId(@PathVariable UUID id) {
        Unidade unidade = unidadeService.buscarPorId(id);
        return UnidadeMapper.toResponse(unidade);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UnidadeResponseDTO atualizar(@PathVariable UUID id, @RequestBody UnidadeRequestDTO dto) {
        Unidade unidade = unidadeService.atualizar(id, dto);
        return UnidadeMapper.toResponse(unidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        unidadeService.deletar(id);
    }

}

