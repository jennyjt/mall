package com.zsbatech.base.utils;

import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import com.zsbatech.base.exception.AuthorizationException;
import com.zsbatech.base.model.UniToken;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Anzai
 * Date: 2018-01-08
 * Time: 15:23
 */
public class JWTUtils {
    private static final String JWT_SECERT = "unipassport-secert-key";

    /**
     * 校验token
     *
     * @param request
     * @return
     */
    public static UniToken validateToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        return validateToken(token);
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static UniToken validateToken(String token) {
        SecretKey secretKey = generalKey(JWT_SECERT);
        Claims claims = null;
        UniToken uniToken = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            uniToken = new UniToken()
                    .setUserId(Long.parseLong(claims.get(RequestField.USERID).toString()))
                    .setUsername(claims.get(RequestField.USERNAME).toString())
                    .setRole((List<Long>) claims.get(RequestField.ROLE))
                    .setToken(token);
            if (uniToken.getUserId() == null || CommonUtils.isEmpty(uniToken.getUsername()) || CommonUtils.isEmpty(uniToken.getRole())) {
                throw new AuthorizationException();
            }
        } catch (ExpiredJwtException exp) {
            throw new AuthorizationException(Response.LOGIN_TIMEOUT, "token失效，请重新登录！");
        } catch (Exception exp) {
            throw new AuthorizationException(Response.LOGIN_TIMEOUT, "用户未登录或角色有误！");
        }
        return uniToken;
    }
    public static UniToken validateTokenAndOrgan(HttpServletRequest request) {
        String token = request.getHeader("token");
        return validateToken(token);
    }
    public static UniToken validateTokenAndOrgan(String token){
        UniToken uniToken = validateToken(token);

        uniToken.setOrganization(com.zsbatech.baasKettleManager.util.HttpUtils.getUserOrgan(uniToken.getUserId(),token));

        return uniToken;
    }

    /**
     * 生成秘钥
     *
     * @return
     */
    private static SecretKey generalKey(String key) {
        byte[] encodedKey = Base64.decodeBase64(key.getBytes());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}
