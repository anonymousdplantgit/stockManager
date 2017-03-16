package com.soucreation;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.boot.autoconfigure.web.WebMvcProperties.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class Config extends WebMvcConfigurerAdapter{
	
	@Bean
	FormattingConversionServiceFactoryBean conversionService(){
		Set<Object> formatters=new HashSet<>();
		formatters.add(new DateFormatter());
		FormattingConversionServiceFactoryBean fcsfb=new FormattingConversionServiceFactoryBean();
		fcsfb.setFormatters(formatters);
		return fcsfb;
		
	}
	
	@Bean
	ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource messageSource= new ResourceBundleMessageSource();
		messageSource.setBasename("Messages");
		
		return messageSource;
	}
	
	@Bean
	public SessionLocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.ENGLISH);
	    return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
