package com.epam.javase.t05;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aivanov on 2/28/2017.
 */
public class GroupShaperTest {
    @Test
    public void getStudentsMark() throws Exception {

        Student std1 = new Student("Andrey");
        Student std2 = new Student("Vladimir");
        Student std3 = new Student("Alexandr");

        List list = new ArrayList();
        list.add(std1);
        list.add(std2);
        list.add(std3);

        std1.addSubject(Subject.BIOLOGY, 50.0);
        std1.addSubject(Subject.CHEMISTRY, 5);

        std2.addSubject(Subject.HISTORY, 5.0);
        std2.addSubject(Subject.ENGLISH, 80);

        std3.addSubject(Subject.BIOLOGY, 80.0);
        std3.addSubject(Subject.ENGLISH, 90);

        GroupShaper group = new GroupShaper(Subject.BIOLOGY, list);

        System.out.println(group.getStudentsMark());

        GroupShaper group2 = new GroupShaper(Subject.SPANISH, list);

        System.out.println(group2.getStudentsMark());
    }

}