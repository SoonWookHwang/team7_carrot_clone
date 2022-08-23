package com.innovation.team7_carrot_clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class Team7CarrotCloneApplication {
	public static void main(String[] args) {
		SpringApplication.run(Team7CarrotCloneApplication.class, args);
	}
}
