package com.diogo.raizesdonordeste.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ItemPedidoRequestDTO(
        @NotNull(message = "Campo idProduto obrigatório")
        UUID idProduto,

        @NotNull(message = "Campo produto obrigatório")
        @Min(value = 1, message = "Quantidade deve ser maior que zero")
        Integer quantidade,

        @Size(max = 255, message = "Observação deve ter no máximo 255 caracteres")
        String observacao
) {
}
