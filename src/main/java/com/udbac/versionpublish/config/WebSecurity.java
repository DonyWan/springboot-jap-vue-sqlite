package com.udbac.versionpublish.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurity implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration intercept = registry.addInterceptor(new TokenInterceptor());
        intercept.addPathPatterns("/**");
        intercept.excludePathPatterns("/user/login", "/user/getToken");
        intercept.excludePathPatterns("/provinces/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
