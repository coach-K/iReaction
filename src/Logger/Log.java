package logger;

import util.DateFormatter;

/**
 * This class creates element of a Log
 */
public class Log {

    private String tag;
    private String message;
    private long date;

    /**
     * Construct this class with the specified element
     * Accepts tags for message log
     * accept message to be formatted
     *
     * @param tag     for message log
     * @param message to be formatted
     */
    public Log(String tag, String message) {
        this.tag = tag;
        this.message = message;
        this.date = System.currentTimeMillis();
        String format = "%s %s - %s\r\n";
        String readableDate = "(" + DateFormatter.getReadableDate(this.date) + ")";
        System.out.printf(format, tag, readableDate, message);
    }

    /**
     * Returns new object with the specified arguments
     * <p/>
     * Accepts tags for message log
     * accept message to be formatted
     *
     * @param tag     for message log
     * @param message to be formatted
     * @return new object with the specified arguments
     */
    public static Log p(String tag, String message) {
        return new Log(tag, message);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
