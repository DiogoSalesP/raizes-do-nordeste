package com.diogo.raizesdonordeste.dto.request;

import jakarta.validation.constraints.*;

public record UsuarioRequestDTO(

        @NotBlank(message = "campo nome obrigatório")
        @Size(min = 3, max = 100, message = "nome deve ter no mínimo 3 caracteres")
        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$", message = "Nome deve conter apenas letras.")
        String nome,

        @NotBlank(message = "campo email obrigatório")
        @Email(message = "e-mail inválido")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "e-mail deve ser um endereço válido")
        String email,

        @NotBlank(message = "campo senha obrigatório")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,20}$",
                message = "A senha deve conter letra maiúscula, minúscula, número e caractere especial.")
        String senha,

        @NotBlank(message = "campo telefone obrigatório")
        @Pattern(regexp = "^\\d{10,11}$", message = "telefone deve ter 10 ou 11 dígitos")
        String telefone
) {
}
