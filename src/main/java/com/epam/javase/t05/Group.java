package com.epam.javase.t05;

import java.util.*;

/**
 * Represents a group of students who are studying the specified subjects.
 *
 * Created by aivanov on 3/3/2017.
 */
public class Group {

    private final String name;
    private final Set<Subject> subjectsSet;
    private final Set<Student> studentsSet;

    public Group(String name, Set<Subject> subj, Set<Student> std) {
        this.name = name;
        this.subjectsSet = new HashSet<>(subj);
        this.studentsSet = new HashSet<>(std);
    }

    @Override
    public String toString() {
        String str = "Group: " + name + System.lineSeparator();
        for (Student std : studentsSet) {
            str += std.getName() + " -";
            for (Subject sub : subjectsSet) {
                 str += " " + sub.name() + ": " + std.getSubjectGrade(sub) + ";";
            }
            str += System.lineSeparator();
        }
        return str;
    }
}
