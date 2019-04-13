package com.yliu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableJpaRepositories("com.yliu.dao.*")
@EnableMongoAuditing
public class JpaConfigration {
	
}
