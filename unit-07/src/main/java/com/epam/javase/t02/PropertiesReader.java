package com.epam.javase.t02;

import java.io.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Represent a class to keep properties values.
 *
 * Created by aivanov on 3/21/2017.
 */
public class PropertiesReader {

    private File file;
    private final Properties properties = new Properties();
    private boolean isFileRead = false;


    public PropertiesReader(File file) {
        checkFile(file);
        this.file = file;
    }

    /**
     * Change source file.
     *
     * @param file the new file
     * @Throws InvalidArgumentException if the file is null or doesn't exist
     */
    public void loadFile(File file) {
        checkFile(file);
        synchronized (properties) {
            this.file = file;
            isFileRead = false;
        }
    }

    public String getValue(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key value cannot be null.");
        }

        synchronized (properties) {
            isTheFirstRead();
            return properties.getProperty(key);
        }
    }

    public Set<String> getAllKeys() {
        synchronized (properties) {
            isTheFirstRead();
            return new HashSet<String>(properties.stringPropertyNames());
        }
    }

    private void isTheFirstRead() {
        if (!isFileRead) {
            try(BufferedReader in = new BufferedReader(new FileReader(file))) {
                properties.load(in);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkFile(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null.");
        }

        if (!file.exists()) {
            throw new IllegalArgumentException("The specified file doens't exist.");
        }
    }

}
