package com.epam.javase.t02;

/**
 * Signal that there is no such a key in the property file.
 *
 * Created by aivanov on 3/15/2017.
 */
public class NoSuchPropertyKeyException extends Exception {

    /**
     * Default constructor.
     */
    public NoSuchPropertyKeyException() {
        super();
    }

    /**
     * Counstructor with a detail message.
     * @param message the detail message
     */
    public NoSuchPropertyKeyException(String message) {
        super(message);
    }
}
