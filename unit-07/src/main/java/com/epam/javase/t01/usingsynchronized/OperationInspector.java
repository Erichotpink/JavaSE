package com.epam.javase.t01.usingsynchronized;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by aivanov on 3/20/2017.
 */
public class OperationInspector {

    Queue<String[]> orders = new LinkedList<>();

    public OperationInspector(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("The specified file doesn't exist");
        }

        fileReader(file);
    }


    private void fileReader(File file) {
        try(BufferedReader in = new BufferedReader(new FileReader(file))) {
            String str = "";
            while ((str = in.readLine()) != null) {
                String[] temp = str.split(",");
                if (temp.length == 3) {
                    orders.add(temp);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Queue<String[]> getOrdersQueue() {
        return new LinkedList<>(orders);
    }

}
