package com.example.carshopwithspringboot.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ApiDoc for Car Service",
                contact = @Contact(
                        name = "Qudratilla Salimov",
                        email = "SalimovSoftwareDeveloper@gmail.com",
                        url = "https://github.com/Qudrat111"
                ),
                version = "1.0",
                termsOfService = "privacy",
                description = "This project for manipulation car services"
        ),
        tags = @Tag(
                name = "About api", description = "This project made with rest architecture"
        )
)
public class SwaggerConfig {


    // Swagger configuration with java based
//    @Bean
//    public OpenAPI getOpenAPI() {
//        return new OpenAPI()
//                .info(
//                        new Info()
//                                .title("ApiDoc for Car Service")
//                                .contact(
//                                        new Contact()
//                                                .email("SalimovSoftwareDeveloper@gmail.com")
//                                                .name("Qudratilla Salimov")
//                                                .url("https://github.com/Qudrat111")
//                                )
//                                .description("This project for manipulation car services")
//                                .termsOfService("privacy")
//                                .version("1.0")
//                );
//    }
}
