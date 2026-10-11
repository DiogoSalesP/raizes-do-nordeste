package com.diogo.raizesdonordeste.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequestDTO(

        @NotBlank
        @Email(message = "e-mail inválido")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "e-mail deve ser um endereço válido")
        String email,

        @NotBlank(message = "campo senha obrigatório")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#]).{8,20}$",
                message = "A senha deve conter letra maiúscula, minúscula, número e caractere especial.")
        String senha
) {
}
