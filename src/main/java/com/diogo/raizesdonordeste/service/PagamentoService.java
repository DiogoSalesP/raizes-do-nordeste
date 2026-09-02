package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.Pagamento;
import com.diogo.raizesdonordeste.domain.Pedido;
import com.diogo.raizesdonordeste.domain.enums.StatusPagamento;
import com.diogo.raizesdonordeste.domain.enums.StatusPedido;
import com.diogo.raizesdonordeste.dto.request.AtualizarStatusPagamentoRequestDTO;
import com.diogo.raizesdonordeste.dto.request.PagamentoRequestDTO;
import com.diogo.raizesdonordeste.mapper.PagamentoMapper;
import com.diogo.raizesdonordeste.repository.PagamentoRepository;
import com.diogo.raizesdonordeste.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public Pagamento salvar(PagamentoRequestDTO dto) {
        Pagamento pagamento = PagamentoMapper.toEntity(dto);
        Pedido pedido = pedidoRepository.findById(dto.idPedido()).orElse(null);
        if (pagamento.getValorPagamento().compareTo(pedido.getValorTotal()) == 0) {
            pagamento.setStatusPagamento(StatusPagamento.APROVADO);
            pedido.setStatus(StatusPedido.ENTREGUE);
        } else {
            pagamento.setStatusPagamento(StatusPagamento.RECUSADO);
        }
        pagamento.setPedido(pedido);
        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> buscarTodos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPorId(UUID id) {
        return pagamentoRepository.findById(id).orElse(null);
    }

    public Pagamento atualizarStatusPagamento(UUID id, AtualizarStatusPagamentoRequestDTO dto) {
        Pagamento pagamento = buscarPorId(id);
        pagamento.setStatusPagamento(dto.statusPagamento());
        return pagamentoRepository.save(pagamento);
    }

}
