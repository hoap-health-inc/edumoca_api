package com.edumoca.soma.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages = {"com.edumoca.soma.*"
        ,"com.edumoca.soma.services.*"
})
@EnableJpaRepositories("com.edumoca.soma.entities.*")
@EnableMongoRepositories("com.edumoca.soma.entities.*")
@EntityScan(value = {"com.edumoca.soma.entities"})
@OpenAPIDefinition(
		info = @Info(title = "Edumoca admin API"),
		servers = {@Server(url = "http://localhost:8080")}
		)
@SecurityScheme(name="BearerJWT", type=SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT", description = "Bearer token for the project.")
@EnableConfigurationProperties
public class ServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicesApplication.class, args);
    }
}
