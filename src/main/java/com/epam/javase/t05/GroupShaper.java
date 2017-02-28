package com.epam.javase.t05;

import java.util.*;

/**
 * Created by aivanov on 2/28/2017.
 */
public class GroupShaper {

    private Subject subject;
    private Map<Student, Number> students = new HashMap<>();

    public GroupShaper(Subject subject, List<Student> list) {
        this.subject = subject;
        for (Student std : list) {
            addStudent(std);
        }
    }

    public void addStudent(Student student) {

        Number grade = student.getSubjectGrade(subject);
        if ( grade != null) {
            students.put(student, grade);
        }
    }

    public Map getStudentsMark() {
        return new HashMap(students);
    }

}
