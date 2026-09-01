package com.diogo.raizesdonordeste.mapper;

import com.diogo.raizesdonordeste.domain.Pagamento;
import com.diogo.raizesdonordeste.dto.request.PagamentoRequestDTO;
import com.diogo.raizesdonordeste.dto.response.PagamentoResponseDTO;

public class PagamentoMapper {

    public static Pagamento toEntity(PagamentoRequestDTO dto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setFormaPagamento(dto.formaPagamento());
        pagamento.setValorPagamento(dto.valorPagamento());
        return pagamento;
    }

    public static PagamentoResponseDTO toResponse(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getIdPagamento(),
                pagamento.getFormaPagamento(),
                pagamento.getValorPagamento(),
                pagamento.getStatusPagamento(),
                pagamento.getDataPagamento(),
                pagamento.getPedido().getIdPedido()

        );
    }
}
