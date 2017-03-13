package com.epam.javase.t03;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Represent a class to convert file encoding. Contains method convertFromUtf8ToUtf16 to convert from utf-8 to utf-16.
 *
 * Created by aivanov on 3/12/2017.
 */
public class FileEncodingConvertor {

    /**
     * Convert file encoding from utf-8 to utf-16.
     *
     * @param source source file
     * @param output destination file
     */
    public static void convertFromUtf8ToUtf16(File source, File output) {

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(source), "utf-8"));
             Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_16))) {

            StringBuilder data = new StringBuilder();

            char[] buffer = new char[1024];

            while(in.read(buffer, 0 , buffer.length) > 0) {
                data.append(buffer);
            }

            out.append(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

