package com.diogo.raizesdonordeste.dto.request;

import com.diogo.raizesdonordeste.domain.enums.NivelFidelidade;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProgramaFidelidadeRequestDTO(

        @NotNull(message = "Campo saldo pontos obrigatório")
        @Min(value = 0, message = "saldo pontos não pode ser negativa")
        Integer saldoPontos,

        @NotNull(message = "Campo nivel é obrigatório")
        NivelFidelidade nivel
) {
}
