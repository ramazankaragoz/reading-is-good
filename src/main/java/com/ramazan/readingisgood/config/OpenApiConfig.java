package com.ramazan.readingisgood.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI().components(new Components().addSecuritySchemes("basicAuth",new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")
                .name("basicAuth")))
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .info(new Info()
                .title("Reading is good API")
                .version("v1")
                .description("Service API reference for users")
                .termsOfService("https://github.com/ramazankaragoz/reading-is-good")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @Bean
    public GroupedOpenApi defaultOpenApi() {
        String[] paths = {"/api/**"};
        return GroupedOpenApi.builder().group("default").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi customerOpenApi() {
        String[] paths = {"/api/customer/**"};
        return GroupedOpenApi.builder().group("Customer").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi bookOpenApi() {
        String[] paths = {"/api/book/**"};
        return GroupedOpenApi.builder().group("Book").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi statisticsOpenApi() {
        String[] paths = {"/api/statistics/**"};
        return GroupedOpenApi.builder().group("Statistics").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi orderOpenApi() {
        String[] paths = {"/api/order/**"};
        return GroupedOpenApi.builder().group("Order").pathsToMatch(paths)
                .build();
    }
}
