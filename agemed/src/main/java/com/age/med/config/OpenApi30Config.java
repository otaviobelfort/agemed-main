package com.age.med.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springdoc.core.customizers.OpenApiCustomiser;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
@Configuration
//@CanEditMyUser
@OpenAPIDefinition(info = @Info(title = "My API", version = "v1"))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApi30Config {
    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openApi -> {
            openApi.getPaths()
                    .values()
                    .stream()
                    .flatMap(pathItem -> pathItem.readOperations().stream())
                    .forEach(operation -> {
                        ApiResponses responses = operation.getResponses();

                        ApiResponse apiResponseNotFound = new ApiResponse().description("Recurso não encontrado");
                        ApiResponse apiResponseErroInterno = new ApiResponse().description("Erro interno no servidor");
                        ApiResponse apiResponseBadRequest = new ApiResponse().description("Parâmetros enviados de maneira incorreta");
                        ApiResponse apiResponseCreated = new ApiResponse().description("Recurso criado");
                        ApiResponse apiResponseOk = new ApiResponse().description("Status OK");
                        ApiResponse apiResponseUnauthorized = new ApiResponse().description("Não autenticado");
                        ApiResponse apiResponseForbidden = new ApiResponse().description("Autenticado, porém sem permissão específica");

                        responses.addApiResponse("200", apiResponseOk);
                        responses.addApiResponse("201", apiResponseCreated);
                        responses.addApiResponse("404", apiResponseNotFound);
                        responses.addApiResponse("401", apiResponseUnauthorized);
                        responses.addApiResponse("403", apiResponseForbidden);
                        responses.addApiResponse("400", apiResponseBadRequest);
                        responses.addApiResponse("500", apiResponseErroInterno);
                    });
        };
    }
}
