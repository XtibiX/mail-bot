package com.fortytwotalents.events;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ServiceConfig.class, RepositoryConfig.class, AspectConfig.class })
@ComponentScan(basePackages = { "com.fortytwotalents.events.service", "com.fortytwotalents.events.repository" })
public class EventsConfig {

}
