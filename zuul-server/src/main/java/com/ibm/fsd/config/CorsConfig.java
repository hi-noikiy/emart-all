package com.ibm.fsd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Global cross domain configuration
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
    @Bean
    public CorsFilter corsFilter() {
    	final CorsConfiguration config = new CorsConfiguration();
    	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        config.addAllowedOrigin("*");  // If it is set to *, it will be automatically converted to origin in the current request header
        config.setAllowCredentials(true); // Allow cookies to cross domains
        config.addAllowedMethod("*"); // Method to allow submission of requests, * means all are allowed
        config.addAllowedHeader("*");  // Access allowed header information, * indicates all
        config.setMaxAge(3600L); // Pre check the cache time of the request (seconds), that is, in this time period, the same cross domain request will not be pre checked again
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
