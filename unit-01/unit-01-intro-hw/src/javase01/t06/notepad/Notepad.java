package javase01.t06.notepad;

import java.util.Scanner;

/**
 * The class represents a simple notebook and the class implements the following methods: add, remove, set and print.
 * The implementation is based on the array of Entry objects.
 *
 * Add operation runs in amortized constant time.
 * Set operation runs in constant time.
 * Remove operation runs in linear time.
 *
 * Default constructor creates an array of length 10, though you can invoke the constructor with length parameter.
 * Due to restrictions of the module 1 "Java Fundamentals", the class implementation doesn't invoke
 * methods Arrays.copyOf, System.arraycopy.
 *
 * Created by Andrey Ivanov on 2/13/2017.
 */

public class Notepad {

    private final static int DEFAULT_SIZE = 10;

    private Entry[] data;
    private int nextPosition = 0;

    /**
     * Construct an empty notepad with default capacity of 10;
     */
    public Notepad() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructs an empty notepad with the specified size.
     * @param size the initial capacity of the notepad
     */
    public Notepad(int size) {
        data = new Entry[size];
    }

    /**
     * Add a new entry to the notepad.
     * @param item to be inserted
     */
    public void addEntry(Entry item) {
        if (item == null) {
            return;
        }

        ensureCapacity(nextPosition);

        data[nextPosition] = item;
        nextPosition++;
    }

    /**
     * Remove the entry with specified index.
     * @param index index of the entry to remove
     */

    public void removeEntry(int index) {
        if (index > data.length || index < 0) {
            return;
        }

        for (int i = index; i < data.length - 1; i++) {
            data[i] = data[i+1];
        }

        nextPosition--;
    }

    /**
     * Remove the first occurrence of the specified entry.
     * @param item the entry to remove
     */

    public void removeEntry(Entry item) {
        if (item == null) {
            return;
        }

        for (int i = 0; i < nextPosition; i++) {
            if (data[i].equals(item)) {
                removeEntry(i);
                return;
            }
        }
    }

    /**
     * Replace the entry at the specified position with the specicified value.
     * @param index index of the entry to replace
     * @param item entry to be stored at the specified index
     */
    public void setEntry(int index, Entry item) {
        if (index > data.length || index < 0 || item == null) {
            return;
        }

        data[index] = item;
    }

    /**
     * Print out all the entries in the notepad.
     */
    public void print() {

        for (int i = 0; i < nextPosition; i++) {
            System.out.println("[" + i + "]: " + data[i]);
        }
    }

    /**
     * Increase the capacity of the notepad.
     * @param size the desired size
     */
    private void ensureCapacity(int size) {
        if (size <= data.length) {
            return;
        }

        Entry[] newData = new Entry[size];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    public static void main(String... args) {

        Notepad notepad = new Notepad();

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add entry");
            System.out.println("2. Remove entry by index");
            System.out.println("3. Remove entry by value");
            System.out.println("4. Modify entry by index");
            System.out.println("5. Print all entries");
            System.out.println("6. Exit");

            switch (in.nextInt()) {
                case 1:
                    notepad.addEntry(new Entry(in.next()));
                    break;
                case 2:
                    notepad.removeEntry(in.nextInt());
                    break;
                case 3:
                    notepad.removeEntry(new Entry(in.next()));
                    break;
                case 4:
                    notepad.setEntry(in.nextInt(), new Entry(in.next()));
                    break;
                case 5:
                    notepad.print();
                    break;
                case 6:
                    return;
            }
        }
    }

}