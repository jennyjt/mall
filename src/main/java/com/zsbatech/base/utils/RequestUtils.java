package com.zsbatech.base.utils;

import com.zsbatech.base.common.RequestDO;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.zsbatech.base.utils.IpUtils.getClientAddr;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/7 10:07
 */
public class RequestUtils {
    private static Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    /**
     * 当前请求 参数
     *
     * @param request {@link HttpServletRequest}
     * @return {@link RequestDO}
     */
    public static RequestDO getCurrentRequestDO(HttpServletRequest request) {
        RequestDO requestDO = new RequestDO();
        String queryString = request.getQueryString();
        try {
            if (queryString != null && !"".equals(queryString)) {
                queryString = URLDecoder.decode(queryString, "UTF-8");
            }
        } catch (UnsupportedEncodingException ex) {
            queryString = "";
            String msg = String.format("%s : exception %s", RequestUtils.class.getName(), ex.getMessage());
            logger.error(msg);
        }

        requestDO.setMethod(request.getMethod());
        requestDO.setQueryString(queryString);
        // 真实ip地址获取
        requestDO.setClientAddr(getClientAddr(request));
        requestDO.setScheme(request.getScheme());
        requestDO.setScheme(request.getScheme());
        requestDO.setProtocol(request.getProtocol());
        requestDO.setRequestURL(request.getRequestURL().toString());
        requestDO.setRequestURI(request.getRequestURI());
        requestDO.setLocalPort(request.getLocalPort());
        requestDO.setLocalName(request.getLocalName());
        requestDO.setLocalAddr(request.getLocalAddr());
        requestDO.setServerName(request.getServerName());
        requestDO.setServerPort(request.getServerPort());
        requestDO.setRemoteHost(request.getRemoteHost());
        requestDO.setRemoteAddr(request.getRemoteAddr());
        return requestDO;
    }

    public static String requestParamsOutput(HttpServletRequest request) {
        StringBuilder requestParamMap = new StringBuilder("[");
        String requestParams = "";
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String[] val = entry.getValue();
            if (val.length == 1) {
                requestParamMap.append(entry.getKey() + "=" + entry.getValue()[0] + ",");
            } else {
                requestParamMap.append(entry.getKey() + "=" + Arrays.toString(entry.getValue()) + ",");
            }
        }
        requestParams = requestParamMap.subSequence(0, requestParamMap.length() - 1).toString() + "]";
        return requestParams;
    }

    public static List<NameValuePair> parseQueryParamMapToBasicNameValuePair(List<NameValuePair> params, TreeMap<String, String> queryParamMap) {
        queryParamMap.forEach((k, v) -> {
            params.add(new BasicNameValuePair(k, v));
        });
        return params;
    }

    public static String parseQueryParamMapToQuereyString(TreeMap<String, String> queryParamMap, String... removeKeys) {
        return parseQueryParamMapToQuereyString(queryParamMap, false, removeKeys);
    }

    public static String parseQueryParamMapToQuereyString(TreeMap<String, String> queryParamMap, boolean ignoreNullValue, String... removeKeys) {
        if (queryParamMap == null || queryParamMap.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Arrays.stream(removeKeys).forEach(key -> {
            queryParamMap.remove(key);
        });
        queryParamMap.forEach((k, v) -> {
            if (!ignoreNullValue) {
                builder.append(k).append("=").append(v).append("&");
            }
        });
        if (!queryParamMap.isEmpty()) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public static String parseQueryString(String queryString, boolean ignoreNullValue, String... removeKeys) {
        if (queryString == null || "".equals(queryString)) {
            return "";
        }
        TreeMap<String, String> queryMap = parseQueryStringToMap(queryString, ignoreNullValue, removeKeys);
        StringBuilder builder = new StringBuilder();
        queryMap.forEach((k, v) -> {
            builder.append(k).append("=").append(v).append("&");
        });
        if (!queryMap.isEmpty()) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public static TreeMap<String, String> parseQueryStringToMap(String queryString, boolean ignoreNullValue, String... removeKeys) {
        TreeMap<String, String> sortMap = new TreeMap<String, String>();
        if (CommonUtils.isEmpty(queryString)) {
            return sortMap;
        }
        String[] queryParamPairs = queryString.split("&");
        Arrays.stream(queryParamPairs).forEach((arrayItem) -> {
            String[] kv = arrayItem.split("=");
            if (kv.length == 1) {
                if (!ignoreNullValue) {
                    sortMap.put(kv[0], "");
                }
            } else {
                sortMap.put(kv[0], kv[1]);
            }
        });

        Arrays.stream(removeKeys).forEach(key -> {
            sortMap.remove(key);
        });
        return sortMap;
    }

    public static String getSign(String queryString, boolean ignoreNullValue, boolean toUpper, String... removeKeys) {
        String queryStr = parseQueryString(queryString, ignoreNullValue, removeKeys);
        return toUpper ? Md5Utils.MD5(queryStr).toUpperCase() : Md5Utils.MD5(queryStr);
    }

    public static String getSign(TreeMap<String, String> parameterMap, boolean toUpper, String... removeKeys) {
        String queryStr = parseQueryParamMapToQuereyString(parameterMap, removeKeys);
        return toUpper ? Md5Utils.MD5(queryStr).toUpperCase() : Md5Utils.MD5(queryStr);
    }

    public static String getSign(String queryString, boolean toUpper, String... removeKeys) {
        return getSign(queryString, false, toUpper);
    }

    /**
     * @param scheme     http
     * @param ipOrDomain www.wxcsdb88.com
     * @param port       9984
     * @param prefix     /uniledger/v1
     * @return http://www.wxcsdb88.com:9984/uniledger/v1
     */
    public static StringBuilder getFullUrl(String scheme, String ipOrDomain, Integer port, String prefix) throws Exception {
        return getFullUrl(scheme, ipOrDomain, port, prefix, null);
    }

    /**
     * @param scheme     http
     * @param ipOrDomain www.wxcsdb88.com
     * @param port       9984
     * @param prefix     /uniledger/v1
     * @param requestURI /common/getChainNodeDetail
     * @return http://www.wxcsdb88.com:9984/uniledger/v1/common/getChainNodeDetail
     */
    public static StringBuilder getFullUrl(String scheme, String ipOrDomain, Integer port, String prefix, String requestURI) throws Exception {
        if (scheme == null || "".equals(scheme)) {
            scheme = "http";
        }
        if (ipOrDomain == null || "".equals(ipOrDomain)) {
            throw new Exception("ip or domain is null");
        }
        StringBuilder url = new StringBuilder(scheme).append("://").append(ipOrDomain);
        if (port != null) {
            url.append(":").append(port);
        }
        if (prefix != null && !"".equals(prefix)) {
            url.append(prefix);
        }
        if (requestURI != null && !"".equals(requestURI)) {
            url.append(requestURI);
        }
        return url;
    }

    /**
     * @param scheme     http
     * @param ipOrDomain www.wxcsdb88.com
     * @param port       9984
     * @param prefix     /uniledger/v1
     * @param dataMap    {"a":1, "b":2}
     * @return http://www.wxcsdb88.com:9984/uniledger/v1?a=1&b=2
     */
    public static StringBuilder getFullUrlWithMapParams(String scheme, String ipOrDomain, Integer port, String prefix, Map<String, Object> dataMap) throws Exception {
        return getFullUrlWithMapParams(scheme, ipOrDomain, port, prefix, null, dataMap);
    }

    /**
     * @param scheme     http
     * @param ipOrDomain www.wxcsdb88.com
     * @param port       9984
     * @param prefix     /uniledger/v1
     * @param requestURI /common/getChainNodeDetail
     * @param dataMap    {"a":1, "b":2}
     * @return http://www.wxcsdb88.com:9984/uniledger/v1/common/getChainNodeDetail?a=1&b=2
     */
    public static StringBuilder getFullUrlWithMapParams(String scheme, String ipOrDomain, Integer port, String prefix, String requestURI, Map<String, Object> dataMap) throws Exception {
        StringBuilder url = getFullUrl(scheme, ipOrDomain, port, prefix, requestURI);
        if (dataMap != null && dataMap.size() >= 1) {
            url.append("?");
            for (Map.Entry<String, Object> v : dataMap.entrySet()) {
                url.append(v.getKey()).append("=").append(v.getValue()).append("&");
            }
            url.subSequence(0, url.length() - 1);
        }
        return url;
    }

}
