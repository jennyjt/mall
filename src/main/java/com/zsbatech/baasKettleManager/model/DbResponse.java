package com.zsbatech.baasKettleManager.model;

public class DbResponse {

    private Long linesSuccessed;
    private String  ktrPath;


    public String getKtrPath() {
        return ktrPath;
    }

    public void setKtrPath(String ktrPath) {
        this.ktrPath = ktrPath;
    }

    public Long getLinesSuccessed() {
        return linesSuccessed;
    }

    public void setLinesSuccessed(Long linesSuccessed) {
        this.linesSuccessed = linesSuccessed;
    }
}
