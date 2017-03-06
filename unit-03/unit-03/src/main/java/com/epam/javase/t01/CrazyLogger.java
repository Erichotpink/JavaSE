package com.epam.javase.t01;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by aivanov on 3/5/2017.
 */
public class CrazyLogger {

    private final StringBuilder log = new StringBuilder();
    private final String separator = ";" + String.valueOf('\u001E');


    public void addRecord(String message) {
        final LocalDateTime now = LocalDateTime.now();
        final String time = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy : hh-mm - "));
        log.append(time + message + separator);
    }

    public String findRecord(String pattern) {
        int start = log.indexOf(pattern);
        int end = log.indexOf(separator, start);

        if (start < 0) return "";

        return log.substring(start, end);
    }

    public String toString() {
        return log.toString();
    }
}
