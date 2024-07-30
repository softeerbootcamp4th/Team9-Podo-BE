package com.softeer.podo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableJpaAuditing
@EnableRedisRepositories
@SpringBootApplication
public class PodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PodoApplication.class, args);
	}

}
