package com.diogo.raizesdonordeste.dto.request;

import com.diogo.raizesdonordeste.domain.enums.StatusPagamento;

public record AtualizarStatusPagamentoRequestDTO(
        StatusPagamento statusPagamento
) {
}
