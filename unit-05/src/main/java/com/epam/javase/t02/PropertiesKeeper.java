package com.epam.javase.t02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Represent a class to keep properties values.
 *
 * Created by aivanov on 3/14/2017.
 */
public class PropertiesKeeper {

    private Properties data = new Properties();

    private PropertiesKeeper(Properties data) {
        this.data = data;
    }

    /**
     * Return instance of the class.
     *
     * @param path file to be loaded
     * @return instance of the class
     * @throws NoSuchPropertiesFileException if the file doesn't exist
     */
    public static PropertiesKeeper getInstance(File path) throws NoSuchPropertiesFileException {
        if (!path.exists()) {
            throw new NoSuchPropertiesFileException("File not found. Please specify a valid path.");
        }

        Properties data = new Properties();

        try (InputStream in = new FileInputStream(path)) {
            data.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return new PropertiesKeeper(data);
    }

    public Set<String> getAllKeys() {
        return new HashSet<String>(data.stringPropertyNames());
    }

    public String getValue(String key) throws NoSuchPropertyKeyException {
        if (data.getProperty(key) == null) {
            throw new NoSuchPropertyKeyException("The specified key don't exist.");
        }

        return (String) data.getProperty(key);
    }

}
