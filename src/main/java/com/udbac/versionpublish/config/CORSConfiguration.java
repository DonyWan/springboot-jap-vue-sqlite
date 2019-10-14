package com.udbac.versionpublish.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CORSConfiguration {
    private CorsConfiguration buidConfig() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        configuration.addAllowedOrigin(CorsConfiguration.ALL);
//		configuration.addExposedHeader(HttpHeaders.CONTENT_DISPOSITION);

//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//        configuration.setAllowedHeaders(
//                Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));

        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin",
                "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, "
                        + "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));

        return configuration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buidConfig());
        return new CorsFilter(source);
    }
}
