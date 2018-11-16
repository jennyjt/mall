package com.zsbatech.base.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/7 09:00:00
 */
public class DateUtils {
    public static Date timestampToDateTime(String timestamp) {
        Long timestampLong = Long.valueOf(timestamp);
        DateTime dateTime = new DateTime(timestampLong);
        return dateTime.toDate();
    }

    public static DateTime getDateTimeAdd(Date date, Integer years, Integer months, Integer days) throws Exception {
        if (date == null) {
            throw new Exception("date should not null");
        }
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.plusYears(years).plusMonths(months).plusDays(days);
    }

    public static Date getDateAdd(Date date, Integer years, Integer months, Integer days) throws Exception {
        if (date == null) {
            throw new Exception("date should not null");
        }
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.plusYears(years).plusMonths(months).plusDays(days).toDate();
    }

    public static String getDateTimeStampStrAdd(Date date, Integer years, Integer months, Integer days) throws Exception {
        DateTime dateTime = getDateTimeAdd(date, years, months, days);
        return dateTime.getMillis() + "";
    }

    public static String getDateTimeStrAdd(Date date, Integer years, Integer months, Integer days) throws Exception {
        DateTime dateTime = getDateTimeAdd(date, years, months, days);
        return dateTime.toString("yyyy-MM-dd HH:mm:ss");
    }

    public static Date currentDateTime() {
        return new DateTime().toDate();
    }

    public static String convertDateTimeStrToTimestampStr(String inputDateTimeStr, String inputDateFormat) {
        return convertDateTimeStrToTimestamp(inputDateTimeStr, inputDateFormat) + "";
    }

    public static String convertDateTimeStrToTimestampStr(String inputDateTimeStr) {
        return convertDateTimeStrToTimestamp(inputDateTimeStr) + "";
    }

    public static long convertDateTimeStrToTimestamp(String inputDateTimeStr, String inputDateFormat) {
        if (inputDateFormat == null || "".equals(inputDateFormat)) {
            return convertDateTimeStrToTimestamp(inputDateTimeStr);
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(inputDateFormat);
        DateTime dateTime = dateTimeFormatter.parseDateTime(inputDateTimeStr);
        return dateTime.getMillis();
    }

    public static long convertDateTimeStrToTimestamp(String inputDateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dateTime = dateTimeFormatter.parseDateTime(inputDateTimeStr);
        return dateTime.getMillis();
    }

    public static String timestampToDateTimeStr(String timestamp, String format) {
        Long timestampLong = Long.valueOf(timestamp);
        DateTime dateTime = new DateTime(timestampLong);
        if (format == null || "".equals(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        return dateTime.toString(format);
    }

    public static String currentDateTimeStr(String format) {
        DateTime dateTime = new DateTime();
        if (format == null || "".equals(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        return dateTime.toString(format);
    }

    public static Long currentTimestamp() {
        return DateTime.now().getMillis();
    }

    public static String currentTimestampStr() {
        return DateTime.now().getMillis() + "";
    }

    public static String datetimeToTimestampStr(String datetime, String format) {
        if (format == null || "".equals(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = DateTime.parse(datetime, dateTimeFormatter);
        return dateTime.getMillis() + "";
    }

    public static String getTimestampStr(Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.getMillis() + "";
    }

    public static String getDateTimeStr(Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.toString(dateTimeFormatter);
    }

    public static boolean checkDateFormat(String dateTimeStr) {
        return checkDateFormat(dateTimeStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 校验日志字符串是否为指定格式
     *
     * @param dateTimeStr
     */
    public static boolean checkDateFormat(String dateTimeStr, String format) {
        if (format == null || "".equals(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        boolean bolRet = false;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date realDate = sdf.parse(dateTimeStr);
            if (dateTimeStr.equals(sdf.format(realDate))) {
                bolRet = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            bolRet = false;
        }
        return bolRet;
    }

    public static void main(String[] args) throws Exception {

        Date date = DateUtils.timestampToDateTime("1493952525730");
        String dateTimeStr = DateUtils.timestampToDateTimeStr("1493952525730", null);
        System.out.println(date);
        System.out.println(dateTimeStr);
        System.out.println(new Date());
        System.out.println(currentTimestamp());
        System.out.println(datetimeToTimestampStr("2017-06-12 13:10:12", "yyyy-MM-dd HH:mm:ss"));

        System.out.println(convertDateTimeStrToTimestampStr("2017-09-19 15:50:02"));
        System.out.println(convertDateTimeStrToTimestampStr("20170919155002", "yyyyMMddHHmmss"));

        System.out.println(DateUtils.getDateTimeStrAdd(new Date(), 0, -3, 0));

    }
}
