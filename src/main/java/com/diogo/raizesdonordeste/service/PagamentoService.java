package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.Pagamento;
import com.diogo.raizesdonordeste.domain.Pedido;
import com.diogo.raizesdonordeste.domain.enums.StatusPagamento;
import com.diogo.raizesdonordeste.domain.enums.StatusPedido;
import com.diogo.raizesdonordeste.dto.request.AtualizarStatusPagamentoRequestDTO;
import com.diogo.raizesdonordeste.dto.request.PagamentoRequestDTO;
import com.diogo.raizesdonordeste.exception.OperacaoNaoPermitidaException;
import com.diogo.raizesdonordeste.exception.RegistroNaoEncontradoException;
import com.diogo.raizesdonordeste.mapper.PagamentoMapper;
import com.diogo.raizesdonordeste.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoService pedidoService;
    private final ProgramaFidelidadeService programaFidelidadeService;

    public Pagamento salvar(PagamentoRequestDTO dto) {
        Pagamento pagamento = PagamentoMapper.toEntity(dto);
        Pedido pedido = pedidoService.buscarPorId(dto.idPedido());
        if (pagamento.getValorPagamento().compareTo(pedido.getValorTotal()) == 0) {
            programaFidelidadeService.atualizar(pedido.getCliente().getEmail(), pedido.getValorTotal());
            pagamento.setStatusPagamento(StatusPagamento.APROVADO);
            pedido.setStatus(StatusPedido.ENTREGUE);
        } else {
            pagamento.setStatusPagamento(StatusPagamento.RECUSADO);
            throw new OperacaoNaoPermitidaException("Pagamento Recusado. Valor do pedido: R$" + pedido.getValorTotal());
        }
        pagamento.setPedido(pedido);
        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> buscarTodos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPorId(UUID id) {
        return pagamentoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Pagamento", id));
    }

    public Pagamento atualizarStatusPagamento(UUID id, AtualizarStatusPagamentoRequestDTO dto) {
        Pagamento pagamento = buscarPorId(id);
        pagamento.setStatusPagamento(dto.statusPagamento());
        return pagamentoRepository.save(pagamento);
    }

}
