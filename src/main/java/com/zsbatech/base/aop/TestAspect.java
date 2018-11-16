package com.zsbatech.base.aop;

import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.utils.CommonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

import static com.zsbatech.base.utils.IpUtils.getClientAddr;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/7 10:03
 */
@Order(6)
@Aspect
@Component
public class TestAspect {
    private static Logger logger = LoggerFactory.getLogger(TestAspect.class);

    @Pointcut("execution(public * com.zsbatech..*.controller..*.*(..))")
    public void webRequestLogTest() {
    }

    @Around("webRequestLogTest()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("test aspect !!!!");
        Object result = joinPoint.proceed();
        ResponseData responseData = (ResponseData) result;
        return result;
    }
}
