package com.udbac.versionpublish.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CORSConfiguration {
	private CorsConfiguration buidConfig() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedHeader(CorsConfiguration.ALL);
		configuration.addAllowedMethod(CorsConfiguration.ALL);
		configuration.addAllowedOrigin(CorsConfiguration.ALL);
		configuration.setAllowCredentials(true);
		return configuration;
	}
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buidConfig());
		return new CorsFilter(source);
	}
}
