package com.dev.employeeapp.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    //setup Logger
    private Logger logger = Logger.getLogger(getClass().getName());

    //setup pointcut dec;arations
    @Pointcut("execution(* com.dev.employeeapp.controller.*.*(..))")
    private void forControllerPackage() {
    }

    //do the same for service and dao
    @Pointcut("execution(* com.dev.employeeapp.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.dev.employeeapp.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    //add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();

        //display method we are calling
        logger.info("========>> in @Before: calling method: "+method);

        //display the arguments to the method


        //get the arguments
        Object[] args = joinPoint.getArgs();

        //loop thru and display args
        for (Object arg:args){
            logger.info("=======>> arguments: "+arg);
        }

    }



}
