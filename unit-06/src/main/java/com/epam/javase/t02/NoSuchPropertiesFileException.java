package com.epam.javase.t02;

/**
 * Signals that there is now such a properties files.
 *
 * Created by aivanov on 3/15/2017.
 */
public class NoSuchPropertiesFileException extends Exception {

    /**
     * Default constructor
     */
    public NoSuchPropertiesFileException() {
        super();
    }

    /**
     * Counstructor with a detail message.
     *
     * @param message the detail message
     */
    public NoSuchPropertiesFileException(String message) {
        super(message);
    }
}
