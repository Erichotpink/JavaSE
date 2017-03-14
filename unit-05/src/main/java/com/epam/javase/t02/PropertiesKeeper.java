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

    public PropertiesKeeper() {}

    /**
     * Constructor.
     *
     * @param file file to be loaded
     * @throws NoSuchPropertiesFileException if the file doesn't exist
     */
    public PropertiesKeeper(File file) throws NoSuchPropertiesFileException {
        loadFile(file);
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

    public void loadFile(File file) throws NoSuchPropertiesFileException {
        if (!file.exists()) {
            throw new NoSuchPropertiesFileException("File not found. Please specify a valid path.");
        }

        try (InputStream in = new FileInputStream(file)) {
            if (data.size() > 0) {
                data = new Properties();
            }
            data.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
