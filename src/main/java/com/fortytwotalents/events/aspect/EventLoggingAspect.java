package com.fortytwotalents.events.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class EventLoggingAspect {

	@Pointcut(value = "execution(* com.fortytwotalents.events.service.*.*(..))")
	public void anyServiceMethod() {
	}

	@Around("anyServiceMethod() && @annotation(ProfileExecution)")
	public Object profileExecuteMethod(ProceedingJoinPoint jointPoint) throws Throwable {
		Signature signature = jointPoint.getSignature();
		String methodName = signature.toShortString();

		StopWatch stopWatch = new StopWatch(EventLoggingAspect.class.getName());
		stopWatch.start(methodName);

		Object value = jointPoint.proceed();

		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
		
		return value;
	}

}
