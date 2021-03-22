/**
 * 
 */
package com.localhost.vaccination;

import static com.localhost.vaccination.constants.VaccinationConstants.BASE_PACKAGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main class to instantiate the spring boot application.
 * 
 * @author Rohit
 *
 */
@SpringBootApplication
@EnableSwagger2
public class SpringBootApplicationVaccNow {
	/**
	 * Main method to instantiate the vaccination app.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationVaccNow.class, args);

	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE)).build();
	}

}
