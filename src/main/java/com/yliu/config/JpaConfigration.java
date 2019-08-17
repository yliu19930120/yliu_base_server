package com.yliu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableJpaRepositories("com.yliu.dao.*")
@EnableMongoAuditing
@EnableSpringDataWebSupport
public class JpaConfigration {
	
}
