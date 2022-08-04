package com.example.voard.Aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.stereotype.Component;

/*@Component
@Aspect
public class TimeAopTest {
    
    @Around("execution(* com.example.voard.Service..*.*(..))")
    public Object aopTest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();

        System.out.println("=> AopName - " + proceedingJoinPoint.getSignature().getName());
        return result;
    }
}*/
