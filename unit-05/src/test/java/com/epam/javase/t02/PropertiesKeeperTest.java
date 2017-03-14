package com.epam.javase.t02;

import org.junit.Test;

import java.io.File;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/15/2017.
 */
public class PropertiesKeeperTest {

    File file = new File("src/test/resources/com/epam/javase/t02/test.txt");
    File auxFile = new File("src/test/resources/com/epam/javase/t02/auxFile.txt");

    @Test (expected = NoSuchPropertiesFileException.class)
    public void shouldThrowExceptionIfFileIsNotExist() throws Exception {
        PropertiesKeeper keeper = new PropertiesKeeper(new File(""));
    }

    @Test (expected = NoSuchPropertyKeyException.class)
    public void shouldThrowExceptionIfPropertiesKeyNotFound() throws Exception {
        PropertiesKeeper keeper = new PropertiesKeeper(file);
        keeper.getValue("value");
    }

    @Test
    public void shouldReturnValidValueIfKeyExists() throws Exception {
        PropertiesKeeper keeper = new PropertiesKeeper(file);
        String value = keeper.getValue("key");
        assertTrue(value.equals("value"));
    }

    @Test
    public void shouldReturnAllKeys() throws Exception {
        PropertiesKeeper keeper = new PropertiesKeeper(file);
        Set<String> set = keeper.getAllKeys();
        assertTrue(set.contains("key"));
        assertTrue(set.contains("another"));
    }

    @Test
    public void shouldContainOnlyNewValueThenLoadNewFile() throws Exception {
        PropertiesKeeper keeper = new PropertiesKeeper(file);
        keeper.loadFile(auxFile);
        assertTrue (keeper.getAllKeys().size() == 1);;
        assertTrue(keeper.getAllKeys().contains("capital"));
    }
}