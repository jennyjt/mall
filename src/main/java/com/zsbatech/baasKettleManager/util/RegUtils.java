package com.zsbatech.baasKettleManager.util;


import com.zsbatech.base.utils.CommonUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wxcsdb88 on 2017/9/6 14:52.
 */
public class RegUtils {

    public static boolean verify(String input, String patternStr) {
        if (CommonUtils.isEmpty(patternStr)) {
            return false;
        }
        Pattern pattern = Pattern.compile(patternStr);
        Matcher m = pattern.matcher(input.trim());
        return m.matches();
    }
}
