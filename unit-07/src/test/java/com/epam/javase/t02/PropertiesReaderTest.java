package com.epam.javase.t02;

import org.junit.Test;
import java.io.File;
import java.util.Set;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 3/21/2017.
 */
public class PropertiesReaderTest {

    File file = new File("src/test/resources/com/epam/javase/t02/test.txt");
    File auxFile = new File("src/test/resources/com/epam/javase/t02/auxFile.txt");
    PropertiesReader reader = new PropertiesReader(file);

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFileIsNotExist() throws Exception {
        PropertiesReader temp = new PropertiesReader(new File(""));
    }

    @Test
    public void shouldThrowExceptionIfPropertiesKeyNotFound() throws Exception {
        assertNull(reader.getValue(""));
    }

    @Test
    public void shouldReturnValidValueIfKeyExists() throws Exception {
        String value = reader.getValue("key");
        assertTrue(value.equals("value"));
    }

    @Test
    public void shouldReturnAllKeys() throws Exception {
        Set<String> set = reader.getAllKeys();
        assertTrue(set.contains("key"));
        assertTrue(set.contains("another"));
    }

    @Test
    public void shouldContainOnlyNewValueThenLoadNewFile() throws Exception {
        reader.loadFile(auxFile);
        assertTrue (reader.getAllKeys().size() == 1);;
        assertTrue(reader.getAllKeys().contains("capital"));
    }
}
