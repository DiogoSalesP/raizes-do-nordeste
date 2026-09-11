package com.diogo.raizesdonordeste.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UnidadeRequestDTO(
        @NotBlank(message = "campo nome obrigatório")
        @Size(min = 3, max = 100, message = "nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "campo endereço é obrigatório")
        @Size(max = 255, message = "endereço deve ter no máximo 255 caracteres")
        String endereco,

        @NotBlank(message = "campo cidade é obrigatório")
        @Size(max = 100, message = "cidade deve ter no máximo 100 caracteres")
        String cidade,

        @NotBlank(message = "campo estado é obrigatório")
        @Pattern(regexp = "^[A-Z]{2}$",
                message = "estado deve ser informado pela sigla (ex: CE, SP, RJ)"
        )
        String estado,

        @NotBlank(message = "campo telefone obrigatório")
        @Pattern(regexp = "^\\d{10,11}$", message = "telefone deve ter 10 ou 11 dígitos")
        String telefone
) {
}
