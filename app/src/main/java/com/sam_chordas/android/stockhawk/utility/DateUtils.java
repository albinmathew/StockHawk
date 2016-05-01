package com.sam_chordas.android.stockhawk.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author albinmathew
 * @date 01/05/16.
 */
public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String getFormattedDate(long dateInMillis) {
        Locale localeUS = new Locale("en", "US");
        SimpleDateFormat queryDayFormat = new SimpleDateFormat(DateUtils.DATE_FORMAT, localeUS);
        return queryDayFormat.format(dateInMillis);
    }

    public static String getAWeekBackDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -7);
        return getFormattedDate(cal.getTimeInMillis());
    }

    public static String getAMonthBackDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return getFormattedDate(cal.getTimeInMillis());
    }

    public static String getThreeMonthsBackDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -3);
        return getFormattedDate(cal.getTimeInMillis());
    }

    public static String getSixMonthsBackDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -6);
        return getFormattedDate(cal.getTimeInMillis());
    }

    public static String getAYearBackDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -1);
        return getFormattedDate(cal.getTimeInMillis());
    }
}
