package Logger;


import Util.DateFormatter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

/**
 * <p>{@code LogWriter} is meant for writing Log objects.
 * <p/>
 * An interpreter for printf-style format strings.  This class provides support
 * for layout justification and alignment,
 * writing to file and logging to console as output.
 *
 * @author OGUNDE KEHINDE
 * @see Logger.Log
 * @see java.util.Formatter
 * @since 1.7
 */
public class LogWriter {

    private Formatter formatter;

    /**
     * Construct this class with the specified argument
     *
     * @param file file path
     * @throws IOException if file not found
     */
    public LogWriter(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        this.formatter = new Formatter(fileWriter);
    }

    /**
     * Writes the specified element to this file and console
     *
     * @param log to be written
     */
    public synchronized void write(Log log) {
        String format = "%s %s - %s\r\n";
        String tag = log.getTag();
        String date = "(" + DateFormatter.getReadableDate(log.getDate()) + ")";
        String message = log.getMessage();

        this.formatter.format(format, tag, date, message);
        this.formatter.flush();
    }

    /**
     * Close this formatter
     */
    public synchronized void close(){
        this.formatter.close();
    }
}
