package com.epam.javase.t01;

/**
 * Signals that you try modify non-txt file.
 *
 * Created by aivanov on 3/15/2017.
 */
public class NotTextFileException extends Exception {

    /**
     * Constructor.
     */
    public NotTextFileException() {
        super();
    }

    /**
     * Constructor with a detail message.
     *
     * @param message the detail message
     */
    public NotTextFileException(String message) {
        super(message);
    }
}
