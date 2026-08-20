package com.diogo.raizesdonordeste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RaizesdonordesteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaizesdonordesteApplication.class, args);
	}

}
