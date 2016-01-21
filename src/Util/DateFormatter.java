package Util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static String getReadableDate(long yourMilliSeconds) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        Date resultDate = new Date(yourMilliSeconds);
        return sdf.format(resultDate);
    }
}
