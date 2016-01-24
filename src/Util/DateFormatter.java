package Util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static String getReadableDate(long milliSeconds) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
        Date date = new Date(milliSeconds);
        return simpleDateFormat.format(date);
    }
}
