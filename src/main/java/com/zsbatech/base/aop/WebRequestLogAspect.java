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
@Order(5)
@Aspect
@Component
public class WebRequestLogAspect {
    private static Logger logger = LoggerFactory.getLogger(WebRequestLogAspect.class);

    @Pointcut("execution(public * com.zsbatech..*.controller..*.*(..))")
    public void webRequestLog() {
    }

    @Around("webRequestLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.nanoTime();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            logger.error("error request!");
            return null;
        }
        HttpServletRequest request = attributes.getRequest();
        String queryString = request.getQueryString();
        if (!CommonUtils.isEmpty(queryString)) {
            queryString = URLDecoder.decode(queryString, "UTF-8");
        }
        String beanName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String uri = request.getRequestURL().toString();

        String remoteAddr = getClientAddr(request);
        String localAddr = request.getLocalAddr();
        String token = request.getHeader("token");
        String method = request.getMethod();

        Object result = joinPoint.proceed();
        double costTime = (System.nanoTime() - beginTime) * 1.0 / 1000000;

        ResponseData responseData = (ResponseData) result;
        int code = responseData.getCode();
        String msg = responseData.getMsg();
        String outputLog = "({}.{}) doAround API_INFO[method={} uri={} token={} code={} from={} to={} cost={}ms parameters=({})] msg=({})";
        logger.info(outputLog, beanName, methodName, method, uri, token, code, remoteAddr, localAddr, String.format("%1$.3f", costTime), queryString, msg);
        return result;
    }
}
