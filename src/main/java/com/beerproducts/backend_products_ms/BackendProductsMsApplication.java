package com.beerproducts.backend_products_ms;

import com.beerproducts.backend_products_ms.services.AuditorAwareImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class BackendProductsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendProductsMsApplication.class, args);
	}

	@Bean
	public AuditorAware<String> myAuditorAware() {
		return new AuditorAwareImpl();
	}

}
