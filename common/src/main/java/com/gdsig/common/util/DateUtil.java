package com.gdsig.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 *
 * @author Administrator
 * @date 2021-08-25
 */
public class DateUtil {

    /**
     * 返回当前日期时间java.sql.Timestamp
     *
     * @return Timestamp
     */
    public static Timestamp getTimestamp() {
        String s = String.format("%tY-%<tm-%<td %<tH:%<tM:%<tS", Calendar.getInstance());
        return Timestamp.valueOf(s);
    }

    public static Date tomorrow() {
        return addDay(new Date(), 1);
    }

    /**
     * 格式化日期：yyyy-MM-dd
     *
     * @param date Date
     * @return String
     */
    public static String formatDateYyyyMm(Date date) {
        return date == null ? "" : (new SimpleDateFormat("yyyy-MM")).format(date);
    }

    /**
     * 格式化日期：yyyy-MM-dd
     *
     * @param date Date
     * @return String
     */
    public static String formatDate(Date date) {
        return date == null ? "" : (new SimpleDateFormat("yyyy-MM-dd")).format(date);
    }

    /**
     * 格式化日期：yyyy-MM-dd
     *
     * @param date Date
     * @return String
     */
    public static String formatDateYyyyMMdd(Date date) {
        return date == null ? "" : (new SimpleDateFormat("yyyyMMdd")).format(date);
    }

    /**
     * 格式化日期：yyyy.M.dd
     *
     * @param date Date
     * @return String
     */
    public static String formatYyyyMdd4Point(Date date) {
        return date == null ? "" : (new SimpleDateFormat("yyyy.M.dd")).format(date);
    }

    /**
     * 格式化日期：MM.dd
     *
     * @param date Date
     * @return String
     */
    public static String formatMmDd4Point(Date date) {
        return date == null ? "" : (new SimpleDateFormat("MM.dd")).format(date);
    }

    /**
     * 格式化日期：M.dd
     *
     * @param date Date
     * @return String
     */
    public static String formatMDd4Point(Date date) {
        return date == null ? "" : (new SimpleDateFormat("M.dd")).format(date);
    }

    /**
     * 格式化日期：M.dd
     *
     * @param date Date
     * @return String
     */
    public static String formatMmDdHhMmSs4Point(Date date) {
        return date == null ? "" : (new SimpleDateFormat("MM.dd HH:mm:ss")).format(date);
    }

    /**
     * 格式化日期：yyMMdd
     *
     * @param date Date
     * @return String
     */
    public static String formatYyMmDd(Date date) {
        return date == null ? "" : (new SimpleDateFormat("yyMMdd")).format(date);
    }

    /**
     * 格式化日期：MM月dd日
     *
     * @param date Date
     * @return String
     */
    public static String formatMmDd4Cn(Date date) {
        return date == null ? "" : (new SimpleDateFormat("MM月dd日")).format(date);
    }

    /**
     * 格式化日期：MM月dd日 HH:mm
     *
     * @param date Date
     * @return String
     */
    public static String formatYyyyMDHHMm4Cn(Date date) {
        return date == null ? "" : (new SimpleDateFormat("yyyy年M月d日 HH:mm")).format(date);
    }

    /**
     * 格式化日期：yyyy-MM-dd HH:mm:ss
     *
     * @param date Date
     * @return String
     */
    public static String formatDateTime(Date date) {
        return date == null ? "" : (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
    }

    /**
     * 格式化日期：yyyy-MM-dd HH:mm:ss:SSS
     *
     * @param date Date
     * @return String
     */
    public static String formatDateTimes(Date date) {
        return date == null ? "" : (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")).format(date);
    }

    /**
     * 格式化日期：MM-dd HH:mm:ss:SSS
     *
     * @param date Date
     * @return String
     */
    public static String formatYearMonth(Date date) {
        return date == null ? "" : (new SimpleDateFormat("yyyy-MM")).format(date);
    }

    /**
     * 格式化日期：MM-dd HH:mm:ss:SSS
     *
     * @param date Date
     * @return String
     */
    public static String formatMonthTime(Date date) {
        return date == null ? "" : (new SimpleDateFormat("MM-dd HH:mm:ss")).format(date);
    }

    /**
     * 格式化日期：MM-dd HH:mm:ss:SSS
     *
     * @param date Date
     * @return String
     */
    public static String formatMonthTimes(Date date) {
        return date == null ? "" : (new SimpleDateFormat("MM-dd HH:mm:ss:SSS")).format(date);
    }

    /**
     * 返回java.sql.Timestamp
     *
     * @return Timestamp
     */
    public static Timestamp toTimestamp(Object value) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(toUtilDate(value));
            return Timestamp.valueOf(String.format("%tY-%<tm-%<td %<tH:%<tM:%<tS", cal));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 返回java.sql.Timestamp
     *
     * @return Timestamp
     */
    public static Timestamp toTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return Timestamp.valueOf(String.format("%tY-%<tm-%<td %<tH:%<tM:%<tS", cal));
    }

    /**
     * 对象转{@code java.util.Date}
     *
     * @param value yyyy-MM-dd | yyyy-MM-dd HH:mm:ss | yyyy-MM-dd HH:mm:ss:SSS
     * @return {@link Date}
     */
    public static Date toUtilDate(Object value) {
        if (value == null) {
            return null;
        }
        String dateStr = value.toString().trim();
        try {
            DateTimeFormatter formatter;
            if (StringUtils.contains(dateStr, "-") && StringUtils.contains(dateStr, ":")) {
                if (dateStr.length() >= 23) {
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
                } else if (dateStr.length() >= 21) {
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                } else {
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                }
                LocalDateTime localDateTime = LocalDateTime.parse(dateStr, formatter);
                return Date.from(localDateTime.toInstant(ZoneOffset.ofHours(8)));
            } else if (StringUtils.contains(dateStr, ".")) {

                formatter = DateTimeFormatter.ofPattern("yyyy.M.dd");
                LocalDate localDate = LocalDate.parse(dateStr, formatter);
                return Date.from(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
            } else {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateStr, formatter);
                return Date.from(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 对象转{@code java.util.Date}
     *
     * @param value yyyy-MM-dd | yyyy-MM-dd HH:mm:ss | yyyy-MM-dd HH:mm:ss:SSS
     * @return {@link Date}
     */
    public static Date yyyyMMdd2Date(Object value) {
        if (value == null) {
            return null;
        }
        try {
            String dateStr = value.toString().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(dateStr, formatter);
            return Date.from(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 对象转{@code java.util.Date}
     *
     * @param value yyyyMMddHHmmss
     * @return {@link Date}
     */
    public static Date yyyyMMddHHmmssToDate(Object value) {
        if (value == null) {
            return null;
        }
        String dateStr = value.toString().trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime localDate = LocalDateTime.parse(dateStr, formatter);
            return Date.from(localDate.toInstant(ZoneOffset.ofHours(8)));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 对象转{@code java.util.Date}
     *
     * @param value yyyy-MM
     * @return {@link Date}
     */
    public static Date toUtilDateYyyyMm(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM").parse(value.toString());
        } catch (Exception ex) {
            return null;
        }
    }


    /**
     * 对象转{@code java.util.Date}
     *
     * @param value yyyy-MM-dd HH:mm:ss
     * @return {@link Date}
     */
    public static Date toUtilDateTime(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value.toString());
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 计算 Day 差，不到1天按1天算
     *
     * @param end   结束日期
     * @param begin 开始日期
     * @return long
     */
    public static int getDayLeft(Date end, Date begin) {
        long r = end.getTime() - begin.getTime();
        BigDecimal bd = new BigDecimal(r).divide(new BigDecimal(1000 * 60 * 60 * 24), BigDecimal.ROUND_CEILING);
        return bd.intValue();
    }

    /**
     * 计算 Day 差，不到1天按1天算
     *
     * @param end   结束日期
     * @param begin 开始日期
     * @return long
     */
    public static int getDayLeft(long end, long begin) {
        long r = end - begin;
        BigDecimal bd = new BigDecimal(r).divide(new BigDecimal(1000 * 60 * 60 * 24), BigDecimal.ROUND_CEILING);
        return bd.intValue();
    }

    /**
     * 计算 Week 差
     *
     * @param end   结束日期
     * @param begin 开始日期
     * @return long
     */
    public static long getWeekLeft(Date end, Date begin) {
        long r = end.getTime() - begin.getTime();
        long days = r / 1000 / 60 / 60 / 24;
        return days / 7 + (days % 7 > 0 ? 1 : 0);
    }

    /**
     * 计算 Month 差
     *
     * @param end   结束日期
     * @param begin 开始日期
     * @return long
     */
    public static long getMonthLeft(Date end, Date begin) {
        long r = end.getTime() - begin.getTime();
        long days = r / 1000 / 60 / 60 / 24;
        return days / 30 + (days % 30 > 0 ? 1 : 0);
    }

    /**
     * 计算 Year 差
     *
     * @param end   结束日期
     * @param begin 开始日期
     * @return long
     */
    public static long getYearLeft(Date end, Date begin) {
        long r = end.getTime() - begin.getTime();
        long days = r / 1000 / 60 / 60 / 24;
        return days / 356 + (days % 365 > 0 ? 1 : 0);
    }

    /**
     * 对象转{@code java.util.Date}
     *
     * @param value yyyy-MM-01
     * @return {@link Date}
     */
    public static Date toUtilFirstDate(Object value) {
        if (value == null) {
            return null;
        }
        try {
            if (value instanceof Date) {
                return new SimpleDateFormat("yyyy-MM-01").parse(new SimpleDateFormat("yyyy-MM-01").format((Date) value));
            }
            return new SimpleDateFormat("yyyy-MM-01").parse(value.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 如果为空，返回默认值
     *
     * @param date1 Date1
     * @param date2 Date2
     * @return Date
     */
    public static Date defaultIfEmpty(Date date1, Date date2) {
        return date1 == null ? date2 : date1;
    }

    /**
     * 日期加天数
     *
     * @param date 日期
     * @param days 天数
     * @return {@link Timestamp}
     */
    public static Timestamp addDay(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 日期加年数
     *
     * @param date 日期
     * @param year 年份增量：正|负
     * @return {@link Timestamp}
     */
    public static Timestamp addYear(Date date, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, year);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 日期加天数
     *
     * @param date 日期
     * @param days 天数
     * @return {@link Timestamp}
     */
    public static Timestamp addDayOrNull(Date date, int days) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 时间转为当天的23:59:59
     *
     * @param date 日期
     * @return {@link Timestamp}
     */
    public static Timestamp to235959(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, 1);
        cal.add(Calendar.SECOND, -1);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 时间转为当天的23:59:59
     *
     * @param date 日期
     * @return {@link Timestamp}
     */
    public static Timestamp to235959999(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, 1);
        cal.add(Calendar.MILLISECOND, -1);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 时间转为当天的00:00:00
     *
     * @param date 日期
     * @return {@link Timestamp}
     */
    public static Timestamp to000000(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return Timestamp.valueOf(String.format("%tY-%<tm-%<td %<tH:%<tM:%<tS", cal));
    }

    /**
     * 输出yyyy-MM-dd 获取当前日期所在月的第一天
     *
     * @param month：1-12
     * @return yyyy-MM-dd
     */
    public static String getFirstDayStrOfMonth(int month) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-" + StringUtils.leftPad(String.valueOf(month), 2, "0") + "-01");
            return format.format(new Date());
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 输出yyyy-MM-dd 获取当前日期所在月的第一天
     *
     * @param month：1-12
     * @return yyyy-MM-dd
     */
    public static Date getFirstDayOfMonth(int month) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-" + StringUtils.leftPad(String.valueOf(month), 2, "0") + "-01");
        return DateUtil.toUtilDate(format.format(new Date()));
    }

    /**
     * 输出yyyy-MM-dd 获取当前日期所在月的第一天
     *
     * @param date：当前时间
     * @param month：1-12
     * @return yyyy-MM-dd
     */
    public static Date getFirstDayOfMonth(Date date, int month) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-" + StringUtils.leftPad(String.valueOf(month), 2, "0") + "-01");
        return DateUtil.toUtilDate(format.format(date));
    }

    /**
     * 获取某个月最后一天
     *
     * @param month：1-12
     * @return yyyy-MM-dd
     */
    public static String getLastDayStrOfMonth(int month) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, 0);

            return formatDate(cal.getTime());
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取某个月最后一天
     *
     * @param month：1-12
     * @return yyyy-MM-dd
     */
    public static Date getLastDayOfMonth(int month) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, 0);

            return cal.getTime();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取某个月最后一天
     *
     * @param date：当前时间
     * @param month：1-12
     * @return yyyy-MM-dd
     */
    public static Date getLastDayOfMonth(Date date, int month) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, 0);

            return cal.getTime();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取年份第一天
     *
     * @param year 年份
     * @return Date
     */
    public static Date getFirstDayOfYear(int year) {
        SimpleDateFormat format = new SimpleDateFormat(StringUtils.leftPad(String.valueOf(year), 4, "0") + "yyyy-01-01");
        return DateUtil.toUtilDate(format.format(new Date()));
    }

    /**
     * 获取年份最后一天
     *
     * @param year 年份
     * @return Date
     */
    public static Date getLastDayOfYear(int year) {
        SimpleDateFormat format = new SimpleDateFormat(StringUtils.leftPad(String.valueOf(year), 4, "0") + "yyyy-12-31");
        return DateUtil.toUtilDate(format.format(new Date()));
    }

    /**
     * 获取上个月第一天
     *
     * @return yyyy-MM-dd
     */
    public static Date getFirstDayLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return to000000(cal.getTime());
    }

    /**
     * 获取上个月最后一天
     *
     * @return yyyy-MM-dd
     */
    public static Date getLastDayLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return to235959(cal.getTime());
    }

    /**
     * 取较小日期
     *
     * @param end   结束日期
     * @param begin 开始日期
     * @return Date
     */
    public static Date min(Date end, Date begin) {
        if (end == null) {
            return begin;
        }
        if (begin == null) {
            return end;
        }
        long value = end.getTime() - begin.getTime();
        return value > 0 ? begin : end;
    }

    /**
     * 取较大日期
     *
     * @param end   结束日期
     * @param begin 开始日期
     * @return Date
     */
    public static Date max(Date end, Date begin) {
        if (end == null) {
            return begin;
        }
        if (begin == null) {
            return end;
        }
        long value = end.getTime() - begin.getTime();
        return value > 0 ? end : begin;
    }

    /**
     * 日期当前小时（24小时制）是否在范围内
     *
     * @param date      日期
     * @param startHour 开始小时：0-24
     * @param endHour   结束小时：0-24
     * @return
     */
    public static boolean betweenHour(Date date, int startHour, int endHour) {
        assert date != null;
        long hour = DateUtils.getFragmentInHours(date, Calendar.DATE);

        if (startHour > endHour) {
            return startHour <= hour || hour <= endHour;
        }
        return startHour <= hour && hour <= endHour;
    }

    /**
     * 当前时间天数递增|递减，并设置小时
     *
     * @param day  天数递增|递减数
     * @param hour 小时
     * @return Date
     */
    public static Date addDays(int day, int hour) {
        Date date = new Date();
        date = DateUtils.addDays(date, day);
        date = DateUtils.setHours(date, hour);
        date = DateUtils.setMinutes(date, 0);
        date = DateUtils.setSeconds(date, 0);
        date = DateUtils.setMilliseconds(date, 0);
        return date;
    }

    /**
     * 当前时间天数递增|递减，并设置小时
     *
     * @param date 时间
     * @param day  天数递增|递减数
     * @param hour 小时
     * @return Date
     */
    public static Date addDays(Date date, int day, int hour) {
        date = DateUtils.addDays(date, day);
        date = DateUtils.setHours(date, hour);
        date = DateUtils.setMinutes(date, 0);
        date = DateUtils.setSeconds(date, 0);
        date = DateUtils.setMilliseconds(date, 0);
        return date;
    }

    /**
     * 设置月份
     *
     * @param date   日期
     * @param months 月份（1-12）
     * @return Date
     */
    public static Date setMonth(Date date, int months) {
        return DateUtils.setMonths(date, months - 1);
    }

    /**
     * 设置小时
     *
     * @param date  日期
     * @param hours 小时
     * @return Date
     */
    public static Date setHours(Date date, int hours) {
        date = DateUtils.setHours(date, hours);
        date = DateUtils.setMinutes(date, 0);
        date = DateUtils.setSeconds(date, 0);
        date = DateUtils.setMilliseconds(date, 0);
        return date;
    }

    /**
     * 传入月份构建第1天零点日期：yyyy-MM-01 00:00:00:000
     *
     * @param month (1~12)
     * @return Date
     */
    public static Date buildFirstDayByMonth(int month) {
        Date date = new Date();
        date = DateUtils.setMonths(date, month - 1);
        date = DateUtils.setDays(date, 1);
        date = DateUtils.setHours(date, 0);
        date = DateUtils.setMinutes(date, 0);
        date = DateUtils.setSeconds(date, 0);
        date = DateUtils.setMilliseconds(date, 0);
        return date;
    }

    /**
     * 传入月份构建最后1天最后1秒日期：yyyy-MM-28/29/30/31 23:59:59:999
     *
     * @param month (1~12)
     * @return Date
     */
    public static Date buildLastDayByMonth(int month) {
        Date date = new Date();
        date = DateUtils.setMonths(date, month);
        date = DateUtils.setDays(date, 1);
        date = DateUtils.addDays(date, -1);
        date = DateUtils.setHours(date, 23);
        date = DateUtils.setMinutes(date, 59);
        date = DateUtils.setSeconds(date, 59);
        date = DateUtils.setMilliseconds(date, 999);
        return date;
    }

    /**
     * 是否存在空的日期
     *
     * @param dates 日期数组
     * @return boolean
     */
    public static boolean isAnyNull(Date... dates) {
        if (dates == null || dates.length == 0) {
            return true;
        }
        for (Date date : dates) {
            if (date == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 今年第几天（从当年起已经过去多少天）
     *
     * @param date 时间对象
     * @return int
     */
    public static int getYearDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 今年第几周（从当年起已经过去多少周）
     *
     * @param date 时间对象
     * @return int
     */
    public static int getYearWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }

    /**
     * 当前时间年月日：年（eg.2022）
     *
     * @param date 时间对象
     * @return int
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 当前时间年月日：月（1-12）
     *
     * @param date 时间对象
     * @return int
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 当前时间年月日：日（1-31）
     *
     * @param date 时间对象
     * @return int
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DATE);
    }

    /**
     * 当前时间小时数（0-24）
     *
     * @param date 时间对象
     * @return int
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 当前时间分钟数（0-60）
     *
     * @param date 时间对象
     * @return int
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 当前时间秒数（0-60）
     *
     * @param date 时间对象
     * @return int
     */
    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * 大于当前时间
     *
     * @param date 时间
     * @return boolean
     */
    public static boolean gtNow(Date date) {
        assert date != null;
        return date.getTime() > System.currentTimeMillis();
    }

    /**
     * 小于当前时间
     *
     * @param date 时间
     * @return boolean
     */
    public static boolean ltNow(Date date) {
        assert date != null;
        return date.getTime() < System.currentTimeMillis();
    }

    /**
     * 字符串：当天
     *
     * @return 当天
     */
    public static String todayYyyyMmDd() {
        return formatDate(new Date());
    }

    /**
     * 输出yyyy-MM-dd 格式化
     */
    public static Timestamp getDate1900() {
        SimpleDateFormat format = new SimpleDateFormat("1900-01-01 00:00:00");
        return Timestamp.valueOf(format.format(new Date()));
    }

    /**
     * 输出yyyy-MM-dd 格式化
     */
    public static Timestamp getDate2900() {
        SimpleDateFormat format = new SimpleDateFormat("2900-01-01 00:00:00");
        return Timestamp.valueOf(format.format(new Date()));
    }

    public static void main(String[] args) {
//        System.out.println(formatDateTimes(to235959(new Date())));

        System.out.println(getFirstDayOfWeek(getYear(new Date()), getYearWeek(new Date())));

        System.out.println(getYearWeek(new Date()));

    }

}
