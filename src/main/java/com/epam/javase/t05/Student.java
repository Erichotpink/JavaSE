package com.epam.javase.t05;

import java.util.*;

/**
 * Represents a student. Contains name and map of subjects and grades.
 *
 * Created by aivanov on 2/28/2017.
 */
public class Student {

    private final String name;
    private final Map<Subject, Number> subjects = new HashMap<>();

    public Student(String name) {
        Objects.requireNonNull(name, "The argument name cannot be null.");

        this.name = name;
    }

    /**
     * Add the subject and grade to the student object.
     *
     * @param subject subject to be added
     * @param grade grade to be added
     */
    public void addSubject(Subject subject, Number grade) {
        Objects.requireNonNull(subject, "The argument subject cannot be null.");
        Objects.requireNonNull(grade, "The argument grade cannot be null.");

        grade = subject.convertGradeToAcceptableFormat(grade);
        subjects.put(subject, grade);
    }

    public Number getSubjectGrade(Subject subject) {
        return subjects.get(subject);
    }

    public Set<Subject> getStudentsSubjects() {
        return new HashSet<Subject>(subjects.keySet());
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
