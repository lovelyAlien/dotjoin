package com.dangsan.dotjoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DotjoinApplication {

	public static void main(String[] args) {
		System.out.println("loading...");
		SpringApplication.run(DotjoinApplication.class, args);
		System.out.println("running...");
	}

}
