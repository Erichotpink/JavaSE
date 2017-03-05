package com.epam.javase.t02;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 2/27/2017.
 */
public class EmployeeTest {


    @Test
    public void getStationeryTotalCost() throws Exception {

        final Stationery pen = new Stationery("Pen", 10.0);
        final Stationery pencil = new Stationery("Pencil", 5.5);
        final Stationery scotchTape = new Stationery("scotchTape", 14.5);

        Employee emp = new Employee("Vasya", "Ivanov");


        emp.addStationery(pen);
        emp.addStationery(pencil);
        emp.addStationery(scotchTape);

        assertTrue(Double.compare(emp.getStationeryTotalCost(), 30.0) == 0);
    }

    @Test
    public void testDefaultTotalCostEqualZero() {
        Employee emp = new Employee("Vladimir", "Sidorov");

        assertTrue(Double.compare(emp.getStationeryTotalCost(), 0.0) == 0);
    }

}