package com.epam.rd.davydova.assignment.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
     * @return result of proceeding the advice
     */
    @Around("@annotation(com.epam.rd.davydova.assignment.annotation.Logging)")
    public Object logMethod(ProceedingJoinPoint joinPoint) {
        var mapper = new ObjectMapper();
        var invokedMethodName = joinPoint.getSignature().getName();
        Object[] inputArguments = joinPoint.getArgs();
        try {
            var proceed = joinPoint.proceed();
            log.info(invokedMethodName +
                    " : " + mapper.writeValueAsString(inputArguments));
            return proceed;
        } catch (Throwable throwable) {
            log.error("Exception is: ", throwable);
        }
        return null;
    }
}
