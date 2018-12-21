package com.zsbatech.baasKettleManager.util;


import com.zsbatech.baasKettleManager.model.FileCatalogDO;
import com.zsbatech.baasKettleManager.vo.CatalogFileCountVO;
import com.zsbatech.baasKettleManager.vo.FileCatalogVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Singleton {

    private static List<CatalogFileCountVO> stringList;

    private static  List<FileCatalogVO> fileCatalogVOList;

    private static Map<Integer, List<FileCatalogVO>> stringMap;

    private Singleton(){

    }

    public static List<CatalogFileCountVO> getListInstance() {
        if (stringList == null || stringList.size() == 0){
            stringList = new ArrayList<>();
        }
        return stringList;
    }

    public static List<FileCatalogVO> getFileCatalogVOListInstance(){
        if (fileCatalogVOList == null || fileCatalogVOList.size() == 0){
            fileCatalogVOList = new ArrayList<>();
        }
        return fileCatalogVOList;
    }

    public static Map<Integer,List<FileCatalogVO>> getStringMapInstance(){
        if (stringMap == null || stringMap.size() == 0){
            stringMap = new HashMap<>();
        }
        return stringMap;
    }
}
