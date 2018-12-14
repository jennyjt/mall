package com.zsbatech.baasKettleManager.util;


import java.util.ArrayList;
import java.util.List;

public class ListSingleton {

    private static List<String> stringList;

    private ListSingleton(){

    }

    public static List<String> getListInstance() {
        if (stringList == null || stringList.size() == 0){
            stringList = new ArrayList<>();
        }
        return stringList;
    }
}
