package com.zsbatech.base.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.zsbatech.base.constants.InitConstant;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/5/10 11:26
 */
public class HttpUtils {
    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);


    private CloseableHttpClient httpClient;

    public HttpUtils() {
        httpClient = HttpClients.createDefault();
    }


    /**
     * post to API
     * 1. when data is instanceof byte[], charset is invalid and the contentType should application/x-protobuf;
     * 2. when data is instanceof String(jsonString), charset is valid or default UTF-8;
     *
     * @param url         post API URI
     * @param data        post data, type of byte[] or jsonString
     * @param contentType HTTP Header Content-Type
     * @param token       token
     * @param charset     string Charset
     * @return {@link byte[]} of response
     * @throws Exception httpclient exception
     */
    public byte[] post(String url, Object data, String token, String contentType, TreeMap<String, String> queryParamMap, String charset) throws Exception {
        String queryString = RequestUtils.parseQueryParamMapToQuereyString(queryParamMap, "sign", "timestamp");
        String timestamp = DateUtils.currentTimestampStr();
        if ("".equals(queryString)) {
            queryString += "timestamp=" + timestamp + "&appId=15b12db830bb402258d616e7e8c80830" + "&token=66B227778E75D76FDB02DEABB474551C";
        } else {
            queryString += "&timestamp=" + timestamp + "&appId=15b12db830bb402258d616e7e8c80830" + "&token=66B227778E75D76FDB02DEABB474551C";
        }
        String sign = RequestUtils.getSign(queryString,true);
        StringBuilder sb = new StringBuilder(url);
        sb.append("?").append(queryString).append("&sign=").append(sign);
        url = sb.toString();

        HttpPost httpPost = new HttpPost(url);
        if (data instanceof String) {
            if (contentType == null || "".equals(contentType)) {
                contentType = "application/json";
            }
            if (charset == null || "".equals(charset)) {
                charset = "UTF-8";
            }
            StringEntity stringEntity = new StringEntity((String) data, charset);
            httpPost.setHeader(HTTP.CONTENT_TYPE, contentType);
            httpPost.setEntity(stringEntity);
        } else if (data instanceof byte[]) {
            if (contentType == null || "".equals(contentType)) {
                contentType = "application/x-protobuf"; // protobuf transfer
            }
            httpPost.setEntity(new ByteArrayEntity((byte[]) data));
            httpPost.setHeader("Content-Type", contentType);
        } else {
            if (contentType == null || "".equals(contentType)) {
                contentType = "application/x-protobuf"; // protobuf transfer
            }
            httpPost.setHeader("Content-Type", contentType);
        }
        if (token != null && !"".equals(token)) {
            httpPost.setHeader("token", token);
        }

        CloseableHttpResponse httpResponse = null;
        HttpEntity entityResponse = null;
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(InitConstant.SOCKET_TIME_OUT).setConnectTimeout(InitConstant.CONNECT_TIMEOUT).build();
            httpPost.setConfig(requestConfig);

            httpResponse = httpClient.execute(httpPost);
            entityResponse = httpResponse.getEntity();
            log.info(String.format("url=%s, token=%s, response Header=%s", url, token, entityResponse.getContentType().toString()));
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK || httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
                return EntityUtils.toByteArray(entityResponse);
            } else {
                throw new Exception(String.format("Remote Server %s response code is not 200 or 202! It`s %s", url, httpResponse.getStatusLine().getStatusCode()));
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (entityResponse != null && !entityResponse.isStreaming()) {
                httpResponse.close();
            }
        }
    }

    /**
     * post with json
     *
     * @param url
     * @param dataMap
     * @return
     * @throws Exception
     */
    public String post(String url, Map<String, Object> dataMap) throws Exception {

        CloseableHttpResponse httpResponse = null;
        HttpEntity entityResponse = null;
        HttpPost httpPost = new HttpPost(url);
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(InitConstant.SOCKET_TIME_OUT).setConnectTimeout(InitConstant.CONNECT_TIMEOUT).build();
            httpPost.setConfig(requestConfig);

            httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
            JSONObject jsonObject = new JSONObject();
            for (String key : dataMap.keySet()) {
                jsonObject.put(key, dataMap.get(key));
            }
            StringEntity stringEntity = new StringEntity(jsonObject.toString(), "UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            httpResponse = httpClient.execute(httpPost);
            entityResponse = httpResponse.getEntity();
            log.info(String.format("url=%s", url));
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK|| httpResponse.getStatusLine().getStatusCode() ==HttpStatus.SC_ACCEPTED
                    || httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
                return EntityUtils.toString(entityResponse);
            } else {
                throw new Exception(String.format("Remote Server %s response code is not 200 or 202! It`s %s", url, httpResponse.getStatusLine().getStatusCode()));
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (entityResponse != null && !entityResponse.isStreaming()) {
                httpResponse.close();
            }
        }
    }
    
    
    /**
     * post with json
     *
     * @param url
     * @param dataMap
     * @return
     * @throws Exception
     */
    public String postWithToken(String url, Map<String, Object> dataMap) throws Exception {

        CloseableHttpResponse httpResponse = null;
        HttpEntity entityResponse = null;
        HttpPost httpPost = new HttpPost(url);
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(InitConstant.SOCKET_TIME_OUT).setConnectTimeout(InitConstant.CONNECT_TIMEOUT).build();
            httpPost.setConfig(requestConfig);

            httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
            JSONObject jsonObject = new JSONObject();
            for (String key : dataMap.keySet()) {
                jsonObject.put(key, dataMap.get(key));
            }
            StringEntity stringEntity = new StringEntity(jsonObject.toString(), "UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            Set key = dataMap.keySet();
            httpPost.setHeader("token",(String)dataMap.get("token"));
            httpResponse = httpClient.execute(httpPost);
            entityResponse = httpResponse.getEntity();
            log.info(String.format("url=%s", url));
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK|| httpResponse.getStatusLine().getStatusCode() ==HttpStatus.SC_ACCEPTED
                    || httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
                return EntityUtils.toString(entityResponse);
            } else {
                throw new Exception(String.format("Remote Server %s response code is not 200 or 202! It`s %s", url, httpResponse.getStatusLine().getStatusCode()));
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (entityResponse != null && !entityResponse.isStreaming()) {
                httpResponse.close();
            }
        }
    }
    

    public String post(String url, String jsonStr) throws Exception {

        CloseableHttpResponse httpResponse = null;
        HttpEntity entityResponse = null;
        HttpPost httpPost = new HttpPost(url);
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(InitConstant.SOCKET_TIME_OUT).setConnectTimeout(InitConstant.CONNECT_TIMEOUT).build();
            httpPost.setConfig(requestConfig);

            httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
            StringEntity stringEntity = new StringEntity(jsonStr, "UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            httpResponse = httpClient.execute(httpPost);
            entityResponse = httpResponse.getEntity();
            log.info(String.format("url=%s", url));
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK|| httpResponse.getStatusLine().getStatusCode() ==HttpStatus.SC_ACCEPTED
                    || httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
                return EntityUtils.toString(entityResponse);
            } else {
                throw new Exception(String.format("Remote Server %s response code is not 200 or 202! It`s %s", url, httpResponse.getStatusLine().getStatusCode()));
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (entityResponse != null && !entityResponse.isStreaming()) {
                httpResponse.close();
            }
        }
    }




    /**
     *
     * @param url          get API URI
     * @return             string of response
     * @throws Exception   httpclient exception
     */
    public String get(String url) throws Exception {

        String responseBody = "";
        CloseableHttpResponse httpResponse = null;
        HttpEntity entityResponse = null;
        //用get方法发送http请求
        HttpGet get = new HttpGet(url);
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(InitConstant.SOCKET_TIME_OUT).setConnectTimeout(InitConstant.CONNECT_TIMEOUT).build();
            get.setConfig(requestConfig);

            //发送get请求
            httpResponse = httpClient.execute(get);
            entityResponse = httpResponse.getEntity();
            log.info(String.format("url=%s", url));
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK|| httpResponse.getStatusLine().getStatusCode() ==HttpStatus.SC_ACCEPTED
                    || httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
                return EntityUtils.toString(entityResponse);
//                return EntityUtils.toByteArray(entityResponse);
            } else {
                throw new Exception(String.format("Remote Server %s response code is not 200 or 202! It`s %s", url, httpResponse.getStatusLine().getStatusCode()));
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (entityResponse != null && !entityResponse.isStreaming()) {
                httpResponse.close();
            }
        }
    }


    public String postXml(String url, String xmlStr, String token) throws Exception {
        byte[] reqBuffer = xmlStr.getBytes(Charset.forName("UTF-8"));
        byte[] respBuffer = post(url, reqBuffer, token,"application/xml; charset=UTF-8", null,null);
        return new String(respBuffer, Charset.forName("UTF-8"));
    }

    public static void main(String[] args) {
        HttpUtils httpclient = new HttpUtils();
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("owner", 123);
        jsonObject.addProperty("status", gson.toJson(1));
        jsonObject.addProperty("signatureStatus", 2321);
        jsonObject.addProperty("name", 321);
        jsonObject.addProperty("contractId", 312);
    }

}
