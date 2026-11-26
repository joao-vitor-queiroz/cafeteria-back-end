package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cafeteriaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cafeteria API")
                        .description("API REST da cafeteria: clientes, cartão fidelidade, ingredientes, produtos e pedidos")
                        .version("v1"));
    }
}
