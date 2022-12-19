package com.jgmp2022.jgmp2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/spring/spring-config.xml")
public class Jgmp2022Application {

	public static void main(String[] args) {
		SpringApplication.run(Jgmp2022Application.class, args);
	}

}
