package com.itstyle.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ServiceLogAspect.class);

    @Around("@annotation(com.itstyle.common.aspect.ServiceAopLog)")
    protected Object logServiceInvoke(ProceedingJoinPoint pjp)
            throws Throwable {
        return doLog(pjp);
    }

    protected Object doLog(ProceedingJoinPoint pjp) throws Throwable {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("*****调用服务:" + pjp.getSignature().toLongString() + "*****");
            for (Object arg : pjp.getArgs())
                printObj(arg, "服务参数:");
            try {
                Object retVal = pjp.proceed();
                printObj(retVal, "返回结果:");
                return retVal;
            } catch (Throwable e) {
                LOGGER.info("抛出异常", e);
                throw e;
            } finally {
                LOGGER.info("*****调用服务结束*****");
            }
        }
        return pjp.proceed();
    }

    void printObj(Object arg, String prefix) {
    }
}