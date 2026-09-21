package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Pagamento;
import com.diogo.raizesdonordeste.dto.request.AtualizarStatusPagamentoRequestDTO;
import com.diogo.raizesdonordeste.dto.request.PagamentoRequestDTO;
import com.diogo.raizesdonordeste.dto.response.PagamentoResponseDTO;
import com.diogo.raizesdonordeste.mapper.PagamentoMapper;
import com.diogo.raizesdonordeste.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    public PagamentoResponseDTO salvar(@RequestBody @Valid PagamentoRequestDTO dto) {
        Pagamento pagamento = pagamentoService.salvar(dto);
        return PagamentoMapper.toResponse(pagamento);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public Page<PagamentoResponseDTO> buscarTodos(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return pagamentoService.buscarTodos(pagina, tamanhoPagina).map(PagamentoMapper::toResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public PagamentoResponseDTO buscarPorId(@PathVariable UUID id) {
        Pagamento pagamento = pagamentoService.buscarPorId(id);
        return PagamentoMapper.toResponse(pagamento);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public PagamentoResponseDTO atualizarStatus(@PathVariable UUID id, @RequestBody @Valid AtualizarStatusPagamentoRequestDTO dto) {
        Pagamento pagamento = pagamentoService.atualizarStatusPagamento(id, dto);
        return PagamentoMapper.toResponse(pagamento);
    }
}
