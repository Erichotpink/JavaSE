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
    public void addRecord() throws Exception {

    }

    @Test
    public void findRecord() throws Exception {

        CrazyLogger log = new CrazyLogger();
        String str = "Service is not responding.";

        log.addRecord(str);

        assertFalse(log.findRecord(str).equals(""));
        assertTrue(log.findRecord(str).contains(str));
        System.out.println(log.findRecord(str));
    }

    @Test
    public void testToString() throws Exception {

        CrazyLogger log = new CrazyLogger();
        log.addRecord("Test");
        log.addRecord("");

        assertTrue(log.toString().contains("Test"));
        assertTrue(log.toString().contains(LocalDate.now().
                   format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()));

        System.out.println(log.toString());

    }
}