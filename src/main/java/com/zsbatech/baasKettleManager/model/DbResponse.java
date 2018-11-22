package com.zsbatech.baasKettleManager.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @author Caohuaijie@zsbatech.com
 * @date 2018/11/19 14:36
 */


public class DbResponse {

    // define jackson object
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // DbResponse code
    private Integer status;

    // reponse message
    private String msg;

    // DbResponse data
    private Object data;



    public static DbResponse build(Integer status, String msg, Object data) {
        return new DbResponse(status, msg, data);
    }

    public static DbResponse ok(Object data) {
        return new DbResponse(data);
    }

    public static DbResponse ok() {
        return new DbResponse(null);
    }

    public DbResponse() {

    }

    public static DbResponse build(Integer status, String msg) {
        return new DbResponse(status, msg, null);
    }

    public DbResponse(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public DbResponse(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     *
     * @param jsonData
     * @param clazz
     * @return
     */
    public static DbResponse formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, DbResponse.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param json
     * @return
     */
    public static DbResponse format(String json) {
        try {
            return MAPPER.readValue(json, DbResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param jsonData
     * @param clazz
     * @return
     */
    public static DbResponse formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}

