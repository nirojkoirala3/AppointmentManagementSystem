package edu.mum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "edu.mum")
public class AppointmentManagement {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentManagement.class, args);
	}
}
