package com.smartlogix.pedidos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SmartLogix Pedidos API")
                        .version("1.0.0")
                        .description("Microservicio de gestión de pedidos para SmartLogix. Permite crear, listar, actualizar y eliminar pedidos.")
                        .contact(new Contact()
                                .name("Dante Muñoz")
                                .email("dant.munozf@duocuc.com")));
    }
}