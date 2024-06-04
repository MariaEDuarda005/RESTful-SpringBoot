package com.example.carros.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration // é um arquivo de configuração do spring
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Carros")
                        .version("1.0")
                        .description("Documentação API dos Carros")
                        .contact(new Contact()
                                .name("Maria Eduarda")
                                .url("")
                                .email("")));
    }
}
