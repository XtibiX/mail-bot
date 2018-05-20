package com.fortytwotalents.events;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.fortytwotalents.events.aspect.EventLoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

	@Bean
	public EventLoggingAspect eventLoggingAspect() {
		return new EventLoggingAspect();
	}

}
