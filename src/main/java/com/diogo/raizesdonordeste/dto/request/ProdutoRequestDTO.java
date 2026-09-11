package com.diogo.raizesdonordeste.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoRequestDTO(

        @NotBlank(message = "Campo nome é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String nome,

        @Size(min = 3, max = 255, message = "Descrição deve ter entre 3 e 100 caracteres")
        String descricao,

        @NotNull(message = "Campo preço é obrigatório")
        @Min(value = 1, message = "Preço deve ser maior que zero")
        BigDecimal preco,

        @NotNull(message = "Campo disponível é obrigatório")
        Boolean disponivel,

        @NotNull(message = "Campo idUnidade é obrigatório")
        UUID idUnidade
) {
}
