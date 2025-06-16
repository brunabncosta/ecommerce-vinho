package br.com.ecommerce;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "E-commerce API", version = "v1"))
public class MicrosserviceEcomApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrosserviceEcomApplication.class, args);
	}

}
