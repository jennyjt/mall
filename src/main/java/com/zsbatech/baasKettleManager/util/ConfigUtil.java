package com.zsbatech.baasKettleManager.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import java.util.Properties;

/**
 * @description 获取配置信息
 */
public class ConfigUtil {

	private static Properties properties;
	private static Properties message;
	
	static{
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void init() throws IOException{
		InputStream inputStream = ConfigUtil.class.getResourceAsStream("/config.properties");
		if(inputStream != null){
			properties = new Properties();
			properties.load(new InputStreamReader(inputStream, "utf-8"));
		}
		
		InputStream inputStream2 = ConfigUtil.class.getResourceAsStream("/message-mapping.properties");
		if(inputStream2 != null){
			message = new Properties();
			message.load(new InputStreamReader(inputStream2, "utf-8"));
		}
		
	}
	
	
	/**
	 * 获取配置文件的值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getPropertyValue(String key,String defaultValue){
		String value = getPropertyValue(key);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * 获取配置文件的值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getPropertyValue(String key){
		Object valueObj = properties.get(key);
		if (valueObj == null) {
			return null;
		}
		
		String value = String.valueOf(valueObj);
		
		if (value.trim().equalsIgnoreCase("")) {
			return null;
		}
		
		Pattern p = Pattern.compile("\\$\\{(.*?)\\}");
		Matcher m = p.matcher(valueObj.toString());
		if(!m.find()){
			return value;
		}
		String inner = m.group();
		String innerKey = m.group(1);
		String innerValue = getPropertyValue(innerKey);
		value = value.replace(inner, innerValue);
		return value;
	}
	
	public static String getMessage(Object key, Object...objects){
		if(message == null){
			return null;
		}
		
		Object valueObj = message.get(String.valueOf(key));
		if (valueObj == null) {
			return null;
		}
		
		String value = String.valueOf(valueObj);
		
		if (value.trim().equalsIgnoreCase("")) {
			return null;
		}
		
		for(int i=0; i<objects.length; i++){
			Object obj = objects[i];
			value = value.replaceAll("\\{"+i+"\\}", String.valueOf(obj));
		}
		
		return value;
	}
}
