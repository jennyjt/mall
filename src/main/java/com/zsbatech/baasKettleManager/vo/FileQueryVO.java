package com.zsbatech.baasKettleManager.vo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/3
 */
public class FileQueryVO {

    private List<String> fileName;

    private String code;

    public List<String> getFileName() {
        return fileName;
    }

    public String getCode() {
        return code;
    }

    public void setFileName(List<String> fileName) {
        this.fileName = fileName;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
