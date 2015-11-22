package com.sks.predix.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@SpringBootApplication
@EnableResourceServer
public class TodoServiceApplication extends ResourceServerConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(TodoServiceApplication.class, args);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/todo/**").authorizeRequests().anyRequest()
				.authenticated();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources)
			throws Exception {
		resources.resourceId("openid");
	}

	@Bean
	public EvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}

}
