package com.zsbatech.baasKettleManager.service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/20
 */
public interface ContentManageService {

    Map<String, List<String>> createContent(List<String> files);

    Map<String, List<String>> deleteFiles(List<String> files);

    Map<String, List<String>> deleteContents(List<String> fileContents);

    List<String> queryFiles(List<String> fileNames);

}
