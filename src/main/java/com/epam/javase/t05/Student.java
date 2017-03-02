package com.epam.javase.t05;

import java.util.*;

/**
 * Created by aivanov on 2/28/2017.
 */
public class Student {

    private final String name;
    private final Map<Subject, Number> subjects = new Hashtable<>();

    public Student(String name) {
        Objects.requireNonNull(name, "The argument name cannot be null.");

        this.name = name;
    }

    public void addSubject(Subject subject, Number grade) {
        Objects.requireNonNull(subject, "The argument subject cannot be null.");
        Objects.requireNonNull(grade, "The argument grade cannot be null.");

        grade = subject.convertGradeToAcceptableFormat(grade);
        subjects.put(subject, grade);
    }

    public Number getSubjectGrade(Subject subject) {
        return subjects.get(subject);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
