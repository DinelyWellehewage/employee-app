package com.dev.employeeapp.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
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
    private void forAppFlow() {
    }

    //add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();

        //display method we are calling
        logger.info("========>> in @Before: calling method: " + method);

        //display the arguments to the method


        //get the arguments
        Object[] args = joinPoint.getArgs();

        //loop thru and display args
        for (Object arg : args) {
            logger.info("=======>> arguments: " + arg);
        }
    }

    //add #AFterReturning
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint joinPoint, Object theResult) {

        //display method we are returning from
        String method = joinPoint.getSignature().toShortString();
        logger.info("========>> in @AfterReturning: from method: " + method);

        //display the data returned
        logger.info("=======>>> result: " + theResult);


    }


}
