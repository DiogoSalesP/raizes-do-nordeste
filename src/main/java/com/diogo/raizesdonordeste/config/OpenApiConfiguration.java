package com.diogo.raizesdonordeste.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Raizes do Nordeste", version = "v1"))
public class OpenApiConfiguration {
}
