package com.base.android.common.utils;

import android.content.Context;
import android.text.TextUtils;

import com.base.android.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by quynguyen
 * Date: 11/30/16.
 * Project: BaseProject
 */

public class DateTimeUtils {
    //Fomart Date
    public static final String FORMAT_DATE_TYPE_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_TYPE_4 = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DATE_TYPE_2 = "HH:mm";
    public static final String FORMAT_DATE_TYPE_3 = "MMMM d, yyyy (EEE)";
    public static final String FORMAT_DATE_TYPE_JP_3 = "yyyy年MM月dd日 (EEE)";
    public static final String FORMAT_DATE_TYPE_5 = "MMMM d, yyyy";
    public static final String FORMAT_DATE_TYPE_JP_5 = "yyyy年MM月dd日";
    public static final String FORMAT_DATE_TYPE_6 = "MMM d";
    public static final String FORMAT_DATE_TYPE_JP_6 = "MM月dd日"; //yyyy年MM月dd日
    public static final String FORMAT_DATE_TYPE_7 = "yyyy-MM-dd";
    public static final String FORMAT_DATE_TYPE_8 = "yyyy/M/d";
    private final static DateFormat LONG_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
    private final static DateFormat SHORT_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private final static DateFormat SHORT_FORMAT_APP = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
    private final static DateFormat SHORT_FORMAT_APP_2 = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    private final static DateFormat SHORT_FORMAT_APP_TIME = new SimpleDateFormat("yyyy.M.d HH:mm", Locale.getDefault());
    private final static DateFormat MONTH_YEAR_FORMAT = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
    private final static DateFormat DAY_FORMAT = new SimpleDateFormat("dd", Locale.getDefault());
    private final static DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private final static DateFormat MONTH_DAY_FORMAT = new SimpleDateFormat("M/d", Locale.getDefault());

    public static String toLongFormat(Date date) {
        if (date == null) {
            return "";
        }
        return LONG_FORMAT.format(date);
    }

    public static String toShortFormat(Date date) {
        if (date == null) {
            return "";
        }
        return SHORT_FORMAT.format(date);
    }

    public static String toShortFormatApp(Date date) {
        if (date == null) {
            return "";
        }
        return SHORT_FORMAT_APP.format(date);
    }

    public static String toShortFormatApp2(Date date) {
        if (date == null) {
            return "";
        }
        return SHORT_FORMAT_APP_2.format(date);
    }

    public static String toShortFormatAppWithTime(Date date) {
        if (date == null) {
            return "";
        }
        return SHORT_FORMAT_APP_TIME.format(date);
    }

    public static String toMonthYearFormat(Date date) {
        if (date == null) {
            return "";
        }
        return MONTH_YEAR_FORMAT.format(date);
    }

    public static String toMonthDayFormat(Date date) {
        if (date == null) {
            return "";
        }
        return MONTH_DAY_FORMAT.format(date);
    }

    public static String toDayFormat(Date date) {
        if (date == null) {
            return "";
        }
        return DAY_FORMAT.format(date);
    }

    public static String toTimeFormat(Date date) {
        if (date == null) {
            return "";
        }
        return TIME_FORMAT.format(date);
    }

    public static Date parseDate(String date) {
        if (date == null) {
            return null;
        }
        try {
            return SHORT_FORMAT.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseStringToDate(String date) {
        if (date == null) {
            return null;
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TYPE_7, Locale.getDefault());
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseMonthYear(String date) {
        if (date == null) {
            return null;
        }
        try {
            return MONTH_YEAR_FORMAT.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseDay(String date) {
        if (date == null) {
            return null;
        }
        try {
            return DAY_FORMAT.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Format String to Date
     *
     * @param date format: yyyy-MM-dd HH:mm:ss
     * @return Object Date
     */
    public static Date parseStringToDateType1(String date) {
        if (!TextUtils.isEmpty(date)) {
            try {
                DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TYPE_1, Locale.getDefault());
                return dateFormat.parse(date);
            } catch (ParseException ignored) {
            }
        }
        return null;
    }

    /**
     * Format String to Date
     *
     * @param date format: yyyy/MM/dd HH:mm
     * @return Object Date
     */
    public static Date parseStringToDateType4(String date) {
        if (!TextUtils.isEmpty(date)) {
            try {
                DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TYPE_4, Locale.getDefault());
                return dateFormat.parse(date);
            } catch (ParseException ignored) {
            }
        }
        return null;
    }

    public static Date parseStringToDateType7(String date) {
        if (!TextUtils.isEmpty(date)) {
            try {
                DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TYPE_7, Locale.getDefault());
                return dateFormat.parse(date);
            } catch (ParseException ignored) {
            }
        }
        return null;
    }

    /**
     * add second to date
     *
     * @param second
     * @param beforeTime
     * @return
     */
    public static Date addSecondToDate(int second, Date beforeTime) {
        final long ONE_MILLIS = 1000;//millisecs
        long curTimeInMs = beforeTime.getTime();
        Date afterDate = new Date(curTimeInMs + (second * ONE_MILLIS));
        return afterDate;
    }

    /**
     * get diff time between date start and date end
     *
     * @param dateStart date start
     * @param dateEnd   date end
     * @return diff second
     */
    public static long getDiffTimeSecond(Date dateStart, Date dateEnd) {
        final long ONE_MILLIS = 1000;//millisecs
        long diff = dateEnd.getTime() - dateStart.getTime();
        return diff / ONE_MILLIS;
    }

    /**
     * Format Date to String
     * @param date format: HH:mm
     * @return Object String
     */
    public static String formatDateToStringType2(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TYPE_2, Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * Format Date to String
     * @param date format: yyyy'%s'MM'%s'dd'%s(%s)'
     * @return Object String
     */
    public static String formatDateToStringType3(Context context, Date date) {
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TYPE_3, Locale.getDefault());
        if (Locale.getDefault().equals(Locale.JAPAN)) {
            dateFormat = new SimpleDateFormat(FORMAT_DATE_TYPE_JP_3, Locale.getDefault());
        }
        return dateFormat.format(date);
    }

    /**
     * Format Date to String
     * @param date format: yyyy'%s'MM'%s'dd'%s'
     * @return Object String
     */
    public static String formatDateToStringType5(Context context, Date date) {
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TYPE_5, Locale.getDefault());
        if (Locale.getDefault().equals(Locale.JAPAN)) {
            dateFormat = new SimpleDateFormat(FORMAT_DATE_TYPE_JP_5, Locale.getDefault());
        }
        return dateFormat.format(date);
    }

    /**
     * Format Date to String
     *
     * @param date format: yyyy'-'MM'-'dd
     * @return Object String
     */
    public static String formatDateToStringType7(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TYPE_7, Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * Format Date to String
     * @param date format: MM'%s'dd'%s'
     * @return Object String
     */
    public static String formatDateToStringType6(Context context, Date date) {
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_TYPE_6, Locale.getDefault());
        if (Locale.getDefault().equals(Locale.JAPAN)) {
            dateFormat = new SimpleDateFormat(FORMAT_DATE_TYPE_JP_6, Locale.getDefault());
        }
        return dateFormat.format(date);
    }

    public static Calendar clearDate(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.JAPANESE);
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        return calendar;
    }

    public static Date dayByAddingUnit(Date date, int value) {
        Calendar calendar = clearDate(date);
        calendar.add(Calendar.DATE, value);
        return calendar.getTime();
    }

    public static Date weekByAddingUnit(Date date, int value) {
        Calendar calendar = clearDate(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.add(Calendar.WEEK_OF_YEAR, value);
        return calendar.getTime();
    }

    public static Date monthByAddingUnit(Date date, int value) {
        Calendar calendar = clearDate(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, value);
        return calendar.getTime();
    }

    public static Calendar convertDateToCalendarFullTime(String date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            calendar.setTime(formatter.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public static String convertLongToDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d HH:mm:ss", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String convertMilliSecondsToDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy HH:mm:ss aa", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String convertMilliSecondsToHHMM(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String convertTimeReturn_MMDD(String s, String pattern) {
        String result = "";
        if (!s.equals("")){
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
            try {
                Date date = formatter.parse(s);
                result = DateTimeUtils.toMonthDayFormat(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String convertTimeReturn_YYYYMMDD(String s, String pattern) {
        String result = "";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            Date date = formatter.parse(s);
            result = DateTimeUtils.toShortFormatApp(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String convertTimeReturn_YYYYMMDD_HHMM(String s, String pattern) {
        String result = "";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            Date date = formatter.parse(s);
            result = DateTimeUtils.toShortFormatAppWithTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String convertTimeWithPattern(String s, String patternInput, String patternOutPut) {
        String result = "";
        SimpleDateFormat formatter = new SimpleDateFormat(patternInput, Locale.getDefault());
        try {
            Date date = formatter.parse(s);

            DateFormat DATE_FORMAT = new SimpleDateFormat(patternOutPut, Locale.getDefault());
            result = DATE_FORMAT.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Calendar convertDateServerToCalendar(String date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_TYPE_7, Locale.getDefault());
        try {
            calendar.setTime(formatter.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public static Calendar getCalendar(String date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_TYPE_7, Locale.getDefault());
        try {
            calendar.setTime(formatter.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    //Convert FORMAT_DATE_TYPE_7 -> FORMAT_DATE_TYPE_8
    public static String convertFormatYearMonthDay(String s) {
        String result = "";
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_TYPE_7);
        try {
            Date date = formatter.parse(s);
            SimpleDateFormat parse = new SimpleDateFormat(FORMAT_DATE_TYPE_8);
            result = parse.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null!");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * @param cal1
     * @param cal2 cal 1 > 2 return true
     *             else false
     */
    public static boolean isAfterDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) return false;
        if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) return true;
        if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) return false;
        if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) return true;
        return cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isBeforeDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) return true;
        if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) return false;
        if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) return true;
        if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) return false;
        return cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static String getDayName(Calendar calendar) {
        String[] days = new String[]{"", "日", "月", "火", "水", "木", "金", "土"};
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return days[day];
    }

    public static String getStringDay(Context context, int code) {
        switch (code) {
            case 0:
                return context.getString(R.string.sunday);
            case 1:
                return context.getString(R.string.monday);
            case 2:
                return context.getString(R.string.tuesday);
            case 3:
                return context.getString(R.string.wednesday);
            case 4:
                return context.getString(R.string.thursday);
            case 5:
                return context.getString(R.string.friday);
            case 6:
                return context.getString(R.string.saturday);
            default:
                return "";
        }
    }

    public static String getTimeCurrent() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getTimeFromCalendar(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_TYPE_7, Locale.getDefault());
        return formatter.format(calendar.getTime());
    }


    public static  boolean checkDateIsLastDateOfMonth(String date) {
        Calendar cal = DateTimeUtils.convertDateServerToCalendar(date);
        int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dateCurrent = cal.get(Calendar.DAY_OF_MONTH);
        return lastDate == dateCurrent;
    }
}
