package com.example.banktransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EntityScan(basePackages = "com.example.banktransaction.model")
public class BanktransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanktransactionApplication.class, args);
	}

}
