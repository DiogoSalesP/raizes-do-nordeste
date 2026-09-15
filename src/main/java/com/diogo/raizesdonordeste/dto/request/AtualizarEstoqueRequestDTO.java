package com.diogo.raizesdonordeste.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AtualizarEstoqueRequestDTO(
        @NotNull(message = "campo quantidade obrigatório")
        @Min(value = 0, message = "Quantidade não pode ser negativa")
        Integer quantidade,

        @NotNull(message = "campo estoque mínimo obrigatório")
        @Min(value = 0, message = "Estoque mínimo não pode ser negativa")
        Integer estoqueMinimo
) {
}