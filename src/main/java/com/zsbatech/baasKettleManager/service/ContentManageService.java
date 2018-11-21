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

    Map<String, List<String>> createContent(String[] files);

    Map<String, List<String>> deleteFiles(String[] files);

    Map<String, List<String>> deleteContents(String[] fileContents);

}
