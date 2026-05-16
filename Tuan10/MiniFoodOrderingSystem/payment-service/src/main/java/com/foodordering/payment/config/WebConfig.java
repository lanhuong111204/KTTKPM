package com.foodordering.payment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cấu hình WebMVC
 * Bao gồm CORS và các cấu hình khác
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Cấu hình CORS cho API
     */
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //             .allowedOriginPatterns("*")
    //             .allowedMethods("*")
    //             .allowedHeaders("*")
    //             .allowCredentials(true)
    //             .maxAge(3600);
    // }
}
