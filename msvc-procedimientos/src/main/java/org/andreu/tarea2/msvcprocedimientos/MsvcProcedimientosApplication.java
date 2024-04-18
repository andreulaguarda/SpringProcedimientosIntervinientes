package org.andreu.tarea2.msvcprocedimientos;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

// Aplicación Spring Boot para el microservicio de Procedimientos
@SpringBootApplication
@EnableFeignClients
public class MsvcProcedimientosApplication {

	// Bean para ModelMapper (mapeo de entidades)
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	// Método main de la aplicación Spring Boot
	public static void main(String[] args) {
		SpringApplication.run(MsvcProcedimientosApplication.class, args);
	}

}
