package org.andreu.tarea2.msvcintervinientes;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// Aplicación Spring Boot para el microservicio de Intervinientes
@SpringBootApplication
public class MsvcIntervinientesApplication {

	// Bean para ModelMapper (mapeo de entidades)
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	// Método main de la aplicación Spring Boot
	public static void main(String[] args) {
		SpringApplication.run(MsvcIntervinientesApplication.class, args);
	}

}
