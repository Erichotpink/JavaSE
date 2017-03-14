package com.epam.javase.t02;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/15/2017.
 */
public class PropertiesKeeperTest {

    File file = new File("resources/com.epam.javase02.t02/test.properties");

    @Test (expected = NoSuchPropertiesFileException.class)
    public void shouldThrowExceptionIfFileIsNotExist() throws Exception {
        PropertiesKeeper keeper = PropertiesKeeper.getInstance(new File(""));
    }

    


}