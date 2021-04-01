package com.sanvalero.mynewshop.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Definicion de propiedades sobre la API
@Configuration
public class ShopConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Mynewshop API")
                .description("Ejemplo de API REST")
                .contact(new Contact()
                .name("Sebastian Camino")
                .email("Sebas16497@outlook.com")
                .url("https://sport.com"))
                .version("1.0"));
    }

}
