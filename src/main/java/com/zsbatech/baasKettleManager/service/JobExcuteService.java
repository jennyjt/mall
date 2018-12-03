package com.zsbatech.baasKettleManager.service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/27
 */
public interface JobExcuteService {
    void excute(String fileName);

    void stop(String transMeta,String carteObjectId);

    void stopAll();

}
