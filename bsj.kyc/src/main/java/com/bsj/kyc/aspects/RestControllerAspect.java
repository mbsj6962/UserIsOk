package com.bsj.kyc.aspects;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestControllerAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    Counter registeredPerson = Metrics.counter("com.bsj.selfDeclaredInfo.created");

    @Before("execution(public * com.bsj.kyc.controller.*Controller.*(..))")
    public void generalAllMethodAspect(){
        logger.info("All Method Call invoke this general aspect");
    }

    @AfterReturning("execution(public * com.bsj.kyc.controller.*Controller.personalVideo(..))")
    public void getCallOnPersonRegistration(){
        logger.info("This aspect is fired when the personalVide method of controller is called");
        registeredPerson.increment();
    }


}
