package com.fortytwotalents.events;

import no.normann.mail.jpa.RepositoryConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ServiceConfig.class, RepositoryConfig.class})
@ComponentScan(basePackages = { "no.normann.mail" })
public class EventsConfig {

}
