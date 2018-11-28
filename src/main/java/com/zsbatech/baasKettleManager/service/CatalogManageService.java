package com.zsbatech.baasKettleManager.service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/20
 */
public interface CatalogManageService {

    Map<String, List<String>> createCatalogs(List<String> files);

    Map<String, List<String>> deleteFiles(List<String> files);

    Map<String, List<String>> deleteCatalogs(List<String> fileCatalogs);

    List<String> queryFiles(List<String> fileNames);

    int saveFiles(List<String> files);

}
