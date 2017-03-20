package com.epam.javase.t01.usingsynchronized;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by aivanov on 3/20/2017.
 */
public class OperationInspector {

    int defaultBalance = 500;
    Map<Integer, Account> map = new HashMap<>();
    Queue<String[]> orders = new LinkedList<>();

    public OperationInspector(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("The specified file doesn't exist");
        }

        fileReader(file);
    }

    public void run() {
        parseOrdersAndCompleteTransfer();
    }

    public int getAccountBalanceByID(int id) {
        if (id < 1) {
            return Integer.MIN_VALUE;
        }

        return map.get(id).getBalance();
    }

    public Queue<String[]> getOrdersQueue() {
        return new LinkedList<>(orders);
    }

    private void parseOrdersAndCompleteTransfer() {

        while (!orders.isEmpty()) {
            String[] s = orders.poll();

            int srcID = Integer.parseInt(s[0]);
            int destID = Integer.parseInt(s[1]);
            int amount = Integer.parseInt(s[2]);

            if (!map.containsKey(srcID)) {
                map.put(srcID, new Account(defaultBalance));
            }

            if (!map.containsKey(destID)) {
                map.put(destID, new Account(defaultBalance));
            }

            Thread t = new Thread(new OperatorTransfer(map.get(srcID), map.get(destID), amount));
            t.start();
        }
    }

    private void fileReader(File file) {
        try(BufferedReader in = new BufferedReader(new FileReader(file))) {
            String str = "";

            while ((str = in.readLine()) != null) {
                String[] temp = str.split(",");
                if (temp.length == 3 &&
                        Integer.parseInt(temp[0]) > 0 &&
                        Integer.parseInt(temp[1]) > 0) {
                    orders.add(temp);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
