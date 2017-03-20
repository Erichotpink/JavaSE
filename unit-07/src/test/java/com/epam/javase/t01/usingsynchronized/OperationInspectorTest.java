package com.epam.javase.t01.usingsynchronized;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by aivanov on 3/20/2017.
 */

public class OperationInspectorTest {

    File path = new File("src/test/resources/com/epam/javase/t01/transfers.txt");

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

        inspector.getOrdersQueue().stream().forEach(s -> System.out.println(Arrays.toString(s)));
    }

}
