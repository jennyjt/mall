package com.zsbatech.base.interceptor;

import com.zsbatech.base.constants.InitConstant;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.utils.AuthUtils;
import com.zsbatech.base.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TreeMap;

import static com.zsbatech.base.utils.IpUtils.getClientAddr;


/**
 * description: 全局参数拦截
 *
 * @author wxcsdb88
 * @since 2017/8/23 16:32
 */


public class ParameterInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(ParameterInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        String token = request.getHeader(RequestField.TOKEN);
        String queryString = request.getQueryString();
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String beanName = handlerMethod.getBeanType().getName();
        String methodName = handlerMethod.getMethod().getName();

        // todo verify the header
        AuthUtils.verifyHeader(beanName, methodName, contentType, token);

        TreeMap<String, String> parametersMap = RequestUtils.parseQueryStringToMap(queryString, false);
        // todo verify the timestamp
        String timestamp = parametersMap.get(RequestField.TIMESTAMP);
        //读取时间差 maxDiff
        AuthUtils.verifyTimestamp(beanName, methodName, timestamp, InitConstant.TIMESTAMP_DIFF);
        // todo verify the necessary fields
        AuthUtils.verifyRequiredParameter(RequestField.TIMESTAMP, RequestField.SIGN);
        // todo verify the parameter in allowed map
        AuthUtils.verifyParameter(beanName, methodName, parametersMap);
        // verify the sign
//        AuthUtils.verifySign(beanName, methodName, parametersMap);

        // verify the token
        // verify the rateLimit

        String url = request.getRequestURL().toString();
        String remoteAddr = getClientAddr(request);
        String localAddr = request.getLocalAddr();

        String method = request.getMethod();
        String outputLog = "({}.{}) ParameterInterceptor[method={} url={} from={} to={} parameters=({})]";
        logger.debug(outputLog, beanName, methodName, method, url, remoteAddr, localAddr, queryString);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
