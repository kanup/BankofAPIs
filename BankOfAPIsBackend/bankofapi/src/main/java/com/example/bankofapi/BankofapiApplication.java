package com.example.bankofapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.example.bankofapi")
@EnableEurekaClient
public class BankofapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankofapiApplication.class, args);
	}

}
