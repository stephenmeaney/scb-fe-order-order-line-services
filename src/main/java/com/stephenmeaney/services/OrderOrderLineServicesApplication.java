package com.stephenmeaney.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
public class OrderOrderLineServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderOrderLineServicesApplication.class, args);
	}
}
