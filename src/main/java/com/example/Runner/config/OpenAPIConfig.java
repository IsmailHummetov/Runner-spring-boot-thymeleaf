package com.example.Runner.config;

import java.util.List;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
@OpenAPIDefinition
@SecurityScheme(
        name = "Authorization",
        scheme = "basic",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        String devUrl = "http://localhost:8080";
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");


        Info info = new Info()
                .title("Tutorial Management API")
                .version("1.0");

        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Authorization")).info(info).servers(List.of(devServer));
    }
}