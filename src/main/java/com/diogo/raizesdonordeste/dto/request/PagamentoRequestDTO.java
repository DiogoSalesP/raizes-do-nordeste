package com.diogo.raizesdonordeste.dto.request;

import com.diogo.raizesdonordeste.domain.enums.FormaPagamento;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoRequestDTO(
        FormaPagamento formaPagamento,
        BigDecimal valorPagamento,
        UUID idPedido
) {
}
