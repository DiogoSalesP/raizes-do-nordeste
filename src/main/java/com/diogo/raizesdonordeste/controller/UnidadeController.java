package com.diogo.raizesdonordeste.controller;


import com.diogo.raizesdonordeste.domain.Unidade;
import com.diogo.raizesdonordeste.dto.request.UnidadeRequestDTO;
import com.diogo.raizesdonordeste.dto.response.UnidadeResponseDTO;
import com.diogo.raizesdonordeste.mapper.UnidadeMapper;
import com.diogo.raizesdonordeste.service.UnidadeService;
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
@RequestMapping("unidades")
@RequiredArgsConstructor
@Tag(name = "Unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Salvar")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
    public UnidadeResponseDTO salvar(@RequestBody @Valid UnidadeRequestDTO dto) {
        Unidade unidade = unidadeService.salvar(dto);
        return UnidadeMapper.toResponse(unidade);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    @Operation(summary = "Buscar")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."))
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
    @Operation(summary = "Buscar por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Busca não permitida."),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada.")
    })
    public UnidadeResponseDTO buscarPorId(@PathVariable UUID id) {
        Unidade unidade = unidadeService.buscarPorId(id);
        return UnidadeMapper.toResponse(unidade);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Atualizar")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação."),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada.")
    })
    public UnidadeResponseDTO atualizar(@PathVariable UUID id, @RequestBody @Valid UnidadeRequestDTO dto) {
        Unidade unidade = unidadeService.atualizar(id, dto);
        return UnidadeMapper.toResponse(unidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar")
    @PreAuthorize("hasRole('GERENTE')")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Unidade deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada.")
    })
    public void deletar(@PathVariable UUID id) {
        unidadeService.deletar(id);
    }

}

