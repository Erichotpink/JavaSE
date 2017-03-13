package com.epam.javase.t03;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 3/13/2017.
 */
public class Utf8ToUtf16FileConverterTest {

    File source = new File("C:/temp/UTF8.txt");
    File destination = new File ("C:/temp/UTF16.txt");
    String data = "Солнце светит высоко!!!" + System.lineSeparator() + "Завтра будет дождь.";

    @Before
    public void createSourceFile() throws Exception {
        try ( Writer writer = new OutputStreamWriter(new FileOutputStream(source), StandardCharsets.UTF_8)) {
            writer.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(source.exists());
    }

    @Test
    public void shouldCreateResultFile() throws Exception {

        destination.delete();
        assertFalse(destination.exists());

        FileEncodingConvertor.convertFromUtf8ToUtf16(source, destination);

        assertTrue(source.exists());
        assertTrue(destination.exists());
    }

    @Test
    public void destinationFileShouldContainsEqualsData () throws Exception {

        try (Reader in = new BufferedReader(new InputStreamReader(new FileInputStream(destination), "utf-16"))) {

            StringBuilder result = new StringBuilder();
            char[] buffer = new char[1024];

            while(in.read(buffer, 0 , buffer.length) > 0) {
                result.append(buffer);
            }

            System.out.println(result.toString());
            assertTrue(result.toString().contains(data));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
