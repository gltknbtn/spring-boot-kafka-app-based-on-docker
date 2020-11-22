package com.gltknbtn.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gltknbtn.annotation.LogInOut;
import com.gltknbtn.enums.LogDirection;
import com.gltknbtn.enums.ResponseStatus;
import com.gltknbtn.producer.LoggingProducer;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

@Aspect
@Component
public class LogInOutAspect {

    private Logger logger = Logger.getLogger(LogInOutAspect.class);

    private LoggingProducer loggingProducer;
    @Autowired
    public LogInOutAspect(LoggingProducer loggingProducer){
        this.loggingProducer = loggingProducer;
    }

    @Around("@annotation(com.gltknbtn.annotation.LogInOut)")
    public Object logInOut(ProceedingJoinPoint joinPoint) throws Throwable{
        String correlationId = UUID.randomUUID().toString();
        long tic = System.currentTimeMillis();
        LogInOut logInOutAnno = null;
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            Method method = signature.getMethod();
            logInOutAnno = method.getAnnotation(LogInOut.class);

            //log input if necessary
            logInput(joinPoint.getArgs(), logInOutAnno.logInput(), correlationId);

            //invoke method
            Object proceed = joinPoint.proceed();
            long toc = System.currentTimeMillis();
            long executionTime = toc - tic;
            logger.info("execution time: " + executionTime +" ms.");

            //log output if necessary
            logOutput(logInOutAnno.logOutput(), proceed, correlationId, ResponseStatus.SUCCESS.getStatus(), executionTime);

            return proceed;
        }catch (Exception e){
            System.out.println("Exception occurred in aspect LoginOutAspect: "+ e.getMessage());
            long toc = System.currentTimeMillis();
            long executionTime = toc - tic;
            if(logInOutAnno != null)
                logOutput(logInOutAnno.logOutput(), e.getMessage(), correlationId, ResponseStatus.FAIL.getStatus(), executionTime);
            throw  e;
        }

    }

    private void logOutput(boolean isLogOutput, Object retVal, String correlationId, int status, long executionTime) throws JsonProcessingException {
        long tic = System.currentTimeMillis();
        if (isLogOutput) {
            loggingProducer.logInputOutput(retVal, LogDirection.RESPONSE, correlationId, status, executionTime);
        }
        long toc = System.currentTimeMillis();
        logger.info("log output elapsed " + (toc - tic) + " ms.");
    }

    private void logInput(Object inOutMsgObject, boolean isLogInput, String correlationId) throws JsonProcessingException {
        long tic = System.currentTimeMillis();
        if (isLogInput) {
            // async..
            loggingProducer.logInputOutput(inOutMsgObject, LogDirection.REQUEST, correlationId, null, null);
        }
        long toc = System.currentTimeMillis();
        logger.info("log input elapsed " + (toc - tic) + " ms.");
    }
}
