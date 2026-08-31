package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.ItemPedido;
import com.diogo.raizesdonordeste.domain.Pedido;
import com.diogo.raizesdonordeste.domain.Produto;
import com.diogo.raizesdonordeste.domain.Usuario;
import com.diogo.raizesdonordeste.domain.enums.StatusPedido;
import com.diogo.raizesdonordeste.dto.request.AtualizarStatusPedidoRequestDTO;
import com.diogo.raizesdonordeste.dto.request.PedidoRequestDTO;
import com.diogo.raizesdonordeste.mapper.PedidoMapper;
import com.diogo.raizesdonordeste.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;

    public Pedido salvar(PedidoRequestDTO dto) {
        Pedido pedido = PedidoMapper.toEntity(dto);
        Usuario cliente = usuarioService.buscarPorId(dto.idCliente());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        for (ItemPedido itemPedido : pedido.getItens()) {
            Produto produto = produtoService.buscarPorId(itemPedido.getProduto().getIdProduto());
            itemPedido.setProduto(produto);
            itemPedido.setPrecoUnitario(produto.getPreco());
        }
        pedido.calculaValorTotal();
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(UUID id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido atualizarStatusPedido(UUID id, AtualizarStatusPedidoRequestDTO dto) {
        Pedido pedido = buscarPorId(id);
        pedido.setStatus(dto.statusPedido());
        return pedidoRepository.save(pedido);
    }
}
