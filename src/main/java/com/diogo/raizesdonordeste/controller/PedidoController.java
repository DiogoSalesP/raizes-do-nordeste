package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Pedido;
import com.diogo.raizesdonordeste.domain.enums.StatusPedido;
import com.diogo.raizesdonordeste.dto.request.AtualizarStatusPedidoRequestDTO;
import com.diogo.raizesdonordeste.dto.request.PedidoRequestDTO;
import com.diogo.raizesdonordeste.dto.response.PedidoResponseDTO;
import com.diogo.raizesdonordeste.mapper.PedidoMapper;
import com.diogo.raizesdonordeste.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public PedidoResponseDTO salvar(@RequestBody PedidoRequestDTO dto) {
        Pedido pedido = pedidoService.salvar(dto);
        return PedidoMapper.toResponse(pedido);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PedidoResponseDTO> buscarTodos() {
        return pedidoService.buscarTodos()
                .stream()
                .map(PedidoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PedidoResponseDTO buscarPorId(@PathVariable UUID id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        return PedidoMapper.toResponse(pedido);
    }

    @GetMapping("/pesquisa")
    @ResponseStatus(HttpStatus.OK)
    public List<PedidoResponseDTO> pesquisar(@RequestParam(value = "canal-pedido") String canalPedido) {
        return pedidoService.pesquisa(canalPedido)
                .stream()
                .map(PedidoMapper::toResponse)
                .toList();
    }
    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public PedidoResponseDTO atualizar(@PathVariable UUID id, @RequestBody AtualizarStatusPedidoRequestDTO dto) {
        Pedido pedido = pedidoService.atualizarStatusPedido(id, dto);
        return PedidoMapper.toResponse(pedido);
    }

}
