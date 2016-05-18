/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author kurtz
 */
public class CalendarUtil {

    public static Calendar setTimeToZero(Date date) {
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(date);
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        return calEnd;
    }

    public static Calendar setTimeToEndTime(Date date) {
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(date);
        calEnd.set(Calendar.HOUR_OF_DAY, 23);
        calEnd.set(Calendar.MINUTE, 59);
        calEnd.set(Calendar.SECOND, 59);
        calEnd.set(Calendar.MILLISECOND, 999);
        return calEnd;
    }
}
