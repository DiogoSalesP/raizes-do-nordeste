package com.diogo.raizesdonordeste.controller;


import com.diogo.raizesdonordeste.domain.ProgramaFidelidade;
import com.diogo.raizesdonordeste.dto.response.ProgramaFidelidadeResponseDTO;
import com.diogo.raizesdonordeste.mapper.ProgramaFidelidadeMapper;
import com.diogo.raizesdonordeste.service.ProgramaFidelidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("programa-fidelidade")
@RequiredArgsConstructor
@Tag(name = "Programa de Fidelidade")
public class ProgramaFidelidadeController {

    private final ProgramaFidelidadeService fidelidadeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Buscar")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."))
    public Page<ProgramaFidelidadeResponseDTO> buscarTodos(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        Page<ProgramaFidelidade> programaFidelidades = fidelidadeService.buscarTodos(pagina, tamanhoPagina);
        return programaFidelidades.map(ProgramaFidelidadeMapper::toResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Buscar por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Busca não permitida."),
            @ApiResponse(responseCode = "404", description = "Programa de fidelidade não encontrado.")
    })
    public ProgramaFidelidadeResponseDTO buscarPorId(@PathVariable UUID id) {
        ProgramaFidelidade programaFidelidades = fidelidadeService.buscarPorId(id);
        return ProgramaFidelidadeMapper.toResponse(programaFidelidades);
    }

    @GetMapping("/pesquisa")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    @Operation(summary = "Buscar por EMAIL")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Busca não permitida."),
    })
    public ProgramaFidelidadeResponseDTO buscarPorEmail(@RequestParam(value = "email") String email) {
        ProgramaFidelidade programaFidelidade = fidelidadeService.buscarPorEmail(email);
        return ProgramaFidelidadeMapper.toResponse(programaFidelidade);
    }

}
