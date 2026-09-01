package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Pagamento;
import com.diogo.raizesdonordeste.dto.request.AtualizarStatusPagamentoRequestDTO;
import com.diogo.raizesdonordeste.dto.request.PagamentoRequestDTO;
import com.diogo.raizesdonordeste.dto.response.PagamentoResponseDTO;
import com.diogo.raizesdonordeste.mapper.PagamentoMapper;
import com.diogo.raizesdonordeste.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public PagamentoResponseDTO salvar(@RequestBody PagamentoRequestDTO dto) {
        Pagamento pagamento = pagamentoService.salvar(dto);
        return PagamentoMapper.toResponse(pagamento);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PagamentoResponseDTO> buscarTodos() {
        return pagamentoService.buscarTodos()
                .stream()
                .map(PagamentoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PagamentoResponseDTO buscarPorId(@PathVariable UUID id) {
        Pagamento pagamento = pagamentoService.buscarPorId(id);
        return PagamentoMapper.toResponse(pagamento);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PagamentoResponseDTO atualizarStatus(@PathVariable UUID id, @RequestBody AtualizarStatusPagamentoRequestDTO dto) {
        Pagamento pagamento = pagamentoService.atualizarStatusPagamento(id, dto);
        return PagamentoMapper.toResponse(pagamento);
    }
}
