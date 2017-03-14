package com.epam.javase.t02;

import java.io.File;

/**
 * Created by aivanov on 3/14/2017.
 */
public class PropertiesKeeper {

    public static PropertiesKeeper getInstance(File path) throws NoSuchPropertiesFileException {
        if (!path.exists()) {
            throw new NoSuchPropertiesFileException("File not found. Please specify a valid path.");
        }

        return null;
    }

}
