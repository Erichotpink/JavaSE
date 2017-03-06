package com.epam.javase.t01;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/6/2017.
 */
public class CrazyLoggerTest {
    @Test
    public void testLogAndReadMethods() throws Exception {

        CrazyLogger log = new CrazyLogger();

        String record1 = "Service is starting.";
        String record2 = "Service is not responding.";
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        log.log(record1);
        log.log(record2);
        log.log(record2);

        assertTrue(log.read(record1).toString().contains(record1));
        assertTrue(log.read(record1).toString().contains(date));

        assertTrue(log.read(record2).toString().contains(record2));
        assertTrue(log.read(record2).toString().contains(date));
    }

    @Test
    public void testToConsole() {
        CrazyLogger log = new CrazyLogger();

        String record1 = "Service is starting.";
        String record2 = "Service is not responding.";

        log.log(record1);
        log.log(record2);
        log.log(record2);

        log.toConsole();
        System.out.println("---------------");
        log.toConsole(record1);
    }
}