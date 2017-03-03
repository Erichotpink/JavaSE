package com.epam.javase.t05;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 2/28/2017.
 */
public class GroupShaperTest {

    @Test
    public void testCorrectValuesForSubjectClassMethodConvertGradeToAcceptableFormat() throws Exception {

        testSubjectWithIntegerGrades();
        testSubjectWithDoubleGrades();
    }

    private void testSubjectWithIntegerGrades() {

        int x = 1;
        int y = 5;
        double z = 4.5;

        Number temp = Subject.MATH.convertGradeToAcceptableFormat(x);
        assertTrue(temp.equals(x));

        temp = Subject.MATH.convertGradeToAcceptableFormat(y);
        assertTrue(temp.equals(y));

        temp = Subject.MATH.convertGradeToAcceptableFormat(z);
        assertTrue(temp.equals((int) z));
    }

    private void testSubjectWithDoubleGrades() {

        double x = 0.1;
        double y = 10.1;
        int z = 5;

        Number temp = Subject.HISTORY.convertGradeToAcceptableFormat(x);
        assertTrue(temp.equals(x));

        temp = Subject.HISTORY.convertGradeToAcceptableFormat(y);
        assertTrue(temp.equals(y));

        temp = Subject.HISTORY.convertGradeToAcceptableFormat(z);
        assertTrue(temp.equals((double) z));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIncorrectValuesForSubjectClassMethodConvertGradeToAcceptableFormat() throws Exception {

        Subject.MATH.convertGradeToAcceptableFormat(-1);
        Subject.MATH.convertGradeToAcceptableFormat(10);

        Subject.HISTORY.convertGradeToAcceptableFormat(0.0);
        Subject.HISTORY.convertGradeToAcceptableFormat(10.11);
    }

    @Test
    public void testStudentsMethodsPositiveCase() throws Exception {

        Student std1 = new Student("Andrey");
        double grade = 10.1;
        int mark = 5;

        std1.addSubject(Subject.HISTORY, grade);
        assertTrue(std1.getSubjectGrade(Subject.HISTORY).equals(grade));

        std1.addSubject(Subject.MATH, mark);
        assertTrue(std1.getSubjectGrade(Subject.MATH).equals(mark));

        std1.addSubject(Subject.HISTORY, mark);
        assertTrue(std1.getSubjectGrade(Subject.HISTORY).equals((double) mark));
    }

    @Test
    public void testStudentsMethodsNegativeCase() throws Exception {

        Student std1 = new Student("Vladimir");
        double grade = -10.1;
        int mark = 1000;

        try {

            std1.addSubject(Subject.ENGLISH, mark);
            std1.addSubject(Subject.SPANISH, grade);
            std1.addSubject(Subject.BIOLOGY, null);

            std1.getSubjectGrade(Subject.ENGLISH).equals(mark);
            std1.getSubjectGrade(Subject.SPANISH).equals(null);
            std1.getSubjectGrade(Subject.BIOLOGY).equals(null);
        } catch (IllegalArgumentException | NullPointerException ex) {

        }
    }

    @Test
    public void testGroupShaperPositiveCase() throws Exception {
        Student std1 = new Student("Vladimir");
        Student std2 = new Student("Alexandr");
        Student std3 = new Student("Andrey");

        Set<Student> stdSet = new HashSet<>();
        stdSet.add(std1);
        stdSet.add(std2);
        stdSet.add(std3);

        List list = new ArrayList();
        list.add(std1);

        std1.addSubject(Subject.BIOLOGY, 50.0);
        std1.addSubject(Subject.CHEMISTRY, 5.0);

        std2.addSubject(Subject.HISTORY, 5);
        std2.addSubject(Subject.ENGLISH, 80.0);

        std3.addSubject(Subject.BIOLOGY, 80);
        std3.addSubject(Subject.ENGLISH, 90.0);
        std3.addSubject(Subject.HISTORY, 5.0);

        GroupShaper group = new GroupShaper(stdSet);

        Set<Subject> subjSet1 = new HashSet<>();
        subjSet1.add(Subject.ENGLISH);
        subjSet1.add(Subject.HISTORY);

        Set<Subject> subjSet2 = new HashSet<>();
        subjSet2.add(Subject.BIOLOGY);

        group.createGroup("First", subjSet1);
        group.createGroup("Second", subjSet2);

        System.out.println(group.getStudentGroups(std3));
    }
}