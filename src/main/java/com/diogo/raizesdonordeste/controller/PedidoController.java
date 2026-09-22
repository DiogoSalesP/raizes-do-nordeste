package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Pedido;
import com.diogo.raizesdonordeste.domain.enums.CanalPedido;
import com.diogo.raizesdonordeste.domain.enums.StatusPedido;
import com.diogo.raizesdonordeste.dto.request.AtualizarStatusPedidoRequestDTO;
import com.diogo.raizesdonordeste.dto.request.PedidoRequestDTO;
import com.diogo.raizesdonordeste.dto.response.PedidoResponseDTO;
import com.diogo.raizesdonordeste.mapper.PedidoMapper;
import com.diogo.raizesdonordeste.service.PedidoService;
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
@RequestMapping("pedidos")
@RequiredArgsConstructor
@Tag(name = "Pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('GERENTE', 'CLIENTE')")
    @Operation(summary = "Salvar")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação."),
            @ApiResponse(responseCode = "400", description = "Busca não permitida."),

    })
    public PedidoResponseDTO salvar(@RequestBody @Valid PedidoRequestDTO dto) {
        Pedido pedido = pedidoService.salvar(dto);
        return PedidoMapper.toResponse(pedido);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."))
    public Page<PedidoResponseDTO> buscarTodos(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return pedidoService.buscarTodos(pagina, tamanhoPagina).map(PedidoMapper::toResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Buscar por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Busca não permitida."),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado.")
    })
    public PedidoResponseDTO buscarPorId(@PathVariable UUID id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        return PedidoMapper.toResponse(pedido);
    }

    @GetMapping("/pesquisa")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Busca não permitida."),
    })
    public List<PedidoResponseDTO> pesquisar(@RequestParam(value = "canal-pedido") CanalPedido canalPedido) {
        return pedidoService.pesquisa(canalPedido)
                .stream()
                .map(PedidoMapper::toResponse)
                .toList();
    }
    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Atualizar")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação."),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado.")
    })
    public PedidoResponseDTO atualizar(@PathVariable UUID id, @RequestBody @Valid AtualizarStatusPedidoRequestDTO dto) {
        Pedido pedido = pedidoService.atualizarStatusPedido(id, dto);
        return PedidoMapper.toResponse(pedido);
    }

}
