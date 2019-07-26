package com.netposa.ips.ga1400.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ZhiFan
 * 2018-09-13 21:10
 */
@Slf4j
public class DateUtil {

    /**
     * @Fields calendar : 日历实例
     */
    private static Calendar CALENDAR = new GregorianCalendar();
    /**
     * ONE_MINUTE
     */
    private static final long ONE_MINUTE = 60;

    /**
     * ONE_HOUR
     */
    private static final long ONE_HOUR = 3600;

    /**
     * ONE_DAY
     */
    private static final long ONE_DAY = 86400;

    /**
     * @Fields FORMAT_0 : yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMAT_0 = "yyyy-MM-dd HH:mm:ss";

    /**
     * @Fields FORMAT_1 : yyyy-MM-dd
     */
    public static final String FORMAT_1 = "yyyy-MM-dd";

    /**
     * @Fields FORMAT_2 : HH:mm:ss
     */
    public static final String FORMAT_2 = "HH:mm:ss";

    /**
     * @Fields FORMAT_3 : yyyyMMddHHmmss
     */
    public static final String FORMAT_3 = "yyyyMMddHHmmss";

    /**
     * @Fields FORMAT_4 : yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String FORMAT_4 = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String FORMAT_YYYYMMDDHH = "yyyyMMddHH";

    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * 获取年月日零时零分零秒
     *
     * @param date
     * @return
     */
    public static Date GetDate00_00_00(Date date) {
        if (null == date)
            return GetCurrentDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        Calendar calendar = Calendar.getInstance();
        calendar.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        return calendar.getTime();
    }

    /**
     * 获取年月日23时59分59秒
     *
     * @param date
     * @return
     */
    public static Date GetDate23_59_59(Date date) {
        if (null == date)
            return GetCurrentDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        Calendar calendar = Calendar.getInstance();
        calendar.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        return calendar.getTime();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date GetCurrentDate() {
        Calendar clCalendar = Calendar.getInstance();
        Date dtDate = clCalendar.getTime();
        return dtDate;
    }

    /**
     * @param date         Date
     * @param parsePattern String
     * @return String
     * @Title: fromToday
     * @Description: 距离今天多久
     */
    public static String fromToday(Date date, String parsePattern) {
        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
        ago = ago < 0 ? 0 : ago;
        if (ago <= ONE_HOUR) {
            return ago / ONE_MINUTE + "分钟前";
        } else if (ago <= ONE_DAY) {
            return ago / ONE_HOUR + "小时前";
        } else {
            return DateFormatUtils.format(date, parsePattern);
        }
    }

    /**
     * @param date
     * @return String
     * @Title: fromDate
     * @Description: 距离多久
     */
    public static long fromNow(Date date) {
        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
        ago = ago < 0 ? 0 : ago;
        return ago / ONE_HOUR;
    }

    /**
     * @param date  Date
     * @param count int
     * @return Date
     * @Title: getWeeksAgoOfDate
     * @Description: 获取当前日期所在周的前几周，返回前几周的周日，从周一到周日为一周
     */
    public static Date getWeeksAgoOfDate(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        if (index == 1) {
            index = 7;
        } else {
            index = index - 1;
        }
        index = index + (count - 1) * 7;
        return DateUtils.addDays(calendar.getTime(), -index);
    }

    /**
     * @param date    日期
     * @param pattern 格式
     * @return 字符串
     * @Title: date2String
     * @Description: 日期转字符串
     */
    public static String date2String(Date date, String pattern) {
        String str = null;
        DateFormat format = new SimpleDateFormat(pattern);
        str = format.format(date);
        return str;
    }

    /**
     * @param date 日期
     * @return 字符串
     * @Title: date2StringFormat3
     * @Description: 日期转字符串根据格式3:yyyyMMddHHmmss
     */
    public static String date2StringFormat3(Date date) {
        String str = null;
        DateFormat format = new SimpleDateFormat(FORMAT_3);
        str = format.format(date);
        return str;
    }

    /**
     * @param dateStr       日期字符串
     * @param originPattern 原格式
     * @param targetPattern 目标格式
     * @return 目标格式字符串
     * @Title: convertDateStrToOtherFormat
     * @Description: 将日期从一种格式转化为另一种格式
     */
    public static String convertDateStrToOtherFormat(String dateStr, String originPattern, String targetPattern) {
        Date temp = null;
        temp = string2Date(dateStr, originPattern);
        return date2String(temp, targetPattern);
    }

    /**
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return 转换后的时间(若转换异常则返回当前时间对象)
     */
    public static Date string2Date(String dateStr, String pattern) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(pattern);
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            log.error("DateHelper.string2Date Error: {}" + e);
        }
        return date;
    }

    /**
     * 获取前一(小时/天/月)的实际字符表达
     *
     * @return
     */
    public static String getPastTimeStr(int type, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(type, calendar.get(type) - 1);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    /**
     * 获取当前时间的实际字符表达
     *
     * @return
     */
    public static String getCurrentTimeStr(int type, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(type, calendar.get(type));
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    /**
     * @param date 日期
     * @return 年份
     * @Title: getYear
     * @Description: 获取年份
     */
    public static int getYear(Date date) {
        CALENDAR.setTime(date);
        return CALENDAR.get(Calendar.YEAR);
    }

    /**
     * @param date 日期
     * @return 月份
     * @Title: getMonth
     * @Description: 获取月份
     */
    public static int getMonth(Date date) {
        CALENDAR.setTime(date);
        return CALENDAR.get(Calendar.MONTH);
    }

    /**
     * @param date 日期
     * @return 天
     * @Title: getDay
     * @Description: 获取天
     */
    public static int getDay(Date date) {
        CALENDAR.setTime(date);
        return CALENDAR.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @param date 日期
     * @return 小时
     * @Title: getHour
     * @Description: 获取小时
     */
    public static int getHour(Date date) {
        CALENDAR.setTime(date);
        return CALENDAR.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * @param date 日期
     * @return 分钟
     * @Title: getMinute
     * @Description: 获取分钟
     */
    public static int getMinute(Date date) {
        CALENDAR.setTime(date);
        return CALENDAR.get(Calendar.MINUTE);
    }

    /**
     * @param date 日期
     * @return 秒
     * @Title: getSecond
     * @Description: 获取秒
     */
    public static int getSecond(Date date) {
        CALENDAR.setTime(date);
        return CALENDAR.get(Calendar.SECOND);
    }

    /**
     * 根据字符串格式去转换相应格式的日期和时间
     *
     * @param date 日期字符串
     * @return java.util.Date 日期
     **/
    public static Date reverse2Date(String date) {
        SimpleDateFormat simple = null;
        switch (date.trim().length()) {
            case 19:// 日期+时间
                simple = new SimpleDateFormat(FORMAT_0);
                break;
            case 10:// 仅日期
                simple = new SimpleDateFormat(FORMAT_1);
                break;
            case 8:// 仅时间
                simple = new SimpleDateFormat(FORMAT_2);
                break;
            case 21:// 日期+时间+微秒
                simple = new SimpleDateFormat(FORMAT_4);
                break;
            case 22:// 日期+时间+微秒
                simple = new SimpleDateFormat(FORMAT_4);
                break;
            case 23:// 日期+时间+微秒
                simple = new SimpleDateFormat(FORMAT_4);
                break;
            default:
                break;
        }
        try {
            return simple.parse(date.trim());
        } catch (ParseException e) {
            log.error("---->{}", e);
        }
        return null;

    }

    /**
     * 根据字符串格式去转换相应格式的日期和时间
     *
     * @param date 日期字符串
     * @return Date 日期
     **/
    public static java.sql.Date reverse2SqlDate(String date) {

        SimpleDateFormat simple = null;

        switch (date.trim().length()) {
            case 19:// 日期+时间
                simple = new SimpleDateFormat(FORMAT_0);
                break;
            case 10:// 仅日期
                simple = new SimpleDateFormat(FORMAT_1);
                break;
            case 8:// 仅时间
                simple = new SimpleDateFormat(FORMAT_2);
                break;
            default:
                break;
        }
        try {
            java.sql.Date sqldate = new java.sql.Date(simple.parse(date.trim()).getTime());
            return sqldate;
        } catch (ParseException e) {
            log.error("---->{}", e);
        }
        return null;

    }

    /**
     * 获取指定日期的后若干天的时间点
     */
    public static Date getSpecifiedDayAfter(Date specifiedDay, Integer addDayNum) {
        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + addDayNum);
        return c.getTime();
    }

    /**
     * 获取指定日期的前若干天的时间点
     */
    public static Date getSpecifiedDayBefore(Date specifiedDay, Integer addDayNum) {
        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - addDayNum);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        return c.getTime();
    }

    /**
     * 获取上个月的第一天
     */
    public static Date getLastMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(GetCurrentDate());
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        return c.getTime();
    }

    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }

    public static boolean isFirstHourOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY) == 1;
    }

    public static Date setTime(long timestamp) {
        Date date = GetCurrentDate();
        date.setTime(timestamp);
        return date;
    }

    public static Date getCurrentMonthFirstDay() {
        String d = DateFormatUtils.format(new Date(), FORMAT_YYYYMMDD);
        SimpleDateFormat simple = new SimpleDateFormat(FORMAT_YYYYMMDD);
        Date date = null;
        try {
            date = simple.parse(d);
        } catch (ParseException e) {
            log.error("---->{}", e);
        }
        return date;
    }

}