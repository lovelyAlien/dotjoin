package com.dangsan.dotjoin;

import com.dangsan.dotjoin.infra.jwt.JWTProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({JWTProperties.class})
public class DotjoinApplication {

	public static void main(String[] args) {
		System.out.println("loading...");
		SpringApplication.run(DotjoinApplication.class, args);
		System.out.println("running...");
	}

}
