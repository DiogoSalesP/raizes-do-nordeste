package com.diogo.raizesdonordeste.dto.response;

import com.diogo.raizesdonordeste.domain.enums.FormaPagamento;
import com.diogo.raizesdonordeste.domain.enums.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PagamentoResponseDTO(
        UUID idPagamento,
        FormaPagamento formaPagamento,
        BigDecimal valorPagamento,
        StatusPagamento statusPagamento,
        LocalDateTime dataPagamento,
        UUID idPedido
) {
}
