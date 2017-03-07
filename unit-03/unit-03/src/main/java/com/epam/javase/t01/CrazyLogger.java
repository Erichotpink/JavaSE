package com.epam.javase.t01;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Represents a simple log utility.
 *
 * The class provides log and read methods to write and read log records and
 * toConsole methods to print the log to the specified stream.
 * Each record has the following format:
 * dd-mm-YYYY : hh-mm – message + separator, where separator is ';' + record separator unicode code('\u001e').
 *
 * Created by aivanov on 3/5/2017.
 */
public class CrazyLogger {

    private final StringBuilder log = new StringBuilder();
    private final String separator = ";" + String.valueOf('\u001E');

    /**
     * Add a new record to the log.
     *
     * @param event contains an event to be added
     */
    public void log(String event) {
        log.append(MessageFormat.format("{0,date,dd-MM-yyyy : kk-mm} - {1}{2}", new Date(), event, separator));
    }

    /**
     * Read all events from the log
     *
     * @return full event log
     */
    public StringBuilder read() {
        return read(log, 0, "", new StringBuilder());
    }


    /**
     * Read events from the log that contains the specified template.
     *
     * @param event the event to be found in the log
     * @return all log records that contain the specified event
     */
    public StringBuilder read(String event) {
        return read(log, 0, event, new StringBuilder());
    }

    /**
     * Print the log to console.
     */
    public void toConsole() {
        toConsole(System.out);
    }

    /**
     * Print the log to the specified print stream.
     *
     * @param out stream to print the log
     */

    public void toConsole(OutputStream out) {
        PrintStream ps = new PrintStream(out, true);
        ps.print(read(""));
    }

    /**
     * Filter the log by the specified event and print to console.
     *
     * @param event events to be printed
     */
    public void toConsole(String event) {
       toConsole(System.out, event);
    }

    /**
     * Filter the log by the specified event and print to the specified print stream.
     *
     * @param out stream to print the log
     * @param event events to be printed
     */
    public void toConsole(OutputStream out, String event) {
        PrintStream ps = new PrintStream(out, true);
        ps.print(read(event));
    }

    private StringBuilder read(StringBuilder log, int startIndex, String event, StringBuilder result) {

        final int matchIndex = log.indexOf(event, startIndex);

        if (matchIndex < 0 || startIndex >= log.length()) {
            return result;
        }

        final int endRecordIndex = log.indexOf(separator, matchIndex);

        String temp = log.substring(startIndex, endRecordIndex);

        final int lastSeparatorIndex = temp.lastIndexOf(separator);
        final int startEventRecord = (lastSeparatorIndex < 0) ? 0 : lastSeparatorIndex + separator.length();

        result.append(temp.substring(startEventRecord));
        result.append(System.lineSeparator());

        read(log, endRecordIndex + separator.length(), event, result);

        return result;
    }
}
