package br.com.academia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.academia.models"})
@EnableJpaRepositories("br.com.academia.repositories")
@ComponentScan({"br.com.academia.controllers"})
@ComponentScan({"br.com.academia.services"})
@ComponentScan({"br.com.academia.exceptions"})
public class AcademiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);
	}

}
