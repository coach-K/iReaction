package Logger;

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
     * accept date of the log
     *
     * @param tag     for message log
     * @param message to be formatted
     * @param date    of the log
     */
    public Log(String tag, String message, long date) {
        this.tag = tag;
        this.message = message;
        this.date = date;
    }

    /**
     * Returns new object with the specified arguments
     * <p/>
     * Accepts tags for message log
     * accept message to be formatted
     * accept date of the log
     *
     * @param tag     for message log
     * @param message to be formatted
     * @param date    of the log
     * @return new object with the specified arguments
     */
    public static Log p(String tag, String message, long date) {
        return new Log(tag, message, date);
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
