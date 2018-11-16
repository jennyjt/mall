package com.zsbatech.baasKettleManager.constants;

import java.lang.invoke.ConstantCallSite;

/**
 * 验证信息
 * @author dwe
 *
 */
public class RegxConstants {
	public static final String blank = "\\s|\\t|\\r|\\n| |　|(&nbsp;)";
	
	/***************************************** 项目名称**************************************/
	 // 项目标识最小长度
    public static final int PROJECT_NAME_LENGTH_MIN = 3;
    // 项目标识最大长度
    public static final int PROJECT_NAME_LENGTH_MAX = 40;

    public static final String PROJECT_NAME_MSG = "项目标识（由" + PROJECT_NAME_LENGTH_MIN + "到" + PROJECT_NAME_LENGTH_MAX + "个中文或者英文文组成）";
    public static final String PROJECT_NAME = "([a-zA-Z0-9]{" + PROJECT_NAME_LENGTH_MIN + "," + PROJECT_NAME_LENGTH_MAX + "})|([\\u4E00-\\u9FA5]{" + PROJECT_NAME_LENGTH_MIN + "," + PROJECT_NAME_LENGTH_MAX + "})";
	
	
    /***************************************** 集群节点数**************************************/
    public static final int POD_NUM_MIN = 1;
    // 项目标识最大长度
    public static final int POD_NUM_MAX = 5;

    public static final String POD_NUM_MSG = "集群节点数（范围" + POD_NUM_MIN + "到" + POD_NUM_MAX + "）";
    public static final String POD_NUM = "^([1-5]|5)$";
	 // 项目标识最小长度
    
	/*
	 * 
	^([1-9][0-9]{0,1}|100)$    1到100之间的整数
    // 单位最小中文长度
    public static final int NAME_LENGTH_MIN_CH = 2;
    // 单位最大中文长度
    public static final int NAME_LENGTH_MAX_CH = 8;

    // 单位最小英文长度
    public static final int NAME_LENGTH_MIN_EN = 3;
    // 单位最大英文长度
    public static final int NAME_LENGTH_MAX_EN = 10;

    public static final String REGEX_NAME_MSG = "单位名称（由" + NAME_LENGTH_MIN_CH + "到" + NAME_LENGTH_MAX_CH + "个中文或者" + NAME_LENGTH_MIN_EN + "到" + NAME_LENGTH_MAX_EN + "个英文组成，）";
    public static final String REGEX_NAME = "([a-zA-Z0-9]{" + NAME_LENGTH_MIN_EN + "," + NAME_LENGTH_MAX_EN + "})|([\\u4E00-\\u9FA5]{" + NAME_LENGTH_MIN_CH + "," + NAME_LENGTH_MAX_CH + "})";
	*/
	
	
    /***************************************** 用户名 **************************************/
    // 用户名最小长度
    public static final int USERNAME_LENGTH_MIN = 4;
    // 用户名最大长度
    public static final int USERNAME_LENGTH_MAX = 28;

    public static final String REGEX_USERNAME_MSG = "用户名（由字母、数字及下划线组成，必须以字母开头，" + USERNAME_LENGTH_MIN + " 到 " + USERNAME_LENGTH_MAX + "位）";
    public static final String REGEX_USERNAME = "[a-zA-Z]{1}[a-zA-Z0-9_]{" + (USERNAME_LENGTH_MIN - 1) + "," + (USERNAME_LENGTH_MAX - 1) + "}";


    /***************************************** 密码 **************************************/
    // 密码最小长度
    public static final int PASSWORD_LENGTH_MIN = 6;
    // 密码最大长度
    public static final int PASSWORD_LENGTH_MAX = 18;

    public static final String REGEX_PASSWORD_MSG = "密码（由字母、数字及下划线组成，" + PASSWORD_LENGTH_MIN + " 到 " + PASSWORD_LENGTH_MAX + "位）";
    public static final String REGEX_PASSWORD = "[a-zA-Z0-9_]{" + PASSWORD_LENGTH_MIN + "," + PASSWORD_LENGTH_MAX + "}";

    
    /***************************************** 联系电话 **************************************/
    //  手机号码
    public static final String REGEX_MOBILE_MSG = "联系电话（格式错误）";
    //    String REGEX_MOBILE = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\\d{8})?$";
    public static final String REGEX_MOBILE = "^1[3-8][0-9]\\d{8}$";

    // 座机号码
    public static final String REGEX_PHONE_MSG = "电话号码（格式错误）";
    public static final String REGEX_PHONE = "^(0[0-9]{2,3}\\-)?([1-9][0-9]{6,7})$";

    /***************************************** 邮箱 **************************************/
    public static final String REGEX_EMAIL_MSG = "邮箱（格式错误）";
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    //
    public static final int REGEX_KEY = 1;
    public static final String REGEX_KEY_MSG = "主键应为大于" + REGEX_KEY + "的整数";
}
