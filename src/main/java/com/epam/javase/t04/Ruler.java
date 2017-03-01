package com.epam.javase.t04;

/**
 * Represents a simple ruler.
 *
 * Created by aivanov on 3/1/2017.
 */
public class Ruler extends Stationery {

    private int size;

    public Ruler(double price) {
        this(price, 30);
    }

    public Ruler(double price, int size) {
        this.price = price;
        this.size = size;
        this.name = "Ruler";
    }

    public int getSize() {
        return size;
    }
}
