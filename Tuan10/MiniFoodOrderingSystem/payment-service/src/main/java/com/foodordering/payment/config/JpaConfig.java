package com.foodordering.payment.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * JPA Configuration - explicitly enable entity and repository scanning
 */
@Configuration
@EntityScan(basePackages = {"com.foodordering.payment.entity"})
@EnableJpaRepositories(basePackages = {"com.foodordering.payment.repository"})
public class JpaConfig {
}
