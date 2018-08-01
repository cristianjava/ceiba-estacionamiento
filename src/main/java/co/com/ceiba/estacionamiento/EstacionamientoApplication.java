package co.com.ceiba.estacionamiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"co.com.ceiba.estacionamiento.negocio.model","co.com.ceiba.estacionamiento.negocio.dao","co.com.ceiba.estacionamiento"})
@EntityScan("co.com.ceiba.estacionamiento.negocio.entity")
public class EstacionamientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstacionamientoApplication.class, args);
	}
}
