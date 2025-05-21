package com.fq.util;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MyDateUtil {

    /**
     * 获取当前系统的时间戳
     */
    public static Timestamp getTime() {
        Date date = new Date();
        long time = date.getTime();
        Calendar d = Calendar.getInstance();
        d.setTime(new Timestamp(time));
        d.set(14, 0);
        Timestamp createtime = new Timestamp(d.getTime().getTime());
        return createtime;
    }

    public static Timestamp stringToTimestamp(String timeStr) {
        Timestamp ts = null;
        try {
            ts = Timestamp.valueOf(timeStr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("时间处理异常", e);
        }
        return ts;
    }

    public static Timestamp stringToTimestamp(String timeStr, String pattern) {
        if (timeStr == null || "".equals(timeStr.trim())) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(pattern);

        format.setLenient(false);
        Timestamp ts = null;
        try {
            if (timeStr.indexOf("/") != -1) {
                timeStr = timeStr.replaceAll("/", "-");
            }

            if (timeStr.length() < pattern.length()) {
                timeStr += " 00:00:00";
            }

            ts = new Timestamp(format.parse(timeStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("时间处理异常", e);
        }
        return ts;
    }

    public static Timestamp stringToShortTimestamp(String timeStr) {
        return stringToTimestamp(timeStr, "yyyy-MM-dd");
    }

    public static Timestamp stringToLongTimestamp(String timeStr) {
        return stringToTimestamp(timeStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static String timestampToString(Timestamp timestamp, String pattern) {
        String tsStr = "";
        if (timestamp == null) {
            return tsStr;
        }
        DateFormat sdf = new SimpleDateFormat(pattern);
        try {
            tsStr = sdf.format(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("时间处理异常", e);
        }
        return tsStr;
    }

    public static String timestampToLongString(Timestamp timestamp) {
        return timestampToString(timestamp, "yyyy-MM-dd HH:mm:ss");
    }

    public static String timestampToShortString(Timestamp timestamp) {
        return timestampToString(timestamp, "yyyy-MM-dd");
    }

    public static Date timestampToDate(Timestamp temTimestamp) {
        Date date = new Date();
        try {
            date = temTimestamp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("时间处理异常", e);
        }
        return date;
    }

    public static Date stringToDate(String dateStr, String pattern) {
        if (dateStr == null || dateStr.length() <= 0) {
            return null;
        }
        Date date = new Date();

        if (dateStr.indexOf("/") != -1) {
            dateStr = dateStr.replaceAll("/", "-");
        }

        if (dateStr.length() < pattern.length()) {
            dateStr += " 00:00:00";
        }

        DateFormat sdf = new SimpleDateFormat(pattern);
        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("时间处理异常", e);
        }
        return date;
    }

    public static Date stringToShortDate(String dateStr) {
        return stringToDate(dateStr, "yyyy-MM-dd");
    }

    public static Date stringToLongDate(String dateStr) {
        return stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static String dateToString(Date date, String pattern) {
        String dateStr = "";
        if (date == null) {
            return dateStr;
        }
        DateFormat sdf = new SimpleDateFormat(pattern);
        try {
            dateStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("时间处理异常", e);
        }
        return dateStr;
    }

    public static String dateToShortString(Date date) {
        return dateToString(date, "yyyy-MM-dd");
    }

    public static String dateToLongString(Date date) {
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date dateToDate(Date date, String pattern) {
        Date newDate = null;
        if (date == null) {
            return newDate;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            String s = sdf.format(date);
            newDate = sdf.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("时间处理异常", e);
        }
        return newDate;
    }

    public static Timestamp dateToTimestamp(Date date) {
        Timestamp newTime = null;
        if (date == null) {
            return newTime;
        }
        try {
            newTime = new Timestamp(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("时间处理异常", e);
        }
        return newTime;
    }

    public static Date dateToShortDate(Date date) {
        return dateToDate(date, "yyyy-MM-dd");
    }

    public static Date dateToLongDate(Date date) {
        return dateToDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Calendar dateToCalendar(Date date) {
        if (date == null) {
            return null;
        }
        int year = MyDateUtil.getYear(date);
        int month = MyDateUtil.getMonth(date);
        int day = MyDateUtil.getDay(date);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.set(year, (month - 1), day);
        return calendar;
    }

    public static String toISO(String dateStr) {
        String newDateStr = "";
        try {
            Date date = MyDateUtil.stringToLongDate(dateStr);
            newDateStr = toISO(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDateStr;
    }

    public static String toISO(Date date) {
        String dateStr = "";
        try {
            if (date == null) {
                return dateStr;
            }
            DateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            dateStr = sf.format(date);
            dateStr += "+08:00";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateStr;
    }

    public static String isoToString(String dateStr) {
        String isoStr = "";
        try {
            if (dateStr == null || dateStr.length() <= 0) {
                return isoStr;
            }
            DateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date parse = sf.parse(dateStr);
            isoStr = MyDateUtil.dateToLongString(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isoStr;
    }

    /**
     * 获取年起始时间
     **/
    public static String getYearStartDate(Date date) {
        date = (date == null) ? new Date() : date;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return dateToLongString(calendar.getTime());
    }

    public static Date getYearStartDateLongDate(Date date) {
        String dateStr = getYearStartDate(date);
        return stringToLongDate(dateStr);
    }

    /**
     * 获取年结束时间
     **/
    public static String getYearEndDate(Date date) {
        date = (date == null) ? new Date() : date;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);

        return dateToLongString(calendar.getTime());
    }

    public static Date getYearEndDateLongDate(Date date) {
        String dateStr = getYearEndDate(date);
        return stringToShortDate(dateStr);
    }

    /**
     * 获取月开始时间
     **/
    public static String getMonthStartDate(Date date) {
        date = (date == null) ? new Date() : date;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, firstDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return dateToLongString(calendar.getTime());
    }

    public static Date getMonthStartLongDate(Date date) {
        String dateStr = getMonthStartDate(date);
        return stringToLongDate(dateStr);
    }

    public static Date getMonthStartShortDate(Date date) {
        String dateStr = getMonthStartDate(date);
        return stringToShortDate(dateStr);
    }

    public static String getMonthStartShortStr(Date date) {
        Date currDate = getMonthStartShortDate(date);
        return dateToShortString(currDate);
    }

    public static String getMonthStartLongStr(Date date) {
        Date currDate = getMonthStartLongDate(date);
        return dateToLongString(currDate);
    }

    /**
     * 获取月结束时间
     **/
    public static String getMonthEndDate(Date date) {
        date = (date == null) ? new Date() : date;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return dateToLongString(calendar.getTime());
    }

    public static Date getMonthEndLongDate(Date date) {
        String dateStr = getMonthEndDate(date);
        return stringToLongDate(dateStr);
    }

    public static Date getMonthEndShortDate(Date date) {
        String dateStr = getMonthEndDate(date);
        return stringToShortDate(dateStr);
    }

    public static String getMonthEndShortStr(Date date) {
        Date currDate = getMonthEndShortDate(date);
        return dateToShortString(currDate);
    }

    public static String getMonthEndLongStr(Date date) {
        Date currDate = getMonthEndLongDate(date);
        return dateToLongString(currDate);
    }

    /**
     * 获取天开始时间
     **/
    public static String getDayStartDate(Date date) {
        date = (date == null) ? new Date() : date;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return dateToLongString(calendar.getTime());
    }

    public static Date getDayStartDateLongDate(Date date) {
        String dateStr = getDayStartDate(date);
        return stringToLongDate(dateStr);
    }

    /**
     * 获取天结束时间
     **/
    public static String getDayEndDate(Date date) {
        date = (date == null) ? new Date() : date;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return dateToLongString(calendar.getTime());
    }

    public static Date getDayEndDateLongDate(Date date) {
        String dateStr = getDayEndDate(date);
        return stringToLongDate(dateStr);
    }

    /**
     * 获取天开始Timestamp
     **/
    public static Timestamp getDayStartTime(Date date) {
        date = (date == null) ? new Date() : date;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return dateToTimestamp(calendar.getTime());
    }

    /**
     * 获取天结束Timestamp
     **/
    public static Timestamp getDayEndTime(Date date) {
        date = (date == null) ? new Date() : date;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return dateToTimestamp(calendar.getTime());
    }

    /**
     * 校验日期是否存在交叉
     **/
    public static boolean dateCross(Date beginTime1, Date endTime1, Date beginTime2, Date endTime2) {
        long b1 = (beginTime1 != null) ? beginTime1.getTime() : 0L;
        long e1 = (endTime1 != null) ? endTime1.getTime() : 0L;
        long b2 = (beginTime2 != null) ? beginTime2.getTime() : 0L;
        long e2 = (endTime2 != null) ? endTime2.getTime() : 0L;

        // b1 - [ b2 - e2 ] - e1
        if (b1 <= b2 && e1 >= e2) {
            return true;
        }

        // b2 - [ b1 - e1 ] - e2
        if (b1 >= b2 && e1 <= e2) {
            return true;
        }

        // b1 - [ b2 - e1 ] - e2
        if (b1 < b2 && e1 >= b2) {
            return true;
        }

        // b2 - [ b1 - e2 ] - e1
        if (b1 > b2 && b1 < e2) {
            return true;
        }
        return false;
    }

    public static double getTimeCoincidence(Date beginTime1, Date endTime1, Date beginTime2, Date endTime2) {
        double coincidenceTime = 0;
        long b1 = (beginTime1 != null) ? beginTime1.getTime() : 0L;
        long e1 = (endTime1 != null) ? endTime1.getTime() : 0L;
        long b2 = (beginTime2 != null) ? beginTime2.getTime() : 0L;
        long e2 = (endTime2 != null) ? endTime2.getTime() : 0L;
        if ((b1 <= b2) && (e1 >= e2)) {
            coincidenceTime = getTimeDifference(endTime2, beginTime2);
        } else {
            if ((b1 >= b2) && (e1 <= e2)) {
                coincidenceTime = getTimeDifference(endTime1, beginTime1);
            } else {
                if ((b1 >= b2) && (b1 < e2) && (e2 <= e1)) {
                    coincidenceTime = getTimeDifference(endTime2, beginTime1);
                } else {
                    if ((b1 <= b2) && (e1 <= e2) && (e1 > b2)) {
                        coincidenceTime = getTimeDifference(endTime1, beginTime2);
                    } else {
                        if ((e1 <= b2) || (b1 >= e2)) {
                            coincidenceTime = 0;
                        } else {
                            coincidenceTime = -1;
                            System.out.println("Unexpected date combination,Could not calculate coincidence.");
                        }
                    }
                }
            }
        }
        return coincidenceTime;
    }

    /**
     * 获取时间差,单位(分钟)
     */
    public static double getTimeDifference(Date date1, Date date2) {
        double df = 0;
        try {
            double num = (date1.getTime() - date2.getTime()) / 60000.0D;
            num = BigDecimal.valueOf(num).setScale(2, 4).doubleValue();
            if (num > 0.0D) {
                df = num;
            } else if (num == 0.0D) {
                df = 0;
            } else {
                df = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("时间处理异常", e);
        }
        return df;
    }

    /**
     * 获取时间差,单位(秒)
     */
    public static double getTimeDiffSec(Date date1, Date date2) {
        long ldate1 = date1.getTime();
        long ldate2 = date2.getTime();
        long diff = (ldate1 < ldate2) ? (ldate2 - ldate1) : (ldate1 - ldate2);
        BigDecimal val = new BigDecimal(diff);
        val = val.divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP);

        return val.doubleValue();
    }

    /**
     * 获取时间差,单位(分钟)
     */
    public static double getTimeDiffMin(Date date1, Date date2) {
        long ldate1 = date1.getTime();
        long ldate2 = date2.getTime();
        long diff = (ldate1 < ldate2) ? (ldate2 - ldate1) : (ldate1 - ldate2);
        BigDecimal val = new BigDecimal(diff);
        val = val.divide(new BigDecimal(60 * 1000), 2, RoundingMode.HALF_UP);

        return val.doubleValue();
    }

    /**
     * 获取时间差,单位(小时)
     */
    public static double getTimeDiffHour(Date date1, Date date2) {
        long ldate1 = date1.getTime();
        long ldate2 = date2.getTime();
        long diff = (ldate1 < ldate2) ? (ldate2 - ldate1) : (ldate1 - ldate2);
        BigDecimal val = new BigDecimal(diff);
        val = val.divide(new BigDecimal(60 * 60 * 1000), 2, RoundingMode.HALF_UP);

        return val.doubleValue();
    }

    /**
     * 获取时间差,单位(天)
     */
    public static double getTimeDiffDay(Date date1, Date date2) {
        long ldate1 = date1.getTime();
        long ldate2 = date2.getTime();
        long diff = (ldate1 < ldate2) ? (ldate2 - ldate1) : (ldate1 - ldate2);
        BigDecimal val = new BigDecimal(diff);
        val = val.divide(new BigDecimal(24 * 60 * 60 * 1000), 1, RoundingMode.HALF_UP);

        return val.doubleValue();
    }

    /**
     * 获取时间差,单位(月)
     */
    public static int getTimeDiffMonth(Date date1, Date date2) {
        double timeDiffDay = getTimeDiffDay(date1, date2);
        Double month = new Double(timeDiffDay / 30);
        return month.intValue();
    }

    /**
     * 偏移年
     */
    public static Date offsetYear(Date date, int offset) {
        if (date == null) {
            throw new RuntimeException("日期为空");
        }
        return offset(date, Calendar.YEAR, offset);
    }

    /**
     * 偏移月
     */
    public static Date offsetMonth(Date date, int offset) {
        if (date == null) {
            throw new RuntimeException("日期为空");
        }
        return offset(date, Calendar.MONTH, offset);
    }

    /**
     * 偏移天
     */
    public static Date offsetDay(Date date, int offset) {
        if (date == null) {
            throw new RuntimeException("日期为空");
        }
        return offset(date, Calendar.DAY_OF_YEAR, offset);
    }

    /**
     * 偏移小时
     */
    public static Date offsetHour(Date date, int offset) {
        if (date == null) {
            throw new RuntimeException("日期为空");
        }
        return offset(date, Calendar.HOUR_OF_DAY, offset);
    }

    /**
     * 偏移分钟
     */
    public static Date offsetMin(Date date, int offset) {
        if (date == null) {
            throw new RuntimeException("日期为空");
        }
        return offset(date, Calendar.MINUTE, offset);
    }

    /**
     * 偏移秒
     */
    public static Date offsetSec(Date date, int offset) {
        if (date == null) {
            throw new RuntimeException("日期为空");
        }
        return offset(date, Calendar.SECOND, offset);
    }

    /**
     * 调整日期和天数
     */
    public static Date offset(Date date, int datePart, int offset) {
        if (date == null) {
            throw new RuntimeException("日期为空");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(datePart, offset);

        return cal.getTime();
    }

    public static Integer getDiffYear(Date beginDate) {
        return getDiffYear(beginDate, new Date());
    }

    public static Integer getDiffYear(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        if (cal.before(beginDate)) {
            throw new IllegalArgumentException("beginDate 时间在endDate之后，计算失败!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(beginDate);
        int yearBegin = cal.get(Calendar.YEAR);
        int monthBegin = cal.get(Calendar.MONTH);
        int dayOfMonthBegin = cal.get(Calendar.DAY_OF_MONTH);

        int year = yearNow - yearBegin;
        if (monthNow <= monthBegin) {
            if (monthNow == monthBegin) {
                if (dayOfMonthNow < dayOfMonthBegin) {
                    year--;
                }
            } else {
                year--;
            }
        }
        return year;
    }

    /**
     * 获取年
     */
    public static int getYear() {
        return getYear(new Date());
    }

    /**
     * 获取年
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * 获取天
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 日期比较,1=a>b,0=(a=b),-1=(a<b)
     */
    public static int compareTo(Date beginDate, Date endDate) {
        return beginDate.compareTo(endDate);
    }

    /**
     * 获取年龄
     */
    public static Integer getAgeByBirthday(Date birthday) {
        if (birthday == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) {
            return null;
        }
        int yearNow = cal.get(1);
        int monthNow = cal.get(2) + 1;
        int dayOfMonthNow = cal.get(5);

        cal.setTime(birthday);
        int yearBirth = cal.get(1);
        int monthBirth = cal.get(2) + 1;
        int dayOfMonthBirth = cal.get(5);

        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) --age;
            } else {
                --age;
            }
        }
        return age;
    }

    /**
     * 获取当前时间归属的季度
     **/
    public static String getQuarter() {
        return getQuarter(new Date());
    }

    /**
     * 获取指定时间归属的季度
     **/
    public static String getQuarter(String currDateStr) {
        String value = "";
        if (currDateStr == null || currDateStr.length() <= 0) {
            return value;
        }

        Date currDate = stringToShortDate(currDateStr);
        return getQuarter(currDate);
    }

    /**
     * 获取指定时间归属的季度
     **/
    public static String getQuarter(Date currDate) {
        String value = "0";
        if (currDate == null) {
            return value;
        }

        int month = getMonth(currDate);
        int quarter = (month % 3 == 0) ? (month / 3) : (month / 3 + 1);

        value = String.valueOf(quarter);
        return value;
    }

    /**
     * 根据季度获取季度的开始与结束时间
     **/
    public static String[] getQuarterZones(String quarter) {
        String[] retValue = null;
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        if ("1".equals(quarter)) {
            start.set(Calendar.MONTH, 0);
            start.set(Calendar.DATE, 1);

            end.set(Calendar.MONTH, 3);
            end.set(Calendar.DATE, 1);
        } else if ("2".equals(quarter)) {
            start.set(Calendar.MONTH, 3);
            start.set(Calendar.DATE, 1);

            end.set(Calendar.MONTH, 6);
            end.set(Calendar.DATE, 1);
        } else if ("3".equals(quarter)) {
            start.set(Calendar.MONTH, 6);
            start.set(Calendar.DATE, 1);

            end.set(Calendar.MONTH, 9);
            end.set(Calendar.DATE, 1);
        } else if ("4".equals(quarter)) {
            start.set(Calendar.MONTH, 9);
            start.set(Calendar.DATE, 1);

            end.set(Calendar.MONTH, 12);
            end.set(Calendar.DATE, 1);
        }
        end.add(Calendar.DATE, -1);

        retValue = new String[2];

        retValue[0] = getMonthStartDate(start.getTime());
        retValue[1] = getMonthEndDate(end.getTime());
        return retValue;
    }

    /**
     * 获取季度的最后一个月
     **/
    public static String getQuarterBeginDateStrByNum(String quarter) {
        String[] quarterZones = getQuarterZones(quarter);
        if (quarterZones == null) {
            return "";
        }
        return quarterZones[0];
    }

    /**
     * 获取季度的最后一个月
     **/
    public static String getQuarterEndDateStrByNum(String quarter) {
        String[] quarterZones = getQuarterZones(quarter);
        if (quarterZones == null) {
            return "";
        }
        return quarterZones[1];
    }

    /**
     * 根据日期，获取当前所属季度的最后一个月
     **/
    public static String getQuarterBeginDateStr(Date currDate) {
        String quarter = getQuarter(currDate);
        return getQuarterBeginDateStrByNum(quarter);
    }

    /**
     * 根据日期，获取当前所属季度的最后一个月
     **/
    public static String getQuarterEndDateStr(Date currDate) {
        String quarter = getQuarter(currDate);
        return getQuarterEndDateStrByNum(quarter);
    }

    /**
     * 获取当月多少天
     **/
    public static int getMonthDayNum(Date currDate) {
        int maxDay = 0;
        if (currDate == null) {
            return maxDay;
        }
        Calendar a = Calendar.getInstance();
        a.setTime(currDate);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        maxDay = a.get(Calendar.DATE);
        return maxDay;
    }

    public static boolean between(Date currDate, Date beginDate, Date endDate) {
        if (currDate == null || beginDate == null || endDate == null) {
            return false;
        }
        if (currDate.getTime() >= beginDate.getTime() && currDate.getTime() <= endDate.getTime()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String currDate = null;
        MyDateUtil.stringToLongTimestamp(currDate);
        Date stringToLongDate = MyDateUtil.stringToLongDate(currDate);
        String yearStartTime = MyDateUtil.getYearStartDate(stringToLongDate);
        String yearEndTime = MyDateUtil.getYearEndDate(stringToLongDate);
        System.out.println(yearStartTime + " ::: " + yearEndTime);
    }

}
