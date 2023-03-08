package com.example.msserverapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsServerApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsServerApiGatewayApplication.class, args);
	}

}
