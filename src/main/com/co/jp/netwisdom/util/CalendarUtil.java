package main.com.co.jp.netwisdom.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日历(日期)相关的UTIL类
 */
public final class CalendarUtil {
    
    /** 时间戳格式 */
    public static final String FORMAT_TIMESTAMP = "YYYYmmDDHHMMSS";
    
    /** [YYYY-mm-DD]格式 */
    public static final String FORMAT_YYYYMMDD_DASH = "YYYY-mm-DD";

    /**
     * 获取目标月的天数(目标月有多少天)
     * @param year
     * @param month
     * @return
     */
    public static int daysOfMonth (int year, int month) {
        int days = 30;  // 初始化返回值
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);  // 设置目标年
        calendar.set(Calendar.MONTH, month - 1);  // 设置目标月
        
        switch (calendar.get(Calendar.MONTH) + 1) {  // 根据目标月判断当月的天数(月从0开始，所以+1)
            case 1 :
                days = 31;
                break;
            case 2 :
                if (isLeapYear(calendar.get(Calendar.YEAR))) {
                    days = 29;
                } else {
                    days = 28;
                }
                break;
            case 3 :
                days = 31;
                break;
            case 4 :
                days = 30;
                break;
            case 5 :
                days = 31;
                break;
            case 6 :
                days = 30;
                break;
            case 7 :
                days = 31;
                break;
            case 8 :
                days = 31;
                break;
            case 9 :
                days = 30;
                break;
            case 10 :
                days = 31;
                break;
            case 11 :
                days = 30;
                break;
            case 12 :
                days = 31;
                break;
        }
        
        return days;  // 返回结果
    }
    
    /**
     * 判断目标年是否是闰年
     * @param year
     * @return
     */
    private static boolean isLeapYear (int year) {
        if (year % 400 == 0) {  // 是闰年
            return true;
        }
        if (year % 4 == 0 && year % 100 != 0) {  // 是闰年
            return true;
        }
        return false;  // 不是闰年
    }
    
    /**
     * 获取目标月的第一天
     * @param year
     * @param month
     * @return
     */
    public static Calendar firstDayOfMonth (int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        
        return calendar;
    }
    
    /**
     * 获取目标月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static Calendar lastDayOfMonth (int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 0);
        
        return calendar;
        /*
         * 这里说明一下。
         * Calendar的月份从0开始，所以把月份设置成month的话，就是目标月的下个月。
         * 把日设置成0的话，就自动回退到上个月的最后一天了。
         * 该日期就是我们原本要获取的目标月的最后一天。
         */
    }
    
    /**
     * 把Calendar对象转换成对应格式的字符串
     * @param calendar
     * @param format
     * @return
     */
    public static String calToStr (Calendar calendar, String format) {
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat (format);
        return sdf.format(date);
    }

    /**
     * 获取时间戳,精确到秒
     * @return
     */
    public static String timestamp () {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat (FORMAT_TIMESTAMP);
        return sdf.format(now);
    }
    
    /**
     * 获取目标日期的星期
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int getWeek (int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);
        
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
    /**
     * 判断目标日期是否为周末
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static boolean isWeekend (int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);
        
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY :  // 星期日
                return true;
            case Calendar.SUNDAY :  // 星期六
                return true;
        }
        return false;
    }
    
}
