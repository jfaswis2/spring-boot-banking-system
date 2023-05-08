package com.example.springbootbankingsystem.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class ConfigApi {

    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info().title("Spring boot Banking System")
                        .description("Banking System API rest docs")
                        .contact(new Contact().email("jfaswis22@gmail.com").name("Jhon"))
                        .version("1.0")
                        .license(new License().name("Banking System API")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring boot Banking System Documentation"));

    }
}
