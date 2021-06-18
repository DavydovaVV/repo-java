package com.epam.rd.davydova.assignment.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a class of LoggingAdvice
 */
@Aspect
public class LoggingAdvice {
    public Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    /**
     * Log annotated method
     *
     * @param joinPoint proceeding join point
     */
    @Before("@annotation(com.epam.rd.davydova.assignment.annotation.Logging)")
    public void logMethod(JoinPoint joinPoint) {
        var mapper = new ObjectMapper();
        var invokedMethodName = joinPoint.getSignature().getName();
        Object[] inputArguments = joinPoint.getArgs();
        try {
            log.info(invokedMethodName +
                    " : " + mapper.writeValueAsString(inputArguments));
        } catch (Throwable throwable) {
            log.error("Exception is: ", throwable);
        }
    }
}
