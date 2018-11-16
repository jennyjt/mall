package com.zsbatech.baasKettleManager.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TemplateUtils
{
    public static final String DEF_REGEX = "\\{\\((.+?)\\)\\}";

    public static String getTemplateWithParas(String templateDesc, Map paraMap, Object paraObject)
    {
        if (StringUtils.isBlank(templateDesc)) {
            return null;
        }
        Map tempMap = new HashMap();
        if ((paraMap != null) && (!paraMap.isEmpty())) {
            tempMap.putAll(paraMap);
        }
        if (paraObject != null)
        {
            Map ObjectMap = objectToMap(paraObject);
            if ((ObjectMap != null) && (!ObjectMap.isEmpty())) {
                tempMap.putAll(ObjectMap);
            }
        }
        return render(templateDesc, tempMap);
    }

    public static String render(String template, Map<String, String> data)
    {
        return render(template, data, DEF_REGEX);
    }

    public static String render(String template, Map<String, String> data, String regex)
    {
        if (StringUtils.isBlank(template)) {
            return "";
        }
        if (StringUtils.isBlank(regex)) {
            return template;
        }
        if ((data == null) || (data.size() == 0)) {
            return template;
        }
        try
        {
            StringBuffer sb = new StringBuffer();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(template);
            while (matcher.find())
            {
                String name = matcher.group(1);
                String value = (String)data.get(name);
                if (value == null) {
                    value = "";
                }
                matcher.appendReplacement(sb, value);
            }
            matcher.appendTail(sb);
            return sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return template;
    }

    public static Map objectToMap(Object obj)
    {
        if (obj == null) {
            return new HashMap();
        }
        return new BeanMap(obj);
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass)
            throws Exception
    {
        if (map == null) {
            return null;
        }
        Object obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors)
        {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, new Object[] { map.get(property.getName()) });
            }
        }
        return obj;
    }

    public static void main(String[] args) throws Exception
    {
        String template = "您提现{(borrowAmount)}元至尾号{(tailNo)}的请求失败，您可以重新提交提款申请。";
        Map<String, String> data = new HashMap();
        data.put("borrowAmount", "1000.00");
        System.out.println(getTemplateWithParas(template, data,null));
    }
}
