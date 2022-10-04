package com.tamingthymeleaf.tamingthymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@SpringBootApplication
public class TamingThymeleaf3Application {

	public static void main(String[] args) {
		SpringApplication.run(TamingThymeleaf3Application.class, args);
	}

	private ITemplateResolver stringTemplateResolver() {
		final StringTemplateResolver templateResolver = new StringTemplateResolver();
		templateResolver.setTemplateMode(TemplateMode.HTML);
		return templateResolver;
	}

	@Bean
	public TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(stringTemplateResolver());
		return templateEngine;
	}

}
