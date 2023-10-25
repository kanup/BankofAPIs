package com.nkdebug.gatewayms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableEurekaClient
@CrossOrigin(origins = "*")
public class GatewaymsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewaymsApplication.class, args);
	}

}
