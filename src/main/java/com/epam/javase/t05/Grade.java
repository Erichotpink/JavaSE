package com.epam.javase.t05;

/**
 * Created by aivanov on 2/27/2017.
 */
public interface Grade<T extends Number> {
    public T getMaxGrade();
    public Class<T> getGradeType();
}
