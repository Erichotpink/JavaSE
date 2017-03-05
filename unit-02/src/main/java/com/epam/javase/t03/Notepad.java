package com.epam.javase.t03;

/**
 * Represents a simple notepad.
 *
 * Created by aivanov on 3/1/2017.
 */
public class Notepad extends Stationery {
    private int pageCount;

    public Notepad(double price) {
        this(price, 50);
    }

    public Notepad(double price, int pageCount) {
        this.price = price;
        this.pageCount = pageCount;
        this.name = "Notepad";
    }

    public int getPageCount() {
        return pageCount;
    }
}
