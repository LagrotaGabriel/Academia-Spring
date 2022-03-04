package br.com.academia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcademiaApplication {

	public static void main(String[] args) {
		System.err.println("Teste");
		SpringApplication.run(AcademiaApplication.class, args);
	}

}
