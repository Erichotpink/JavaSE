package com.epam.javase.t05;

import java.util.*;

/**
 * Created by aivanov on 2/28/2017.
 */
public class GroupShaper {

    private Set<Student> studentsSet = new HashSet<>();
    private Set<Group> groupsSet = new HashSet<>();

    public GroupShaper(Set<Student> students) {
        studentsSet.addAll(students);
    }

    public Set<Group> getStudentGroups(Student student) {
        Set<Group> temp = new HashSet<>();
        for (Group g : groupsSet) {
            if (g.containsStudent(student)) {temp.add(g);}
        }
        return temp;
    }

    public void createGroup(String name, Set<Subject> subj) {
        Set<Student> stdSet = new HashSet<>();

        for (Student s : studentsSet) {
            if (s.getStudentsSubjects().containsAll(subj)) {
                stdSet.add(s);
            }
        }

        groupsSet.add(new Group(name, new HashSet<Subject>(subj), stdSet));
    }

    public void printAllGroups() {
        for (Group g : groupsSet) {
            System.out.println(g);
        }
    }
}
