package com.epam.javase.t05;

import java.util.*;

/**
 * Created by aivanov on 2/28/2017.
 */
public class Student {

    private final String name;
    private final Map<Subject, Number> subjects = new HashMap<>();

    public Student(String name) {
        Objects.requireNonNull(name, "The argument name cannot be null.");

        this.name = name;
    }

    public void addSubject(Subject subject, Number grade) {
        Objects.requireNonNull(subject, "The argument subject cannot be null.");
        Objects.requireNonNull(grade, "The argument grade cannot be null.");

        if (grade.getClass() != subject.getGradeType()) {
            throw new ClassCastException("Illegal grade type. Specified value: " + grade.getClass() +
                    " Please specify a new value for " + subject.name() + " with type " + subject.getGradeType());
        }

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
