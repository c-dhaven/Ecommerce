package com.project.Ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EcommerceApplication.class, args);
		System.out.println(context.getBeanFactory().toString());
		System.out.println("Application Running!");
	}

}
