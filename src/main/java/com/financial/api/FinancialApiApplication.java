package com.financial.api;

import com.financial.api.com.financial.api.config.properties.ApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperties.class)
public class FinancialApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialApiApplication.class, args);
	}
}
