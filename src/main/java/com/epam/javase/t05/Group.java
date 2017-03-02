package com.epam.javase.t05;

import sun.reflect.generics.tree.Tree;

import java.text.MessageFormat;
import java.util.*;

/**
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

    public boolean containsStudent(Student std) {
        return studentsSet.contains(std);
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

    public static void main(String[] args) {
        Student std1 = new Student("Vasya");
        Student std2 = new Student("Petya");
        Set<Student> list = new HashSet<>();
        list.add(std1);
        list.add(std2);

        std1.addSubject(Subject.MATH, 5);
        std1.addSubject(Subject.ENGLISH, 90);

        std2.addSubject(Subject.MATH, 4);
        std2.addSubject(Subject.ENGLISH, 85);

        Set<Subject> set = new HashSet<>();
        set.add(Subject.MATH);
        set.add(Subject.ENGLISH);

        Group group = new Group("Group1", set, list);

        System.out.println(group);
    }
}
