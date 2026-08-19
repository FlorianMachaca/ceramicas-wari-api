package com.florian.ceramicaswari.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ceramicasWariOpenAPI() {

        Contact contacto = new Contact()
                .name("Florian Machaca")
                .url("https://github.com/FlorianMachaca");

        Info informacion = new Info()
                .title("Cerámicas Wari API")
                .version("1.0")
                .description(
                        "API REST para la gestión de productos, clientes, "
                        + "pedidos, producción, proveedores, materias primas, "
                        + "pagos y exportaciones de Cerámicas Wari."
                )
                .contact(contacto);

        return new OpenAPI()
                .info(informacion);
    }
}