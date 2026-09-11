package com.diogo.raizesdonordeste.dto.request;

import com.diogo.raizesdonordeste.domain.enums.StatusPagamento;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusPagamentoRequestDTO(
        @NotNull(message = "campo status pagamento obrigatório")
        StatusPagamento statusPagamento
) {
}
