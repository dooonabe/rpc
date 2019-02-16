package com.dooonabe.drpc.aspect;

import com.dooonabe.drpc.entity.TimeUsedEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wangtt19184 on 2019/2/16.
 */
@Aspect
@Component
public class TimeUsedAspect {
    private static Logger logger = LoggerFactory.getLogger(TimeUsedAspect.class);

    @Pointcut("execution(* com.dooonabe.drpc.controller.*Controller.*(..))")
    private void countTimeUsed() {
    }

    @Around(value = "countTimeUsed()")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        TimeUsedEntity entity = new TimeUsedEntity();
        entity.setClassName(joinPoint.getTarget().getClass().getName());
        entity.setMethodName(joinPoint.getSignature().getName());
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed();
        entity.setUsedTime(System.nanoTime() - startTime);
        logger.info("{}", entity);
        return result;
    }

}
