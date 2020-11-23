package com.quick.start;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	@Bean
	public LocaleResolver localeResolver() {
//		SessionLocaleResolver localResokver= new SessionLocaleResolver();
		AcceptHeaderLocaleResolver localResokver= new AcceptHeaderLocaleResolver();
		localResokver.setDefaultLocale(Locale.US);
		return localResokver;
	}
	@Bean
	public 	MessageSource messageSource() {
		ResourceBundleMessageSource bundleMessage= new ResourceBundleMessageSource();
		bundleMessage.setBasename("messages");
		bundleMessage.setFallbackToSystemLocale(false);
		return bundleMessage;
	}

}
