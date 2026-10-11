package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.dto.request.LoginRequestDTO;
import com.diogo.raizesdonordeste.dto.response.LoginResponseDTO;
import com.diogo.raizesdonordeste.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Login")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Usuário inexistente ou senha inválida."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO dto) {
        return authenticationService.login(dto);
    }
}
