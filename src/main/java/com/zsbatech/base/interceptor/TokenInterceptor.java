package com.zsbatech.base.interceptor;

import com.alibaba.fastjson.JSON;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import com.zsbatech.base.exception.AuthorizationException;
import com.zsbatech.base.utils.CommonUtils;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.baasKettleManager.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wxcsdb88 on 2017/5/23 20:17.
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(RequestField.TOKEN);
        if (CommonUtils.isEmpty(token)) {
            throw new AuthorizationException(Response.INVALID_TOKEN, "token 为空！");
        }
      
        HttpUtils httpUtils = new HttpUtils();
        String baseUrl = ConfigUtil.getPropertyValue("unipassport.url");
        String url = baseUrl+"/auth/validate";
        String requestStr = httpUtils.post(url, token);
        logger.debug(url, token);
       
        Map<String,Object> resultMap = JSON.parseObject(requestStr);
        //这块需要统一用户中心修改一下对应的接口；
        if("验证通过!".equals(resultMap.get("msg"))){
        	return true;
        }
        else{
        	throw new AuthorizationException(Response.INVALID_TOKEN, "token 不正确！");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
