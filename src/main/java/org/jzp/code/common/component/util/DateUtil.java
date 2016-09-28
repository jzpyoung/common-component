package org.jzp.code.common.component.util;

import org.jzp.code.common.component.enums.CaculateDateEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-09-27
 */
public class DateUtil {

    /**
     * 字符串转化日期
     *
     * @param dateStr 待转化字符串
     * @param format  格式
     * @return 转化完日期对象
     */
    public static Date stringToDate(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return date;
        }
        return date;
    }

    /**
     * 日期格式转换字符串
     *
     * @param date   日期
     * @param format 格式
     * @return 转换后的字符串
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 在时间的基础上向前/后intervalMin分钟数,得到的时间值
     *
     * @param date             运算基础时间
     * @param intervalMin      运算值
     * @param caculateDateEnum 向前/向后
     * @return 运算结果
     */
    public static Date calculateDate_Min(Date date, int intervalMin, CaculateDateEnum caculateDateEnum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, intervalMin * caculateDateEnum.getValue());
        return cal.getTime();
    }

    /**
     * 在时间的基础上向前/后intervalHour小时数,得到的时间值
     *
     * @param date             运算基础时间
     * @param intervalHour     运算值
     * @param caculateDateEnum 向前/向后
     * @return 运算结果
     */
    public static Date calculateDate_Hour(Date date, int intervalHour, CaculateDateEnum caculateDateEnum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, intervalHour * caculateDateEnum.getValue());
        return cal.getTime();
    }

    /**
     * 在时间的基础上向前/后intervalDay天数,得到的时间值
     *
     * @param date             运算基础时间
     * @param intervalDay      运算值
     * @param caculateDateEnum 向前/向后
     * @return 运算结果
     */
    public static Date calculateDate_Day(Date date, int intervalDay, CaculateDateEnum caculateDateEnum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, intervalDay * caculateDateEnum.getValue());
        return cal.getTime();
    }

    /**
     * 判断两个日期是否是同一天
     *
     * @param day1
     * @param day2
     * @return $运算结果
     */
    public static boolean isSameDay(Date day1, Date day2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ds1 = sdf.format(day1);
        String ds2 = sdf.format(day2);
        if (ds1.equals(ds2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算两个date类型之间的差值(小时)
     *
     * @param day1
     * @param day2
     * @return $运算结果
     */
    public static Long calDateIntervalHour(Date day1, Date day2) {
        Long intervalLong = calDateIntervalLong(day1, day2);
        return intervalLong / (1000 * 60 * 60);
    }

    /**
     * 计算两个date类型之间的差值(毫秒)
     *
     * @param day1
     * @param day2
     * @return $运算结果
     */
    public static Long calDateIntervalLong(Date day1, Date day2) {
        return day2.getTime() - day1.getTime();
    }

    /**
     * 判断两个日期大小
     *
     * @param d1
     * @param d2
     * @return $运算结果
     */
    public static int compareDate(Date d1, Date d2) {
        if (d1.getTime() > d2.getTime()) {
            return 1;
        } else if (d1.getTime() < d2.getTime()) {
            return -1;
        } else {
            return 0;
        }
    }
}
