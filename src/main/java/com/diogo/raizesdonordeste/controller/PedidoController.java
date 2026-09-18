package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Pedido;
import com.diogo.raizesdonordeste.domain.enums.CanalPedido;
import com.diogo.raizesdonordeste.domain.enums.StatusPedido;
import com.diogo.raizesdonordeste.dto.request.AtualizarStatusPedidoRequestDTO;
import com.diogo.raizesdonordeste.dto.request.PedidoRequestDTO;
import com.diogo.raizesdonordeste.dto.response.PedidoResponseDTO;
import com.diogo.raizesdonordeste.mapper.PedidoMapper;
import com.diogo.raizesdonordeste.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    public PedidoResponseDTO salvar(@RequestBody @Valid PedidoRequestDTO dto) {
        Pedido pedido = pedidoService.salvar(dto);
        return PedidoMapper.toResponse(pedido);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public List<PedidoResponseDTO> buscarTodos() {
        return pedidoService.buscarTodos()
                .stream()
                .map(PedidoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public PedidoResponseDTO buscarPorId(@PathVariable UUID id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        return PedidoMapper.toResponse(pedido);
    }

    @GetMapping("/pesquisa")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public List<PedidoResponseDTO> pesquisar(@RequestParam(value = "canal-pedido") CanalPedido canalPedido) {
        return pedidoService.pesquisa(canalPedido)
                .stream()
                .map(PedidoMapper::toResponse)
                .toList();
    }
    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public PedidoResponseDTO atualizar(@PathVariable UUID id, @RequestBody @Valid AtualizarStatusPedidoRequestDTO dto) {
        Pedido pedido = pedidoService.atualizarStatusPedido(id, dto);
        return PedidoMapper.toResponse(pedido);
    }

}
