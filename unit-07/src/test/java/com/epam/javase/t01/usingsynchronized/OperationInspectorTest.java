package com.epam.javase.t01.usingsynchronized;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by aivanov on 3/20/2017.
 */

public class OperationInspectorTest {

    File path = new File("src/test/resources/com/epam/javase/t01/transfers.txt");
    File answer = new File("src/test/resources/com/epam/javase/t01/answers.txt");

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFileNotExist() throws Exception {
        OperationInspector inspector = new OperationInspector(new File(""));
    }

    @Test
    public void shouldCreateValidQueueOfOrders() throws Exception {
        OperationInspector inspector = new OperationInspector(path);

        for (String[] s : inspector.getOrdersQueue()) {
            assertTrue(s.length == 3);
        }
    }

    @Test
    public void checkIfResultEqualsToExpectedValues() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(answer));

        OperationInspector inspector = new OperationInspector(path);
        inspector.run();

        Thread.sleep(1000);

        String s = "";

        while ((s = in.readLine()) != null) {
            int id = Integer.parseInt(s.split(",")[0]);
            int balance = Integer.parseInt(s.split(",")[1]);
            assertTrue(inspector.getAccountBalanceByID(id) == balance);
        }
    }
}
