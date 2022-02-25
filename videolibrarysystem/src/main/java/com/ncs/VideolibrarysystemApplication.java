package com.ncs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class VideolibrarysystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideolibrarysystemApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("This is a Video Library System") String appDesciption,
			@Value("1.0.0") String appVersion) {
		return new OpenAPI()

				.info(new Info().title("Video Library System").version(appVersion).description(appDesciption)
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
