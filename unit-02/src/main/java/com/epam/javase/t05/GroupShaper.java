package com.epam.javase.t05;

import java.util.*;

/**
 * Represents a list of students and their mapping to groups.
 *
 * Created by aivanov on 2/28/2017.
 */
public class GroupShaper {

    private Map<Student, List<Group>> studentsSet = new HashMap<>();
    private Set<Group> groups = new HashSet<>();

    public GroupShaper(Set<Student> students) {
        for (Student s : students) {
            studentsSet.put(s, new ArrayList<>());
        }
    }

    public List<Group> getStudentGroups(Student student) {
        return new ArrayList<>(studentsSet.get(student));
    }

    /**
     * Create a new group that include students who are studying the specified subjects.
     *
     * @param name name of the group
     * @param subj subjects list to filter students
     */
    public void createGroup(String name, Set<Subject> subj) {
        Set<Student> stdSet = new HashSet<>();

        for (Student s : studentsSet.keySet()) {
            if (s.getStudentsSubjects().containsAll(subj)) {
                stdSet.add(s);
            }
        }

        Group group = new Group(name, new HashSet(subj), stdSet);
        groups.add(group);

        for (Student s : stdSet) {
            studentsSet.get(s).add(group);
        }
    }

    public void printAllGroups() {
        for (Group g : groups) {
            System.out.println(g);
        }
    }
}
