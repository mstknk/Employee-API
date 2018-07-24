package com.code.challenge.employee.api.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
//				.securitySchemes(Arrays.asList((new BasicAuth("basicAuth"))))
				.select().apis(RequestHandlerSelectors.basePackage("com.code.challenge.employee.api.controllers"))
				.paths(regex("/employee-api.*")).build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Employee REST API", "Employee API ­ Coding Challenge", "1.0", "Terms of service",
				new Contact("Mesut KONAK", "", "mesutkonak89@gmail.com"), "", "", new ArrayList<VendorExtension>());
		return apiInfo;
	}

}
