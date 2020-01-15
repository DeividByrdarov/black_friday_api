package com.dbardarov.blackfriday;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class BlackfridayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackfridayApplication.class, args);
	}

	@Bean
	public GraphQLScalarType dateTime()
	{
		return ExtendedScalars.DateTime;
	}

}
