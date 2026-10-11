package com.diogo.raizesdonordeste.dto.response;

public record LoginResponseDTO(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}
