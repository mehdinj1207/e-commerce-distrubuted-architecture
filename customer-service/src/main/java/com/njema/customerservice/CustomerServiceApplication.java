package com.njema.customerservice;

import com.njema.customerservice.entities.Customer;
import com.njema.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.saveAll(List.of(
					Customer.builder()
							.name("Mehdi")
							.email("mehdi@gmail.com")
							.build(),
					Customer.builder()
							.name("Mohamed")
							.email("med@gmail.com")
							.build(),
					Customer.builder()
							.name("Fatma")
							.email("fatma@gmail.com")
							.build(),
					Customer.builder()
							.name("Ala")
							.email("ala@gmail.com")
							.build()
			));
			customerRepository.findAll().forEach(System.out::println);

		};
	}

}
