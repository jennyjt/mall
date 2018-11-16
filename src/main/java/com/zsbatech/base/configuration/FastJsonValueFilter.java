package com.zsbatech.base.configuration;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.math.BigDecimal;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/1 12:11
 */
public class FastJsonValueFilter implements ValueFilter {

    public static String getPrettyNumberString(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros().toPlainString();
    }

    public static BigDecimal getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros();
    }

    @Override
    public Object process(Object object, String name, Object value) {
//        System.out.println(String.format("name: %s, value: %s, type is: %s", name, value, value.getClass().getSimpleName()));
        if (value != null && value instanceof BigDecimal) {
            return getPrettyNumber(value.toString());
        }
        return value;
    }
}
