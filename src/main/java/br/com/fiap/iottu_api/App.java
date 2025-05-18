package br.com.fiap.iottu_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "API Iottu", version = "v1", description = "API do SaaS de controle de motos", contact = @Contact(name = "Iottu", email = "contato@iottu.com.br")))
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
