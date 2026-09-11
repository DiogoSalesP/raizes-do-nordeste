package com.diogo.raizesdonordeste.dto.request;

import com.diogo.raizesdonordeste.domain.enums.FormaPagamento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoRequestDTO(
        @NotNull(message = "campo forma pagamento obrigatório")
        FormaPagamento formaPagamento,

        @NotNull(message = "campo valor obrigatório")
        @Min(value = 1, message = "Valor deve ser maior que zero")
        BigDecimal valorPagamento,

        @NotNull(message = "campo idPedido obrigatório")
        UUID idPedido
) {
}
